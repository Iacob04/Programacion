import Foundation
import UIKit

// MARK: - Errores

enum ClaudeError: LocalizedError {
    case sinClaveAPI
    case respuestaInvalida
    case errorAPI(String)

    var errorDescription: String? {
        switch self {
        case .sinClaveAPI:
            "No has configurado tu clave de API de Anthropic. Ve a Ajustes para añadirla."
        case .respuestaInvalida:
            "La respuesta de la IA no se pudo interpretar. Inténtalo de nuevo."
        case .errorAPI(let mensaje):
            "Error de la API: \(mensaje)"
        }
    }
}

// MARK: - Resultados tipados

struct AnalisisPlato: Decodable {
    struct Alimento: Decodable {
        let nombre: String
        let gramos: Double
        let calorias: Double
        let proteinas: Double
        let carbohidratos: Double
        let grasas: Double
    }
    let esComida: Bool
    let descripcion: String
    let alimentos: [Alimento]

    enum CodingKeys: String, CodingKey {
        case esComida = "es_comida"
        case descripcion
        case alimentos
    }

    var totalCalorias: Double { alimentos.reduce(0) { $0 + $1.calorias } }
}

struct RecetasGeneradas: Decodable {
    struct RecetaIA: Decodable {
        let nombre: String
        let resumen: String
        let caloriasPorRacion: Double
        let raciones: Int
        let tiempoMinutos: Int
        let ingredientes: [String]
        let pasos: [String]

        enum CodingKeys: String, CodingKey {
            case nombre, resumen, raciones, ingredientes, pasos
            case caloriasPorRacion = "calorias_por_racion"
            case tiempoMinutos = "tiempo_minutos"
        }
    }
    let recetas: [RecetaIA]
}

// MARK: - Servicio

enum ClaudeService {
    private static let modelo = "claude-opus-4-8"
    private static let endpoint = URL(string: "https://api.anthropic.com/v1/messages")!

    // MARK: Análisis de foto de plato

    static func analizarPlato(imagen: UIImage) async throws -> AnalisisPlato {
        guard let datosJPEG = imagen.redimensionada(maxLado: 1024).jpegData(compressionQuality: 0.7) else {
            throw ClaudeError.respuestaInvalida
        }
        let base64 = datosJPEG.base64EncodedString()

        let esquema: [String: Any] = [
            "type": "object",
            "properties": [
                "es_comida": ["type": "boolean",
                              "description": "true si la imagen muestra comida"],
                "descripcion": ["type": "string",
                                "description": "Descripción breve del plato en español"],
                "alimentos": [
                    "type": "array",
                    "items": [
                        "type": "object",
                        "properties": [
                            "nombre": ["type": "string"],
                            "gramos": ["type": "number", "description": "Porción estimada en gramos"],
                            "calorias": ["type": "number"],
                            "proteinas": ["type": "number", "description": "gramos de proteína"],
                            "carbohidratos": ["type": "number", "description": "gramos de carbohidratos"],
                            "grasas": ["type": "number", "description": "gramos de grasa"]
                        ],
                        "required": ["nombre", "gramos", "calorias", "proteinas", "carbohidratos", "grasas"],
                        "additionalProperties": false
                    ]
                ]
            ],
            "required": ["es_comida", "descripcion", "alimentos"],
            "additionalProperties": false
        ]

        let cuerpo: [String: Any] = [
            "model": modelo,
            "max_tokens": 4096,
            "system": "Eres un nutricionista experto en estimar porciones y calorías a partir de fotos de platos. Responde siempre en español.",
            "output_config": ["format": ["type": "json_schema", "schema": esquema]],
            "messages": [[
                "role": "user",
                "content": [
                    ["type": "image",
                     "source": ["type": "base64", "media_type": "image/jpeg", "data": base64]],
                    ["type": "text",
                     "text": "Identifica los alimentos de esta foto, estima la porción de cada uno en gramos y calcula sus calorías y macronutrientes aproximados. Si la imagen no muestra comida, devuelve es_comida=false y la lista vacía."]
                ]
            ]]
        ]

        let texto = try await enviar(cuerpo: cuerpo)
        guard let datos = texto.data(using: .utf8),
              let analisis = try? JSONDecoder().decode(AnalisisPlato.self, from: datos) else {
            throw ClaudeError.respuestaInvalida
        }
        return analisis
    }

    // MARK: Generación de recetas

    static func generarRecetas(gustos: [String], notasDieta: String,
                               objetivoDiario: Double) async throws -> [RecetasGeneradas.RecetaIA] {
        let esquema: [String: Any] = [
            "type": "object",
            "properties": [
                "recetas": [
                    "type": "array",
                    "items": [
                        "type": "object",
                        "properties": [
                            "nombre": ["type": "string"],
                            "resumen": ["type": "string", "description": "Una frase describiendo el plato"],
                            "calorias_por_racion": ["type": "number"],
                            "raciones": ["type": "integer"],
                            "tiempo_minutos": ["type": "integer"],
                            "ingredientes": ["type": "array", "items": ["type": "string"],
                                             "description": "Ingredientes con cantidades"],
                            "pasos": ["type": "array", "items": ["type": "string"],
                                      "description": "Pasos de preparación en orden"]
                        ],
                        "required": ["nombre", "resumen", "calorias_por_racion", "raciones",
                                     "tiempo_minutos", "ingredientes", "pasos"],
                        "additionalProperties": false
                    ]
                ]
            ],
            "required": ["recetas"],
            "additionalProperties": false
        ]

        let listaGustos = gustos.isEmpty ? "sin preferencias concretas" : gustos.joined(separator: ", ")
        let notas = notasDieta.isEmpty ? "ninguna" : notasDieta
        let prompt = """
        Genera 3 recetas saludables y variadas pensadas para alguien en déficit calórico \
        con un objetivo diario de \(Int(objetivoDiario)) kcal.
        Gustos del usuario: \(listaGustos).
        Restricciones o notas: \(notas).
        Cada receta debe ser realista, con ingredientes fáciles de encontrar en España, \
        cantidades concretas y pasos claros. Las calorías por ración deben rondar entre \
        el 25% y el 40% del objetivo diario.
        """

        let cuerpo: [String: Any] = [
            "model": modelo,
            "max_tokens": 8000,
            "system": "Eres un chef y nutricionista. Creas recetas saludables en español con calorías calculadas con precisión.",
            "output_config": ["format": ["type": "json_schema", "schema": esquema]],
            "messages": [["role": "user", "content": prompt]]
        ]

        let texto = try await enviar(cuerpo: cuerpo)
        guard let datos = texto.data(using: .utf8),
              let resultado = try? JSONDecoder().decode(RecetasGeneradas.self, from: datos) else {
            throw ClaudeError.respuestaInvalida
        }
        return resultado.recetas
    }

    // MARK: Llamada HTTP

    private struct APIResponse: Decodable {
        struct Bloque: Decodable {
            let type: String
            let text: String?
        }
        let content: [Bloque]
        let stop_reason: String?
    }

    private struct APIErrorEnvelope: Decodable {
        struct Detalle: Decodable { let message: String }
        let error: Detalle
    }

    private static func enviar(cuerpo: [String: Any]) async throws -> String {
        guard let clave = KeychainHelper.leerClaveAPI(), !clave.isEmpty else {
            throw ClaudeError.sinClaveAPI
        }

        var peticion = URLRequest(url: endpoint)
        peticion.httpMethod = "POST"
        peticion.timeoutInterval = 300
        peticion.setValue("application/json", forHTTPHeaderField: "Content-Type")
        peticion.setValue(clave, forHTTPHeaderField: "x-api-key")
        peticion.setValue("2023-06-01", forHTTPHeaderField: "anthropic-version")
        peticion.httpBody = try JSONSerialization.data(withJSONObject: cuerpo)

        let (datos, respuesta) = try await URLSession.shared.data(for: peticion)
        guard let http = respuesta as? HTTPURLResponse else { throw ClaudeError.respuestaInvalida }
        guard http.statusCode == 200 else {
            if let envoltorio = try? JSONDecoder().decode(APIErrorEnvelope.self, from: datos) {
                throw ClaudeError.errorAPI(envoltorio.error.message)
            }
            throw ClaudeError.errorAPI("HTTP \(http.statusCode)")
        }

        let decodificada = try JSONDecoder().decode(APIResponse.self, from: datos)
        guard decodificada.stop_reason != "refusal",
              let texto = decodificada.content.first(where: { $0.type == "text" })?.text else {
            throw ClaudeError.respuestaInvalida
        }
        return texto
    }
}

// MARK: - Redimensionado de imagen (reduce coste y latencia de la API)

private extension UIImage {
    func redimensionada(maxLado: CGFloat) -> UIImage {
        let ladoMayor = max(size.width, size.height)
        guard ladoMayor > maxLado else { return self }
        let escala = maxLado / ladoMayor
        let nuevoTamano = CGSize(width: size.width * escala, height: size.height * escala)
        let renderer = UIGraphicsImageRenderer(size: nuevoTamano)
        return renderer.image { _ in
            draw(in: CGRect(origin: .zero, size: nuevoTamano))
        }
    }
}
