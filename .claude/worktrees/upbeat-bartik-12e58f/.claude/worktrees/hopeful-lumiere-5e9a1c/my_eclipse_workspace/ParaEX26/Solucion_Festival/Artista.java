package festival;

/**
 * CLASE ABSTRACTA Artista
 * - No se puede instanciar directamente
 * - Las clases hijas (Cantante y GrupoMusica) deben implementar el metodo calcularSueldo()
 * - El codigo se genera automaticamente con formato ART-999 usando contador estatico
 */
public abstract class Artista {
    
    // Atributos protegidos para que las clases hijas puedan acceder
    protected String codigo;
    protected String nombre;
    protected double sueldoBase;
    
    // Contador estatico: compartido por TODAS las instancias de Artista y sus hijas
    // Se usa para generar codigos secuenciales: ART-001, ART-002, etc.
    protected static int contadorArtistas = 0;
    
    /**
     * Constructor de Artista
     * @param nombre Nombre artistico del artista
     * @param sueldoBase Sueldo base del artista
     */
    public Artista(String nombre, double sueldoBase) {
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
        
        // Incrementamos el contador y generamos el codigo
        contadorArtistas++;
        this.codigo = obtenerCodigo();
        
        // Mostramos informacion basica con printf
        // %s = String, %.2f = double con 2 decimales
        System.out.printf("%s. Codigo %s. ", this.nombre, this.codigo);
    }
    
    // Getters
    public String getNombre() {
        return this.nombre;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public double getSueldoBase() {
        return this.sueldoBase;
    }
    
    /**
     * Genera el codigo con formato ART-999
     * Metodo estatico: se puede llamar sin crear instancia
     * @return String con el codigo formateado
     */
    public static String obtenerCodigo() {
        // Convertimos el contador a String
        String codigo = String.valueOf(contadorArtistas);
        
        // Rellenamos con ceros a la izquierda hasta tener 3 digitos
        // Ejemplo: si contador = 5 -> codigo = "005"
        for(int i = codigo.length(); i < 3; i++) {
            codigo = "0" + codigo;
        }
        
        // Anadimos el prefijo
        codigo = "ART-" + codigo;
        return codigo;
    }
    
    /**
     * Metodo ABSTRACTO
     * - Debe ser implementado OBLIGATORIAMENTE por las clases hijas
     * - Cada tipo de artista calcula su sueldo de forma diferente
     */
    public abstract void calcularSueldo();
}
