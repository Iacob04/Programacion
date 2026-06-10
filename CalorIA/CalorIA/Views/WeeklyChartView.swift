import SwiftUI
import SwiftData
import Charts

/// Gráfica de barras de los últimos 7 días frente al objetivo diario.
/// Las barras rojas indican días en los que se superó el límite.
struct WeeklyChartView: View {
    let perfil: UserProfile

    @Query private var entradas: [FoodEntry]

    private struct DiaResumen: Identifiable {
        let id: Date
        let fecha: Date
        let total: Double
        init(fecha: Date, total: Double) {
            self.id = fecha
            self.fecha = fecha
            self.total = total
        }
    }

    private var objetivo: Double { perfil.objetivoDiario }

    private var ultimos7Dias: [DiaResumen] {
        let calendario = Calendar.current
        let hoy = calendario.startOfDay(for: .now)
        return (0..<7).reversed().compactMap { desplazamiento in
            guard let dia = calendario.date(byAdding: .day, value: -desplazamiento, to: hoy) else {
                return nil
            }
            let total = entradas
                .filter { calendario.isDate($0.fecha, inSameDayAs: dia) }
                .reduce(0) { $0 + $1.calorias }
            return DiaResumen(fecha: dia, total: total)
        }
    }

    private var diasSuperados: Int { ultimos7Dias.filter { $0.total > objetivo }.count }
    private var mediaSemanal: Double {
        let conDatos = ultimos7Dias.filter { $0.total > 0 }
        guard !conDatos.isEmpty else { return 0 }
        return conDatos.reduce(0) { $0 + $1.total } / Double(conDatos.count)
    }

    var body: some View {
        NavigationStack {
            List {
                Section("Calorías de los últimos 7 días") {
                    grafica
                        .frame(height: 260)
                        .padding(.vertical, 8)
                }

                Section("Resumen semanal") {
                    LabeledContent("Objetivo diario", value: "\(Int(objetivo)) kcal")
                    LabeledContent("Media diaria (días con registro)", value: "\(Int(mediaSemanal)) kcal")
                    LabeledContent("Días con límite superado") {
                        Text("\(diasSuperados) de 7")
                            .bold()
                            .foregroundStyle(diasSuperados > 0 ? .red : .green)
                    }
                }

                Section("Día a día") {
                    ForEach(ultimos7Dias) { dia in
                        HStack {
                            Text(dia.fecha, format: .dateTime.weekday(.wide).day())
                            Spacer()
                            Text("\(Int(dia.total)) kcal")
                                .foregroundStyle(.secondary)
                            Image(systemName: dia.total > objetivo
                                  ? "xmark.circle.fill"
                                  : (dia.total > 0 ? "checkmark.circle.fill" : "minus.circle"))
                                .foregroundStyle(dia.total > objetivo
                                                 ? .red
                                                 : (dia.total > 0 ? .green : .gray))
                        }
                    }
                }
            }
            .navigationTitle("Semana")
        }
    }

    private var grafica: some View {
        Chart {
            ForEach(ultimos7Dias) { dia in
                BarMark(
                    x: .value("Día", dia.fecha, unit: .day),
                    y: .value("Calorías", dia.total)
                )
                .foregroundStyle(dia.total > objetivo ? Color.red : Color.green)
                .cornerRadius(6)
            }

            RuleMark(y: .value("Objetivo", objetivo))
                .lineStyle(StrokeStyle(lineWidth: 2, dash: [6, 4]))
                .foregroundStyle(.orange)
                .annotation(position: .top, alignment: .trailing) {
                    Text("Objetivo \(Int(objetivo))")
                        .font(.caption2)
                        .foregroundStyle(.orange)
                }
        }
        .chartXAxis {
            AxisMarks(values: .stride(by: .day)) { _ in
                AxisValueLabel(format: .dateTime.weekday(.narrow))
            }
        }
    }
}
