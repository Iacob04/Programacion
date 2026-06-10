import SwiftUI
import SwiftData

struct SettingsView: View {
    @Bindable var perfil: UserProfile

    @Environment(\.modelContext) private var contexto
    @State private var claveAPI = KeychainHelper.leerClaveAPI() ?? ""
    @State private var claveGuardada = false
    @State private var confirmarBorrado = false

    private let opcionesGustos = [
        "Pollo", "Pescado", "Carne roja", "Huevos", "Pasta", "Arroz",
        "Legumbres", "Ensaladas", "Verduras", "Fruta", "Comida italiana",
        "Comida mexicana", "Comida asiática", "Vegetariano", "Vegano"
    ]

    var body: some View {
        NavigationStack {
            Form {
                Section("Datos personales") {
                    TextField("Nombre", text: $perfil.nombre)
                    Picker("Sexo", selection: sexoBinding) {
                        ForEach(Sexo.allCases) { Text($0.rawValue).tag($0) }
                    }
                    Stepper("Edad: \(perfil.edad) años", value: $perfil.edad, in: 14...100)
                    Stepper("Altura: \(Int(perfil.alturaCm)) cm", value: $perfil.alturaCm, in: 120...230, step: 1)
                    Stepper("Peso: \(perfil.pesoKg, specifier: "%.1f") kg", value: $perfil.pesoKg, in: 35...250, step: 0.5)
                }

                Section("Actividad y objetivo") {
                    Picker("Nivel de actividad", selection: actividadBinding) {
                        ForEach(NivelActividad.allCases) { Text($0.rawValue).tag($0) }
                    }
                    Picker("Déficit diario", selection: $perfil.deficitDiario) {
                        Text("Suave: −250 kcal").tag(250)
                        Text("Moderado: −500 kcal").tag(500)
                        Text("Intenso: −750 kcal").tag(750)
                    }
                    LabeledContent("Mantenimiento", value: "\(Int(perfil.mantenimiento)) kcal")
                    LabeledContent("Objetivo diario") {
                        Text("\(Int(perfil.objetivoDiario)) kcal").bold().foregroundStyle(.green)
                    }
                }

                Section("Gustos de comida") {
                    ForEach(opcionesGustos, id: \.self) { gusto in
                        Button {
                            if let indice = perfil.gustos.firstIndex(of: gusto) {
                                perfil.gustos.remove(at: indice)
                            } else {
                                perfil.gustos.append(gusto)
                                perfil.gustos.sort()
                            }
                        } label: {
                            HStack {
                                Text(gusto).foregroundStyle(.primary)
                                Spacer()
                                if perfil.gustos.contains(gusto) {
                                    Image(systemName: "checkmark.circle.fill").foregroundStyle(.green)
                                }
                            }
                        }
                    }
                    TextField("Alergias o restricciones", text: $perfil.notasDieta, axis: .vertical)
                }

                Section {
                    SecureField("sk-ant-…", text: $claveAPI)
                    Button(claveGuardada ? "Clave guardada ✓" : "Guardar clave") {
                        KeychainHelper.guardarClaveAPI(claveAPI.trimmingCharacters(in: .whitespaces))
                        claveGuardada = true
                    }
                    .disabled(claveGuardada)
                    .onChange(of: claveAPI) { claveGuardada = false }
                } header: {
                    Text("Clave API de Anthropic")
                } footer: {
                    Text("Se guarda cifrada en el llavero del dispositivo. Necesaria para el análisis de fotos y la generación de recetas.")
                }

                Section {
                    Button("Borrar todo el historial de comidas", role: .destructive) {
                        confirmarBorrado = true
                    }
                }
            }
            .navigationTitle("Ajustes")
            .confirmationDialog("¿Borrar todo el historial?", isPresented: $confirmarBorrado,
                                titleVisibility: .visible) {
                Button("Borrar historial", role: .destructive) { borrarHistorial() }
            } message: {
                Text("Se eliminarán todos los registros de comidas. Tu perfil y recetas se conservan.")
            }
        }
    }

    private var sexoBinding: Binding<Sexo> {
        Binding(get: { perfil.sexoEnum }, set: { perfil.sexo = $0.rawValue })
    }

    private var actividadBinding: Binding<NivelActividad> {
        Binding(get: { perfil.actividadEnum }, set: { perfil.nivelActividad = $0.rawValue })
    }

    private func borrarHistorial() {
        try? contexto.delete(model: FoodEntry.self)
    }
}
