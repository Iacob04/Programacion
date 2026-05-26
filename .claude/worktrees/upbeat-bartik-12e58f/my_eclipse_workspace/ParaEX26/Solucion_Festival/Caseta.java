package festival;

import java.util.ArrayList;
// import java.util.HashSet;  // Descomenta si prefieres usar HashSet
// import java.util.HashMap;  // Descomenta si prefieres usar HashMap

/**
 * CLASE Caseta
 * 
 * ELECCION DE COLECCION:
 * - ArrayList: Mantiene el ORDEN de insercion (orden de actuacion)
 *              Permite duplicados (mismo artista varias veces)
 *              Busqueda lenta: O(n)
 * 
 * - HashSet:   NO permite duplicados automaticamente
 *              Busqueda rapida: O(1)
 *              NO mantiene orden
 * 
 * - HashMap:   Busqueda por clave (codigo) muy rapida: O(1)
 *              Permite asociar datos adicionales
 *              NO mantiene orden
 * 
 * Para este ejercicio usamos ArrayList porque necesitamos mantener
 * el orden de actuacion de los artistas.
 */
public class Caseta {
    
    private String nombre;
    private String codigo;
    private Artista responsable;
    
    // COLECCION: ArrayList mantiene el orden de actuacion
    private ArrayList<Artista> artistas;
    
    // Contador estatico para generar codigos CAS-999
    private static int contadorCasetas = 0;
    
    /**
     * Constructor de Caseta
     * @param nombre Nombre de la caseta
     * @param responsable Artista responsable de la caseta
     */
    public Caseta(String nombre, Artista responsable) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.artistas = new ArrayList<>();  // Inicializamos la coleccion
        
        contadorCasetas++;
        this.codigo = obtenerCodigo();
        
        System.out.printf("Caseta: %s. %s. Responsable: %s.%n", 
            this.codigo, this.nombre, this.responsable.getNombre());
    }
    
    /**
     * Genera el codigo con formato CAS-999
     */
    public static String obtenerCodigo() {
        String codigo = String.valueOf(contadorCasetas);
        for(int i = codigo.length(); i < 3; i++) {
            codigo = "0" + codigo;
        }
        codigo = "CAS-" + codigo;
        return codigo;
    }
    
    /**
     * Cambia el responsable de la caseta
     * @param nuevoResponsable Nuevo artista responsable
     */
    public void cambiarResponsable(Artista nuevoResponsable) {
        this.responsable = nuevoResponsable;
        System.out.printf("El responsable de la Caseta %s ha cambiado. Ahora es %s%n", 
            this.codigo, this.responsable.getNombre());
    }
    
    /**
     * Asigna un artista a la caseta
     * Con ArrayList: simplemente anadimos al final
     * Con HashSet: add() devuelve false si ya existe
     * 
     * @param a Artista a asignar
     */
    public void asignarArtista(Artista a) {
        // Con ArrayList: comprobamos manualmente si ya existe
        // Con HashSet: artistas.add(a) devuelve false si ya existe
        if(artistas.contains(a)) {
            System.out.printf("No se puede asignar a %s a la caseta %s. Ya esta asignado.%n", 
                a.getNombre(), this.codigo);
        } else {
            artistas.add(a);
            System.out.printf("%s asignado a la caseta %s%n", a.getNombre(), this.codigo);
        }
    }
    
    /**
     * Elimina un artista de la caseta
     * @param a Artista a eliminar
     */
    public void eliminarArtista(Artista a) {
        // remove() devuelve true si se elimino, false si no existia
        boolean eliminado = artistas.remove(a);
        
        if(eliminado) {
            System.out.printf("%s eliminado de la caseta %s%n", a.getNombre(), this.codigo);
        } else {
            System.out.printf("No se puede eliminar a %s de la caseta %s. No esta asignado.%n", 
                a.getNombre(), this.codigo);
        }
    }
    
    /**
     * Busca un artista por su codigo y devuelve su posicion
     * @param codigo Codigo del artista a buscar
     * @return Posicion en el orden de actuacion, o -1 si no esta
     */
    public int buscarArtista(String codigo) {
        // Recorremos la lista buscando el codigo
        for(int i = 0; i < artistas.size(); i++) {
            if(artistas.get(i).getCodigo().equals(codigo)) {
                return i;  // Devolvemos la posicion (empezando en 0)
            }
        }
        return -1;  // No encontrado
        
        // NOTA: Con HashMap seria mucho mas rapido:
        // return artistasMap.containsKey(codigo) ? ... : -1;
    }
    
    /**
     * Lista toda la informacion de la caseta
     */
    public void listarInfo() {
        System.out.printf("Caseta %s. %s%n", this.codigo, this.nombre);
        System.out.printf("Responsable: %s. %s%n", 
            this.responsable.getCodigo(), this.responsable.getNombre());
        System.out.printf("Artistas asignados (orden de actuacion):%n");
        
        // Recorremos con for-each (mantiene el orden en ArrayList)
        int posicion = 1;  // Empezamos a contar en 1 para mostrar
        for(Artista a : artistas) {
            System.out.printf("  %d. %s. %s%n", posicion, a.getCodigo(), a.getNombre());
            posicion++;
        }
    }
    
    // Getters
    public String getCodigo() {
        return this.codigo;
    }
    
    public String getNombre() {
        return this.nombre;
    }
}
