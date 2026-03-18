package carnaval;

public class Cupletista extends Agrupacion {
    
    private String tipoHumor;
    
    public Cupletista(String nombre, double puntuacionBase, String tipoHumor) {
        super(nombre, puntuacionBase);
        this.tipoHumor = tipoHumor;
        System.out.printf("Humor: %s. Puntuacion Base: %.2f\n", this.tipoHumor, this.puntuacionBase);
    }
    
    public void calcularPuntuacion() {
        double extras = 0;
        if(this.tipoHumor.equalsIgnoreCase("Grosero")) {
            extras = 2.0;
        }
        System.out.printf("La puntuacion final de %s (%s) es de %.2f\n", this.nombre, this.codigo, this.puntuacionBase + extras);
    }
}
