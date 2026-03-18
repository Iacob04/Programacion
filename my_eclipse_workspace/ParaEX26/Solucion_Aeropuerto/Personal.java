package aeropuerto;

/**
 * CLASE ABSTRACTA Personal
 * - Clase padre de Piloto y AuxiliarVuelo
 * - El codigo se genera automaticamente con formato PER-999
 */
public abstract class Personal {
    
    protected String codigo;
    protected String nombre;
    protected double sueldoBase;
    
    protected static int contadorPersonal = 0;
    
    public Personal(String nombre, double sueldoBase) {
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
        contadorPersonal++;
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
        String codigo = String.valueOf(contadorPersonal);
        for(int i = codigo.length(); i < 3; i++) {
            codigo = "0" + codigo;
        }
        codigo = "PER-" + codigo;
        return codigo;
    }
    
    public abstract void calcularSueldo();
}
