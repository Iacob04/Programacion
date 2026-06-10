import SwiftUI
import SwiftData

struct OnboardingView: View {
    @Environment(\.modelContext) private var contexto

    @State private var paso = 0

    // Paso 1: datos personales
    @State private var nombre = ""
    @State private var sexo: Sexo = .masculino
    @State private var edad = 25
    @State private var alturaCm = 175.0
    @State private var pesoKg = 75.0

    // Paso 2: actividad y déficit
    @State private var actividad: NivelActividad = .ligero
    @State private var deficit = 500

    // Paso 3: gustos y API
    @State private var gustosSeleccionados: Set<String> = []
    @State private var notasDieta = ""
    @State private var claveAPI = ""

    private let opcionesGustos = [
        "Pollo", "Pescado", "Carne roja", "Huevos", "Pasta", "Arroz",
        "Legumbres", "Ensaladas", "Verduras", "Fruta", "Comida italiana",
        "Comida mexicana", "Comida asiática", "Vegetariano", "Vegano"
    ]

    private var mantenimiento: Double {
        CalorieCalculator.tdee(sexo: sexo, edad: edad, alturaCm: alturaCm,
                               pesoKg: pesoKg, actividad: actividad)
    }

    var body: some View {
        NavigationStack {
            TabView(selection: $paso) {
                pasoDatos.tag(0)
                pasoActividad.tag(1)
                pasoGustos.tag(2)
            }
            .tabViewStyle(.page(indexDisplayMode: .always))
            .indexViewStyle(.page(backgroundDisplayMode: .always))
            .navigationTitle("Bienvenido a CalorIA")
            .navigationBarTitleDisplayMode(.inline)
        }
    }

    // MARK: Paso 1

    private var pasoDatos: some View {
        Form {
            Section("Tus datos") {
                TextField("Nombre", text: $nombre)
                Picker("Sexo", selection: $sexo) {
                    ForEach(Sexo.allCases) { Text($0.rawValue).tag($0) }
                }
                Stepper("Edad: \(edad) años", value: $edad, in: 14...100)
                Stepper("Altura: \(Int(alturaCm)) cm", value: $alturaCm, in: 120...230, step: 1)
                Stepper("Peso: \(pesoKg, specifier: "%.1f") kg", value: $pesoKg, in: 35...250, step: 0.5)
            }
            Section {
                Button("Siguiente") { withAnimation { paso = 1 } }
                    .frame(maxWidth: .infinity)
                    .bold()
            }
        }
    }

    // MARK: Paso 2

    private var pasoActividad: some View {
        Form {
            Section("Nivel de actividad") {
                Picker("Actividad", selection: $actividad) {
                    ForEach(NivelActividad.allCases) { nivel in
                        VStack(alignment: .leading) {
                            Text(nivel.rawValue)
                            Text(nivel.descripcion).font(.caption).foregroundStyle(.secondary)
                        }.tag(nivel)
                    }
                }
                .pickerStyle(.inline)
                .labelsHidden()
            }
            Section("Déficit calórico diario") {
                Picker("Déficit", selection: $deficit) {
                    Text("Suave: −250 kcal").tag(250)
                    Text("Moderado: −500 kcal").tag(500)
                    Text("Intenso: −750 kcal").tag(750)
                }
                .pickerStyle(.inline)
                .labelsHidden()
            }
            Section("Tu plan") {
                LabeledContent("Mantenimiento", value: "\(Int(mantenimiento)) kcal")
                LabeledContent("Objetivo diario") {
                    Text("\(Int(max(mantenimiento - Double(deficit), 1200))) kcal")
                        .bold().foregroundStyle(.green)
                }
            }
            Section {
                Button("Siguiente") { withAnimation { paso = 2 } }
                    .frame(maxWidth: .infinity)
                    .bold()
            }
        }
    }

    // MARK: Paso 3

    private var pasoGustos: some View {
        Form {
            Section("¿Qué te gusta comer?") {
                ForEach(opcionesGustos, id: \.self) { gusto in
                    Button {
                        if gustosSeleccionados.contains(gusto) {
                            gustosSeleccionados.remove(gusto)
                        } else {
                            gustosSeleccionados.insert(gusto)
                        }
                    } label: {
                        HStack {
                            Text(gusto).foregroundStyle(.primary)
                            Spacer()
                            if gustosSeleccionados.contains(gusto) {
                                Image(systemName: "checkmark.circle.fill")
                                    .foregroundStyle(.green)
                            }
                        }
                    }
                }
            }
            Section("Alergias o restricciones (opcional)") {
                TextField("Ej.: sin gluten, alergia a frutos secos…", text: $notasDieta, axis: .vertical)
            }
            Section {
                SecureField("sk-ant-…", text: $claveAPI)
            } header: {
                Text("Clave API de Anthropic")
            } footer: {
                Text("Necesaria para analizar fotos y generar recetas con IA. Consíguela en console.anthropic.com. Puedes añadirla después en Ajustes.")
            }
            Section {
                Button("Empezar") { crearPerfil() }
                    .frame(maxWidth: .infinity)
                    .bold()
            }
        }
    }

    private func crearPerfil() {
        if !claveAPI.trimmingCharacters(in: .whitespaces).isEmpty {
            KeychainHelper.guardarClaveAPI(claveAPI.trimmingCharacters(in: .whitespaces))
        }
        let perfil = UserProfile(
            nombre: nombre.isEmpty ? "Usuario" : nombre,
            sexo: sexo,
            edad: edad,
            alturaCm: alturaCm,
            pesoKg: pesoKg,
            nivelActividad: actividad,
            deficitDiario: deficit,
            gustos: Array(gustosSeleccionados).sorted(),
            notasDieta: notasDieta
        )
        contexto.insert(perfil)
    }
}
