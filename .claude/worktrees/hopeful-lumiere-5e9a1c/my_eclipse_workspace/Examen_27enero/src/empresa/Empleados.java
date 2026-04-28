package empresa;

public abstract class Empleados {
	
	protected String nombrecompleto;
    protected int codigo;
    protected double salariobase ;
    
    protected static int contadorEmpleados = 0;

    public Empleados(String nombrecompleto, int codigo, double salariobase  ) {
        this.nombrecompleto = nombrecompleto;
        this.codigo = codigo;
        this.salariobase = salariobase;
        contadorEmpleados++;
    }

    public String getNombre() {
        return nombrecompleto;
    }

    public String getCodigo() {
    	String codigoformateado = String.format("%03d", codigo);
        return codigoformateado;
    }

    public double getSalariobase() {
        return salariobase;
    }
}


