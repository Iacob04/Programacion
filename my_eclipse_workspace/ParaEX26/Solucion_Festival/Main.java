package festival;

/**
 * CLASE Main - Pruebas del Festival de la Feria de Sevilla
 * 
 * Este main prueba TODOS los metodos requeridos en el examen:
 * 1. Creacion de artistas (Cantantes y Grupos)
 * 2. Creacion de casetas
 * 3. Cambio de responsable
 * 4. Asignacion de artistas (con control de duplicados)
 * 5. Eliminacion de artistas
 * 6. Listado de informacion
 * 7. Busqueda de artista por codigo
 * 8. Calculo de sueldos
 */
public class Main {
    public static void main(String[] args) {
        
        // =====================================================
        // 1. CREACION DE ARTISTAS
        // =====================================================
        System.out.println("=== CREACION DE ARTISTAS ===");
        
        // Cantantes: nombre, sueldoBase, estiloMusical
        Cantante c1 = new Cantante("Paquita la del Barrio", 2500.00, "Rancheras");
        Cantante c2 = new Cantante("Los Chichos", 3500.00, "Rumba");
        Cantante c3 = new Cantante("Pasion Vega", 2800.00, "Sevillanas");
        
        // Grupos: nombre, sueldoBase, numIntegrantes
        GrupoMusica g1 = new GrupoMusica("Estopa", 5000.00, 2);
        GrupoMusica g2 = new GrupoMusica("Ketama", 4200.00, 3);
        
        System.out.println();  // Linea en blanco
        
        // =====================================================
        // 2. CREACION DE CASETAS
        // =====================================================
        System.out.println("=== CREACION DE CASETAS ===");
        
        Caseta caseta1 = new Caseta("La Feria de Paquita", c1);
        Caseta caseta2 = new Caseta("Rumba y Pasion", c2);
        Caseta caseta3 = new Caseta("Noche Flamenca", c3);
        
        System.out.println();
        
        // =====================================================
        // 3. CAMBIO DE RESPONSABLE
        // =====================================================
        System.out.println("=== CAMBIOS EN LAS CASETAS ===");
        
        // Cambiamos el responsable de la caseta 2
        caseta2.cambiarResponsable(c3);
        
        System.out.println();
        
        // =====================================================
        // 4. ASIGNACION DE ARTISTAS
        // =====================================================
        System.out.println("=== ASIGNACION DE ARTISTAS A LAS CASETAS ===");
        
        // Asignamos artistas a la caseta 2
        caseta2.asignarArtista(g1);   // Estopa
        caseta2.asignarArtista(g2);   // Ketama
        caseta2.asignarArtista(c2);   // Los Chichos
        
        // Intentamos asignar duplicado (debe mostrar error)
        caseta2.asignarArtista(g1);   // Estopa de nuevo - ERROR
        
        // Asignamos a otra caseta
        caseta1.asignarArtista(c3);   // Pasion Vega
        
        System.out.println();
        
        // =====================================================
        // 5. ELIMINACION DE ARTISTAS
        // =====================================================
        System.out.println("=== ELIMINACION DE ARTISTAS ===");
        
        // Eliminamos un artista
        caseta2.eliminarArtista(g2);   // Eliminamos a Ketama
        
        // Intentamos eliminar el mismo artista (debe mostrar error)
        caseta2.eliminarArtista(g2);   // ERROR - ya no esta
        
        System.out.println();
        
        // =====================================================
        // 6. LISTADO DE INFORMACION
        // =====================================================
        System.out.println("=== LISTADO DE INFORMACION DE UNA CASETA ===");
        
        caseta2.listarInfo();
        
        System.out.println();
        
        // =====================================================
        // 7. BUSQUEDA DE ARTISTA
        // =====================================================
        System.out.println("=== BUSQUEDA DE ARTISTA ===");
        
        // Buscamos artista que existe
        String codigoBuscar = "ART-004";  // Codigo de Estopa
        int posicion = caseta2.buscarArtista(codigoBuscar);
        
        if(posicion >= 0) {
            System.out.printf("El artista %s (%s) ocupa la posicion %d en el orden de actuacion%n", 
                g1.getNombre(), codigoBuscar, posicion + 1);
        } else {
            System.out.printf("El artista con codigo %s no esta asignado a esta caseta%n", codigoBuscar);
        }
        
        // Buscamos artista que NO existe en esta caseta
        String codigoNoExiste = "ART-005";  // Codigo de Ketama (eliminado)
        int posicion2 = caseta2.buscarArtista(codigoNoExiste);
        
        if(posicion2 >= 0) {
            System.out.printf("El artista con codigo %s ocupa la posicion %d%n", codigoNoExiste, posicion2 + 1);
        } else {
            System.out.printf("El artista con codigo %s no esta asignado a esta caseta%n", codigoNoExiste);
        }
        
        System.out.println();
        
        // =====================================================
        // 8. CALCULO DE SALARIOS
        // =====================================================
        System.out.println("=== CALCULO DE SALARIOS ===");
        
        // Cantante con plus de Sevillanas
        c3.calcularPuntuacion();   // Pasion Vega: 2800 + 300 = 3100
        
        // Cantante sin plus
        c2.calcularPuntuacion();   // Los Chichos: 3500 (sin plus)
        
        // Grupo con plus por integrantes
        g1.calcularPuntuacion();   // Estopa: 5000 + (150 * 2) = 5300
    }
}
