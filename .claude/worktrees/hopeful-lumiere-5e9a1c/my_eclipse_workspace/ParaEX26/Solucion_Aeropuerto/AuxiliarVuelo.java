package aeropuerto;

/**
 * CLASE AuxiliarVuelo (hija de Personal)
 * - Sueldo = base + 100€ por cada idioma
 */
public class AuxiliarVuelo extends Personal {
    
    private int numIdiomas;
    
    public AuxiliarVuelo(String nombre, double sueldoBase, int numIdiomas) {
        super(nombre, sueldoBase);
        this.numIdiomas = numIdiomas;
        System.out.printf("Idiomas: %d. Salario Base: %.2f%n", 
            this.numIdiomas, this.sueldoBase);
    }
    
    @Override
    public void calcularSueldo() {
        // 100€ por cada idioma
        double extras = 100.0 * numIdiomas;
        double sueldoTotal = this.sueldoBase + extras;
        
        System.out.printf("El salario total de %s (%s) es de %.2f%n", 
            this.nombre, this.codigo, sueldoTotal);
    }
    
    public int getNumIdiomas() {
        return this.numIdiomas;
    }
}
