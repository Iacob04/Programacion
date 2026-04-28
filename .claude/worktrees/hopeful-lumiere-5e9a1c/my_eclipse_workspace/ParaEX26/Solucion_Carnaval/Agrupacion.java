package carnaval;

public abstract class Agrupacion {
    protected String codigo;
    protected String nombre;
    protected double puntuacionBase;
    
    protected static int contadorAgrupaciones = 0;
    
    public Agrupacion(String nombre, double puntuacionBase) {
        this.nombre = nombre;
        this.puntuacionBase = puntuacionBase;
        contadorAgrupaciones++;
        this.codigo = obtenerCodigo();
        System.out.printf("%s. Codigo %s. ", this.nombre, this.codigo);
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public static String obtenerCodigo() {
        String codigo = String.valueOf(contadorAgrupaciones);
        for(int i = codigo.length(); i < 3; i++) {
            codigo = "0" + codigo;
        }
        codigo = "AGR-" + codigo;
        return codigo;
    }
    
    public abstract void calcularPuntuacion();
}
