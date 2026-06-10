import SwiftUI
import SwiftData

/// Entrada rápida manual, para cuando no hay foto ni código de barras.
struct ManualEntryView: View {
    @Environment(\.dismiss) private var cerrar
    @Environment(\.modelContext) private var contexto

    @State private var nombre = ""
    @State private var caloriasTexto = ""
    @State private var gramosTexto = ""
    @State private var proteinasTexto = ""
    @State private var carbohidratosTexto = ""
    @State private var grasasTexto = ""

    private func numero(_ texto: String) -> Double {
        Double(texto.replacingOccurrences(of: ",", with: ".")) ?? 0
    }

    var body: some View {
        NavigationStack {
            Form {
                Section("Alimento") {
                    TextField("Nombre (ej.: tostada con aguacate)", text: $nombre)
                    HStack {
                        TextField("Calorías", text: $caloriasTexto)
                            .keyboardType(.decimalPad)
                        Text("kcal").foregroundStyle(.secondary)
                    }
                    HStack {
                        TextField("Cantidad (opcional)", text: $gramosTexto)
                            .keyboardType(.decimalPad)
                        Text("g").foregroundStyle(.secondary)
                    }
                }
                Section("Macros (opcional)") {
                    HStack {
                        TextField("Proteínas", text: $proteinasTexto).keyboardType(.decimalPad)
                        Text("g").foregroundStyle(.secondary)
                    }
                    HStack {
                        TextField("Carbohidratos", text: $carbohidratosTexto).keyboardType(.decimalPad)
                        Text("g").foregroundStyle(.secondary)
                    }
                    HStack {
                        TextField("Grasas", text: $grasasTexto).keyboardType(.decimalPad)
                        Text("g").foregroundStyle(.secondary)
                    }
                }
                Section {
                    Button("Añadir al diario") {
                        contexto.insert(FoodEntry(
                            nombre: nombre,
                            calorias: numero(caloriasTexto),
                            proteinas: numero(proteinasTexto),
                            carbohidratos: numero(carbohidratosTexto),
                            grasas: numero(grasasTexto),
                            gramos: numero(gramosTexto),
                            origen: "manual"
                        ))
                        cerrar()
                    }
                    .frame(maxWidth: .infinity)
                    .bold()
                    .disabled(nombre.isEmpty || numero(caloriasTexto) <= 0)
                }
            }
            .navigationTitle("Añadir manualmente")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancelar") { cerrar() }
                }
            }
        }
    }
}
