import Foundation

enum Sexo: String, CaseIterable, Identifiable {
    case masculino = "Masculino"
    case femenino = "Femenino"
    var id: String { rawValue }
}

enum NivelActividad: String, CaseIterable, Identifiable {
    case sedentario = "Sedentario"
    case ligero = "Ligero"
    case moderado = "Moderado"
    case activo = "Activo"
    case muyActivo = "Muy activo"

    var id: String { rawValue }

    var factor: Double {
        switch self {
        case .sedentario: 1.2
        case .ligero: 1.375
        case .moderado: 1.55
        case .activo: 1.725
        case .muyActivo: 1.9
        }
    }

    var descripcion: String {
        switch self {
        case .sedentario: "Poco o ningún ejercicio"
        case .ligero: "Ejercicio 1-3 días/semana"
        case .moderado: "Ejercicio 3-5 días/semana"
        case .activo: "Ejercicio 6-7 días/semana"
        case .muyActivo: "Ejercicio intenso diario o trabajo físico"
        }
    }
}

enum CalorieCalculator {

    /// Metabolismo basal con la fórmula de Mifflin-St Jeor.
    static func bmr(sexo: Sexo, edad: Int, alturaCm: Double, pesoKg: Double) -> Double {
        let base = 10 * pesoKg + 6.25 * alturaCm - 5 * Double(edad)
        return sexo == .masculino ? base + 5 : base - 161
    }

    /// Gasto energético total diario (mantenimiento).
    static func tdee(sexo: Sexo, edad: Int, alturaCm: Double, pesoKg: Double,
                     actividad: NivelActividad) -> Double {
        bmr(sexo: sexo, edad: edad, alturaCm: alturaCm, pesoKg: pesoKg) * actividad.factor
    }
}
