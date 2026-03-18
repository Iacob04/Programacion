package festival;

/**
 * CLASE Cantante (hija de Artista)
 * - Hereda codigo, nombre y sueldoBase de Artista
 * - Anade el atributo estiloMusical
 * - Implementa el metodo abstracto calcularSueldo()
 */
public class Cantante extends Artista {
    
    // Atributo especifico de Cantante
    private String estiloMusical;
    
    /**
     * Constructor de Cantante
     * Llama al constructor padre con super() y luego muestra info especifica
     * @param nombre Nombre artistico
     * @param sueldoBase Sueldo base
     * @param estiloMusical Estilo (Sevillanas, Rumba, Pop, Rock, etc.)
     */
    public Cantante(String nombre, double sueldoBase, String estiloMusical) {
        // Llamamos al constructor de la clase padre (Artista)
        super(nombre, sueldoBase);
        
        this.estiloMusical = estiloMusical;
        
        // Mostramos la info especifica del cantante
        // %s para String, %.2f para double con 2 decimales, %n para salto de linea
        System.out.printf("Estilo: %s. Salario Base: %.2f%n", this.estiloMusical, this.sueldoBase);
    }
    
    /**
     * Implementacion del metodo abstracto calcularSueldo()
     * Los cantantes cobran:
     * - Sueldo base + 300€ si su estilo es "Sevillanas"
     * - Solo sueldo base en caso contrario
     */
    @Override
    public void calcularSueldo() {
        double extras = 0;
        
        // Comparamos ignorando mayusculas/minusculas
        if(this.estiloMusical.equalsIgnoreCase("Sevillanas")) {
            extras = 300.0;
        }
        
        double sueldoTotal = this.sueldoBase + extras;
        
        // Mostramos el resultado formateado
        System.out.printf("El salario total de %s (%s) es de %.2f%n", 
            this.nombre, this.codigo, sueldoTotal);
    }
    
    // Getter para el estilo musical
    public String getEstiloMusical() {
        return this.estiloMusical;
    }
}
