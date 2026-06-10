import Foundation
import SwiftData

// MARK: - Perfil del usuario

@Model
final class UserProfile {
    var nombre: String
    var sexo: String            // Sexo.rawValue
    var edad: Int
    var alturaCm: Double
    var pesoKg: Double
    var nivelActividad: String  // NivelActividad.rawValue
    var deficitDiario: Int      // kcal que se restan al mantenimiento
    var gustos: [String]
    var notasDieta: String      // alergias, restricciones, texto libre
    var creadoEl: Date

    init(nombre: String,
         sexo: Sexo,
         edad: Int,
         alturaCm: Double,
         pesoKg: Double,
         nivelActividad: NivelActividad,
         deficitDiario: Int,
         gustos: [String],
         notasDieta: String) {
        self.nombre = nombre
        self.sexo = sexo.rawValue
        self.edad = edad
        self.alturaCm = alturaCm
        self.pesoKg = pesoKg
        self.nivelActividad = nivelActividad.rawValue
        self.deficitDiario = deficitDiario
        self.gustos = gustos
        self.notasDieta = notasDieta
        self.creadoEl = .now
    }

    var sexoEnum: Sexo { Sexo(rawValue: sexo) ?? .masculino }
    var actividadEnum: NivelActividad { NivelActividad(rawValue: nivelActividad) ?? .sedentario }

    /// Calorías de mantenimiento (TDEE) según Mifflin-St Jeor.
    var mantenimiento: Double {
        CalorieCalculator.tdee(sexo: sexoEnum, edad: edad, alturaCm: alturaCm,
                               pesoKg: pesoKg, actividad: actividadEnum)
    }

    /// Objetivo diario = mantenimiento − déficit elegido (mínimo de seguridad 1200 kcal).
    var objetivoDiario: Double {
        max(mantenimiento - Double(deficitDiario), 1200)
    }
}

// MARK: - Registro de comida

@Model
final class FoodEntry {
    var nombre: String
    var calorias: Double
    var proteinas: Double
    var carbohidratos: Double
    var grasas: Double
    var gramos: Double
    var fecha: Date
    var origen: String   // "foto", "codigo", "manual", "receta"

    init(nombre: String, calorias: Double, proteinas: Double = 0,
         carbohidratos: Double = 0, grasas: Double = 0,
         gramos: Double = 0, fecha: Date = .now, origen: String) {
        self.nombre = nombre
        self.calorias = calorias
        self.proteinas = proteinas
        self.carbohidratos = carbohidratos
        self.grasas = grasas
        self.gramos = gramos
        self.fecha = fecha
        self.origen = origen
    }
}

// MARK: - Receta

@Model
final class Recipe {
    var nombre: String
    var resumen: String
    var caloriasPorRacion: Double
    var raciones: Int
    var tiempoMinutos: Int
    var ingredientes: [String]
    var pasos: [String]
    var creadaEl: Date

    init(nombre: String, resumen: String, caloriasPorRacion: Double, raciones: Int,
         tiempoMinutos: Int, ingredientes: [String], pasos: [String]) {
        self.nombre = nombre
        self.resumen = resumen
        self.caloriasPorRacion = caloriasPorRacion
        self.raciones = raciones
        self.tiempoMinutos = tiempoMinutos
        self.ingredientes = ingredientes
        self.pasos = pasos
        self.creadaEl = .now
    }
}
