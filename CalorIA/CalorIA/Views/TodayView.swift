import SwiftUI
import SwiftData

/// Diario de hoy. Los registros antiguos se conservan para la gráfica semanal,
/// pero esta vista solo enseña el día actual: el contador "se reinicia" cada día.
struct TodayView: View {
    let perfil: UserProfile

    @Environment(\.modelContext) private var contexto
    @Query(sort: \FoodEntry.fecha, order: .reverse) private var todasLasEntradas: [FoodEntry]

    @State private var mostrarFoto = false
    @State private var mostrarEscaner = false
    @State private var mostrarManual = false

    private var entradasDeHoy: [FoodEntry] {
        todasLasEntradas.filter { Calendar.current.isDateInToday($0.fecha) }
    }

    private var consumido: Double { entradasDeHoy.reduce(0) { $0 + $1.calorias } }
    private var objetivo: Double { perfil.objetivoDiario }
    private var restante: Double { objetivo - consumido }
    private var superado: Bool { restante < 0 }

    var body: some View {
        NavigationStack {
            List {
                Section {
                    resumenDiario
                }

                Section("Comidas de hoy") {
                    if entradasDeHoy.isEmpty {
                        ContentUnavailableView(
                            "Sin registros hoy",
                            systemImage: "fork.knife",
                            description: Text("Haz una foto de tu plato o escanea un producto para empezar.")
                        )
                    } else {
                        ForEach(entradasDeHoy) { entrada in
                            filaEntrada(entrada)
                        }
                        .onDelete(perform: borrarEntradas)
                    }
                }
            }
            .navigationTitle("Hoy")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Menu {
                        Button { mostrarFoto = true } label: {
                            Label("Foto del plato", systemImage: "camera.fill")
                        }
                        Button { mostrarEscaner = true } label: {
                            Label("Escanear producto", systemImage: "barcode.viewfinder")
                        }
                        Button { mostrarManual = true } label: {
                            Label("Añadir manualmente", systemImage: "square.and.pencil")
                        }
                    } label: {
                        Image(systemName: "plus.circle.fill").font(.title2)
                    }
                }
            }
            .sheet(isPresented: $mostrarFoto) { PhotoAnalysisView() }
            .sheet(isPresented: $mostrarEscaner) { BarcodeScanFlowView() }
            .sheet(isPresented: $mostrarManual) { ManualEntryView() }
        }
    }

    // MARK: Resumen

    private var resumenDiario: some View {
        VStack(spacing: 12) {
            HStack {
                VStack(alignment: .leading) {
                    Text("Consumido").font(.caption).foregroundStyle(.secondary)
                    Text("\(Int(consumido)) kcal").font(.title2).bold()
                }
                Spacer()
                VStack(alignment: .trailing) {
                    Text(superado ? "Excedido" : "Restante")
                        .font(.caption).foregroundStyle(.secondary)
                    Text("\(Int(abs(restante))) kcal")
                        .font(.title2).bold()
                        .foregroundStyle(superado ? .red : .green)
                }
            }

            ProgressView(value: min(consumido, objetivo), total: objetivo)
                .tint(superado ? .red : .green)

            HStack {
                Text("Objetivo: \(Int(objetivo)) kcal")
                    .font(.caption).foregroundStyle(.secondary)
                Spacer()
                if superado {
                    Label("Límite superado", systemImage: "exclamationmark.triangle.fill")
                        .font(.caption).foregroundStyle(.red)
                }
            }

            HStack(spacing: 16) {
                macro("P", entradasDeHoy.reduce(0) { $0 + $1.proteinas }, .blue)
                macro("C", entradasDeHoy.reduce(0) { $0 + $1.carbohidratos }, .orange)
                macro("G", entradasDeHoy.reduce(0) { $0 + $1.grasas }, .purple)
            }
        }
        .padding(.vertical, 4)
    }

    private func macro(_ etiqueta: String, _ valor: Double, _ color: Color) -> some View {
        HStack(spacing: 4) {
            Text(etiqueta).bold().foregroundStyle(color)
            Text("\(Int(valor)) g").font(.caption)
        }
    }

    // MARK: Filas

    private func filaEntrada(_ entrada: FoodEntry) -> some View {
        HStack {
            Image(systemName: icono(origen: entrada.origen))
                .foregroundStyle(.secondary)
            VStack(alignment: .leading) {
                Text(entrada.nombre)
                HStack(spacing: 8) {
                    if entrada.gramos > 0 {
                        Text("\(Int(entrada.gramos)) g")
                    }
                    Text(entrada.fecha, style: .time)
                }
                .font(.caption)
                .foregroundStyle(.secondary)
            }
            Spacer()
            Text("\(Int(entrada.calorias)) kcal").bold()
        }
    }

    private func icono(origen: String) -> String {
        switch origen {
        case "foto": "camera.fill"
        case "codigo": "barcode"
        case "receta": "book.fill"
        default: "square.and.pencil"
        }
    }

    private func borrarEntradas(en indices: IndexSet) {
        for indice in indices {
            contexto.delete(entradasDeHoy[indice])
        }
    }
}
