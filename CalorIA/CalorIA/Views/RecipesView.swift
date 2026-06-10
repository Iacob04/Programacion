import SwiftUI
import SwiftData

/// Recetas generadas por IA según los gustos del usuario y su objetivo calórico.
struct RecipesView: View {
    let perfil: UserProfile

    @Environment(\.modelContext) private var contexto
    @Query(sort: \Recipe.creadaEl, order: .reverse) private var recetas: [Recipe]

    @State private var generando = false
    @State private var mensajeError: String?

    var body: some View {
        NavigationStack {
            List {
                if recetas.isEmpty && !generando {
                    ContentUnavailableView(
                        "Sin recetas todavía",
                        systemImage: "book",
                        description: Text("Genera recetas personalizadas según tus gustos: \(perfil.gustos.isEmpty ? "configúralos en Ajustes" : perfil.gustos.joined(separator: ", ")).")
                    )
                }

                ForEach(recetas) { receta in
                    NavigationLink {
                        RecipeDetailView(receta: receta)
                    } label: {
                        VStack(alignment: .leading, spacing: 4) {
                            Text(receta.nombre).font(.headline)
                            Text(receta.resumen)
                                .font(.caption)
                                .foregroundStyle(.secondary)
                                .lineLimit(2)
                            HStack(spacing: 12) {
                                Label("\(Int(receta.caloriasPorRacion)) kcal/ración", systemImage: "flame.fill")
                                Label("\(receta.tiempoMinutos) min", systemImage: "clock")
                            }
                            .font(.caption2)
                            .foregroundStyle(.secondary)
                        }
                        .padding(.vertical, 2)
                    }
                }
                .onDelete { indices in
                    for indice in indices { contexto.delete(recetas[indice]) }
                }
            }
            .navigationTitle("Recetas")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        generar()
                    } label: {
                        if generando {
                            ProgressView()
                        } else {
                            Label("Generar", systemImage: "sparkles")
                        }
                    }
                    .disabled(generando)
                }
            }
            .overlay {
                if generando && recetas.isEmpty {
                    VStack(spacing: 12) {
                        ProgressView()
                        Text("Creando recetas a tu medida…")
                            .font(.caption).foregroundStyle(.secondary)
                    }
                }
            }
            .alert("Error", isPresented: .constant(mensajeError != nil)) {
                Button("OK") { mensajeError = nil }
            } message: {
                Text(mensajeError ?? "")
            }
        }
    }

    private func generar() {
        generando = true
        Task {
            defer { generando = false }
            do {
                let nuevas = try await ClaudeService.generarRecetas(
                    gustos: perfil.gustos,
                    notasDieta: perfil.notasDieta,
                    objetivoDiario: perfil.objetivoDiario
                )
                for receta in nuevas {
                    contexto.insert(Recipe(
                        nombre: receta.nombre,
                        resumen: receta.resumen,
                        caloriasPorRacion: receta.caloriasPorRacion,
                        raciones: receta.raciones,
                        tiempoMinutos: receta.tiempoMinutos,
                        ingredientes: receta.ingredientes,
                        pasos: receta.pasos
                    ))
                }
            } catch {
                mensajeError = error.localizedDescription
            }
        }
    }
}

// MARK: - Detalle

struct RecipeDetailView: View {
    let receta: Recipe

    @Environment(\.modelContext) private var contexto
    @State private var anadida = false

    var body: some View {
        List {
            Section {
                Text(receta.resumen)
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
                HStack(spacing: 16) {
                    Label("\(Int(receta.caloriasPorRacion)) kcal/ración", systemImage: "flame.fill")
                    Label("\(receta.raciones) raciones", systemImage: "person.2")
                    Label("\(receta.tiempoMinutos) min", systemImage: "clock")
                }
                .font(.caption)
                .foregroundStyle(.secondary)
            }

            Section("Ingredientes") {
                ForEach(receta.ingredientes, id: \.self) { ingrediente in
                    Label(ingrediente, systemImage: "circle.fill")
                        .labelStyle(IngredienteLabelStyle())
                }
            }

            Section("Preparación") {
                ForEach(Array(receta.pasos.enumerated()), id: \.offset) { indice, paso in
                    HStack(alignment: .top, spacing: 12) {
                        Text("\(indice + 1)")
                            .font(.caption).bold()
                            .frame(width: 24, height: 24)
                            .background(Circle().fill(Color.green.opacity(0.2)))
                        Text(paso)
                    }
                    .padding(.vertical, 2)
                }
            }

            Section {
                Button {
                    contexto.insert(FoodEntry(
                        nombre: receta.nombre,
                        calorias: receta.caloriasPorRacion,
                        origen: "receta"
                    ))
                    anadida = true
                } label: {
                    Label(anadida ? "Añadida al diario de hoy" : "He comido una ración (añadir a hoy)",
                          systemImage: anadida ? "checkmark.circle.fill" : "plus.circle.fill")
                        .frame(maxWidth: .infinity)
                }
                .bold()
                .disabled(anadida)
            }
        }
        .navigationTitle(receta.nombre)
        .navigationBarTitleDisplayMode(.inline)
    }
}

private struct IngredienteLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        HStack(alignment: .firstTextBaseline, spacing: 10) {
            configuration.icon
                .font(.system(size: 6))
                .foregroundStyle(.green)
            configuration.title
        }
    }
}
