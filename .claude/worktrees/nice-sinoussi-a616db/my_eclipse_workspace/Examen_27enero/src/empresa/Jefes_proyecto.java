package empresa;

public class Jefes_proyecto extends Empleados{
	
	public Programadores programador;
	
	public Jefes_proyecto (String nombrecompleto, int codigo, double salariobase) {
		
		super(nombrecompleto,codigo,salariobase);
		
	}
	public void mostrarJefes() {
	
		
		System.out.println(getNombre() + "."+"Código "+ "EMP-"+getCodigo() +"."+ " Jefe de Proyecto "
		+". "+ "Salario base: "+ getSalariobase()+"€");
		
		
	}

}
