package aeropuerto;

/**
 * CLASE Main - Pruebas del Aeropuerto
 */
public class Main {
    public static void main(String[] args) {
        
        // =====================================================
        // 1. CREACION DE PERSONAL
        // =====================================================
        System.out.println("=== CREACION DE PERSONAL ===");
        
        // Pilotos: nombre, sueldoBase, horasVuelo
        Piloto p1 = new Piloto("Antonio Recio", 4500.00, 3500);
        Piloto p2 = new Piloto("Berta Escobar", 4200.00, 2800);
        
        // Auxiliares: nombre, sueldoBase, numIdiomas
        AuxiliarVuelo a1 = new AuxiliarVuelo("Amador Rivas", 1800.00, 2);
        AuxiliarVuelo a2 = new AuxiliarVuelo("Maite Figueroa", 1900.00, 4);
        AuxiliarVuelo a3 = new AuxiliarVuelo("Vicente Maroto", 1700.00, 1);
        
        System.out.println();
        
        // =====================================================
        // 2. CREACION DE VUELOS
        // =====================================================
        System.out.println("=== CREACION DE VUELOS ===");
        
        Vuelo v1 = new Vuelo("IB1234", p1);
        Vuelo v2 = new Vuelo("FR4567", p2);
        Vuelo v3 = new Vuelo("UX8910", p1);
        
        System.out.println();
        
        // =====================================================
        // 3. CAMBIO DE PILOTO
        // =====================================================
        System.out.println("=== CAMBIOS EN LOS VUELOS ===");
        
        v2.cambiarPiloto(p1);
        
        System.out.println();
        
        // =====================================================
        // 4. ASIGNACION DE AUXILIARES
        // =====================================================
        System.out.println("=== ASIGNACION DE AUXILIARES A LOS VUELOS ===");
        
        v2.asignarAuxiliar(a1);   // Amador Rivas
        v2.asignarAuxiliar(a2);   // Maite Figueroa
        v2.asignarAuxiliar(a3);   // Vicente Maroto
        
        // Intentamos duplicado
        v2.asignarAuxiliar(a1);   // ERROR - ya esta
        
        // Asignamos a otro vuelo
        v1.asignarAuxiliar(a3);   // Vicente Maroto
        
        System.out.println();
        
        // =====================================================
        // 5. ELIMINACION DE AUXILIARES
        // =====================================================
        System.out.println("=== ELIMINACION DE AUXILIARES ===");
        
        v2.eliminarAuxiliar(a2);   // Eliminamos a Maite
        v2.eliminarAuxiliar(a2);   // ERROR - ya no esta
        
        System.out.println();
        
        // =====================================================
        // 6. LISTADO DE INFORMACION
        // =====================================================
        System.out.println("=== LISTADO DE INFORMACION DE UN VUELO ===");
        
        v2.listarInfo();
        
        System.out.println();
        
        // =====================================================
        // 7. BUSQUEDA DE AUXILIAR
        // =====================================================
        System.out.println("=== BUSQUEDA DE AUXILIAR ===");
        
        // Buscamos auxiliar que existe
        String codigoBuscar = "PER-003";  // Amador Rivas
        int posicion = v2.buscarAuxiliar(codigoBuscar);
        
        if(posicion >= 0) {
            System.out.printf("El auxiliar %s (%s) ocupa la posicion %d en la lista de embarque%n", 
                a1.getNombre(), codigoBuscar, posicion + 1);
        } else {
            System.out.printf("El auxiliar con codigo %s no esta asignado a este vuelo%n", codigoBuscar);
        }
        
        // Buscamos auxiliar que NO existe
        String codigoNoExiste = "PER-004";  // Maite (eliminada)
        int posicion2 = v2.buscarAuxiliar(codigoNoExiste);
        
        if(posicion2 >= 0) {
            System.out.printf("El auxiliar con codigo %s ocupa la posicion %d%n", codigoNoExiste, posicion2 + 1);
        } else {
            System.out.printf("El auxiliar con codigo %s no esta asignada a este vuelo%n", codigoNoExiste);
        }
        
        System.out.println();
        
        // =====================================================
        // 8. CALCULO DE SALARIOS
        // =====================================================
        System.out.println("=== CALCULO DE SALARIOS ===");
        
        // Piloto: 4500 + (3500/100)*50 = 4500 + 1750 = 6250
        p1.calcularSueldo();
        
        // Auxiliar: 1800 + (2*100) = 2000
        a1.calcularSueldo();
        
        // Auxiliar: 1900 + (4*100) = 2300
        a2.calcularSueldo();
    }
}
