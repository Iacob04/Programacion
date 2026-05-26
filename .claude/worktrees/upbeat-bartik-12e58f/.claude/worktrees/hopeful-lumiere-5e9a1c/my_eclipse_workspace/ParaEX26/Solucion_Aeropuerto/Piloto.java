package aeropuerto;

/**
 * CLASE Piloto (hija de Personal)
 * - Sueldo = base + 50€ por cada 100 horas de vuelo
 */
public class Piloto extends Personal {
    
    private int horasVuelo;
    
    public Piloto(String nombre, double sueldoBase, int horasVuelo) {
        super(nombre, sueldoBase);
        this.horasVuelo = horasVuelo;
        System.out.printf("Horas de vuelo: %d. Salario Base: %.2f%n", 
            this.horasVuelo, this.sueldoBase);
    }
    
    @Override
    public void calcularSueldo() {
        // 50€ por cada 100 horas
        double extras = (horasVuelo / 100) * 50.0;
        double sueldoTotal = this.sueldoBase + extras;
        
        System.out.printf("El salario total de %s (%s) es de %.2f%n", 
            this.nombre, this.codigo, sueldoTotal);
    }
    
    public int getHorasVuelo() {
        return this.horasVuelo;
    }
}
