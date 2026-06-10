import Foundation

// MARK: - Modelo del producto

struct ProductoEscaneado {
    let nombre: String
    let marca: String?
    let kcalPor100g: Double
    let proteinasPor100g: Double
    let carbohidratosPor100g: Double
    let grasasPor100g: Double

    func calorias(gramos: Double) -> Double { kcalPor100g * gramos / 100 }
    func proteinas(gramos: Double) -> Double { proteinasPor100g * gramos / 100 }
    func carbohidratos(gramos: Double) -> Double { carbohidratosPor100g * gramos / 100 }
    func grasas(gramos: Double) -> Double { grasasPor100g * gramos / 100 }
}

enum OpenFoodFactsError: LocalizedError {
    case productoNoEncontrado
    case sinDatosNutricionales
    case errorRed

    var errorDescription: String? {
        switch self {
        case .productoNoEncontrado:
            "Este producto no está en la base de datos de OpenFoodFacts. Añádelo manualmente."
        case .sinDatosNutricionales:
            "El producto existe pero no tiene información de calorías registrada."
        case .errorRed:
            "No se pudo conectar con OpenFoodFacts. Comprueba tu conexión."
        }
    }
}

// MARK: - Servicio

enum OpenFoodFactsService {

    private struct Respuesta: Decodable {
        let status: Int
        let product: Producto?
    }

    private struct Producto: Decodable {
        let productName: String?
        let productNameEs: String?
        let brands: String?
        let nutriments: Nutrientes?

        enum CodingKeys: String, CodingKey {
            case productName = "product_name"
            case productNameEs = "product_name_es"
            case brands
            case nutriments
        }
    }

    private struct Nutrientes: Decodable {
        let energiaKcal: Double?
        let proteinas: Double?
        let carbohidratos: Double?
        let grasas: Double?

        enum CodingKeys: String, CodingKey {
            case energiaKcal = "energy-kcal_100g"
            case proteinas = "proteins_100g"
            case carbohidratos = "carbohydrates_100g"
            case grasas = "fat_100g"
        }
    }

    static func buscarProducto(codigoBarras: String) async throws -> ProductoEscaneado {
        guard let url = URL(string: "https://world.openfoodfacts.org/api/v2/product/\(codigoBarras).json") else {
            throw OpenFoodFactsError.errorRed
        }
        var peticion = URLRequest(url: url)
        peticion.setValue("CalorIA-iOS/1.0", forHTTPHeaderField: "User-Agent")

        let datos: Data
        do {
            (datos, _) = try await URLSession.shared.data(for: peticion)
        } catch {
            throw OpenFoodFactsError.errorRed
        }

        guard let respuesta = try? JSONDecoder().decode(Respuesta.self, from: datos),
              respuesta.status == 1,
              let producto = respuesta.product else {
            throw OpenFoodFactsError.productoNoEncontrado
        }
        guard let kcal = producto.nutriments?.energiaKcal else {
            throw OpenFoodFactsError.sinDatosNutricionales
        }

        return ProductoEscaneado(
            nombre: producto.productNameEs ?? producto.productName ?? "Producto sin nombre",
            marca: producto.brands,
            kcalPor100g: kcal,
            proteinasPor100g: producto.nutriments?.proteinas ?? 0,
            carbohidratosPor100g: producto.nutriments?.carbohidratos ?? 0,
            grasasPor100g: producto.nutriments?.grasas ?? 0
        )
    }
}
