package festival;

/**
 * CLASE GrupoMusica (hija de Artista)
 * - Hereda codigo, nombre y sueldoBase de Artista
 * - Anade el atributo numIntegrantes
 * - Implementa el metodo abstracto calcularSueldo()
 */
public class GrupoMusica extends Artista {
    
    // Atributo especifico de GrupoMusica
    private int numIntegrantes;
    
    /**
     * Constructor de GrupoMusica
     * @param nombre Nombre del grupo
     * @param sueldoBase Sueldo base del grupo
     * @param numIntegrantes Numero de integrantes
     */
    public GrupoMusica(String nombre, double sueldoBase, int numIntegrantes) {
        // Llamamos al constructor de la clase padre
        super(nombre, sueldoBase);
        
        this.numIntegrantes = numIntegrantes;
        
        // Mostramos la info especifica del grupo
        System.out.printf("Grupo de %d integrantes. Salario Base: %.2f%n", 
            this.numIntegrantes, this.sueldoBase);
    }
    
    /**
     * Implementacion del metodo abstracto calcularSueldo()
     * Los grupos cobran:
     * - Sueldo base + 150€ por cada integrante
     */
    @Override
    public void calcularSueldo() {
        // Calculamos el plus: 150€ por integrante
        double extras = 150.0 * numIntegrantes;
        
        double sueldoTotal = this.sueldoBase + extras;
        
        // Mostramos el resultado formateado
        System.out.printf("El salario total de %s (%s) es de %.2f%n", 
            this.nombre, this.codigo, sueldoTotal);
    }
    
    // Getter para el numero de integrantes
    public int getNumIntegrantes() {
        return this.numIntegrantes;
    }
}
