import Foundation
import Security

/// Guarda la clave de la API de Claude en el llavero del sistema
/// (más seguro que UserDefaults: no se incluye en copias de seguridad sin cifrar).
enum KeychainHelper {
    private static let servicio = "com.alexiacob.caloria"
    private static let cuenta = "anthropic-api-key"

    static func guardarClaveAPI(_ clave: String) {
        let datos = Data(clave.utf8)
        let consulta: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrService as String: servicio,
            kSecAttrAccount as String: cuenta
        ]
        SecItemDelete(consulta as CFDictionary)
        guard !clave.isEmpty else { return }
        var nuevo = consulta
        nuevo[kSecValueData as String] = datos
        SecItemAdd(nuevo as CFDictionary, nil)
    }

    static func leerClaveAPI() -> String? {
        let consulta: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrService as String: servicio,
            kSecAttrAccount as String: cuenta,
            kSecReturnData as String: true,
            kSecMatchLimit as String: kSecMatchLimitOne
        ]
        var resultado: AnyObject?
        guard SecItemCopyMatching(consulta as CFDictionary, &resultado) == errSecSuccess,
              let datos = resultado as? Data else { return nil }
        return String(data: datos, encoding: .utf8)
    }
}
