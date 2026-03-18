package carnaval;

public class Chirigota extends Agrupacion {
    
    private int numComponentes;
    
    public Chirigota(String nombre, double puntuacionBase, int numComponentes) {
        super(nombre, puntuacionBase);
        this.numComponentes = numComponentes;
        System.out.printf("%d componentes. Puntuacion Base: %.2f\n", this.numComponentes, this.puntuacionBase);
    }
    
    public void calcularPuntuacion() {
        double extras = 0.5 * numComponentes;
        System.out.printf("La puntuacion final de %s (%s) es de %.2f\n", this.nombre, this.codigo, this.puntuacionBase + extras);
    }
}
