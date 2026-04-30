package aeropuerto;

import java.util.ArrayList;

/**
 * CLASE Vuelo
 * 
 * COLECCION ELEGIDA: ArrayList<AuxiliarVuelo>
 * - Mantiene el orden de embarque
 * - Permite duplicados (un auxiliar puede embarcar varias veces? No, controlamos)
 * - Busqueda secuencial O(n) - suficiente para listas pequenas
 * 
 * Alternativa HashSet: mejor para evitar duplicados automaticamente
 * Alternativa HashMap: mejor si buscamos mucho por codigo
 */
public class Vuelo {
    
    private String codigoVuelo;    // Ej: IB1234
    private String codigo;         // VUE-999
    private Piloto piloto;
    private ArrayList<AuxiliarVuelo> auxiliares;
    
    private static int contadorVuelos = 0;
    
    public Vuelo(String codigoVuelo, Piloto piloto) {
        this.codigoVuelo = codigoVuelo;
        this.piloto = piloto;
        this.auxiliares = new ArrayList<>();
        
        contadorVuelos++;
        this.codigo = obtenerCodigo();
        
        System.out.printf("Vuelo: %s. Codigo: %s. Piloto: %s.%n", 
            this.codigo, this.codigoVuelo, this.piloto.getNombre());
    }
    
    public static String obtenerCodigo() {
        String codigo = String.valueOf(contadorVuelos);
        for(int i = codigo.length(); i < 3; i++) {
            codigo = "0" + codigo;
        }
        codigo = "VUE-" + codigo;
        return codigo;
    }
    
    public void cambiarPiloto(Piloto nuevoPiloto) {
        this.piloto = nuevoPiloto;
        System.out.printf("El piloto del Vuelo %s ha cambiado. Ahora es %s%n", 
            this.codigo, this.piloto.getNombre());
    }
    
    /**
     * Asigna un auxiliar al vuelo
     * Controla duplicados manualmente (con HashSet seria automatico)
     */
    public void asignarAuxiliar(AuxiliarVuelo a) {
        if(auxiliares.contains(a)) {
            System.out.printf("No se puede asignar a %s al vuelo %s. Ya esta asignado.%n", 
                a.getNombre(), this.codigo);
        } else {
            auxiliares.add(a);
            System.out.printf("%s asignado al vuelo %s%n", a.getNombre(), this.codigo);
        }
    }
    
    public void eliminarAuxiliar(AuxiliarVuelo a) {
        boolean eliminado = auxiliares.remove(a);
        
        if(eliminado) {
            System.out.printf("%s eliminado del vuelo %s%n", a.getNombre(), this.codigo);
        } else {
            System.out.printf("No se puede eliminar a %s del vuelo %s. No esta asignado.%n", 
                a.getNombre(), this.codigo);
        }
    }
    
    /**
     * Busca auxiliar por codigo y devuelve posicion en lista de embarque
     */
    public int buscarAuxiliar(String codigo) {
        for(int i = 0; i < auxiliares.size(); i++) {
            if(auxiliares.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
    
    public void listarInfo() {
        System.out.printf("Vuelo %s. Codigo: %s%n", this.codigo, this.codigoVuelo);
        System.out.printf("Piloto: %s. %s%n", 
            this.piloto.getCodigo(), this.piloto.getNombre());
        System.out.printf("Auxiliares asignados (orden de embarque):%n");
        
        int posicion = 1;
        for(AuxiliarVuelo a : auxiliares) {
            System.out.printf("  %d. %s. %s%n", posicion, a.getCodigo(), a.getNombre());
            posicion++;
        }
    }
}
