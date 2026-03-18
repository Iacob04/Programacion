package empresa;

public class Programadores extends Empleados{
	
	public boolean sabeJava;
	public boolean sabePython;
	
	
	
	public Programadores(String nombrecompleto, int codigo, double sueldobase, boolean java, boolean python) {
		super(nombrecompleto, codigo, sueldobase);
		
		this.sabeJava = java;
		this.sabePython = python;
		
	}
	
	public void mostrarProgramador() {
	
		if(sabeJava == true && sabePython == false) {
		System.out.println(getNombre() + "."+"Código "+ "EMP-"+ getCodigo() +"."+ " Lenguaje de Programación: " + "Java"
				+". "+ "Sueldo base: "+ getSalariobase()+"€");
		}
		else if(sabeJava == false && sabePython == true) {
			System.out.println(getNombre() + "."+"Código "+ "EMP-" +getCodigo() +"."+ " Lenguaje de Programación: " + "Python"
					+". "+ "Sueldo base: "+ getSalariobase()+"€");
		}
		else if(sabeJava == true && sabePython == true) {
			System.out.println(getNombre() + "."+"Código "+  "EMP-"+getCodigo() +"."+ " Lenguaje de Programación: " + "Java y Python"
					+". "+ "Sueldo base: "+ getSalariobase()+"€");
		}
		else {
			System.out.println(getNombre() + "."+"Código "+ "EMP-" +getCodigo() +"."+ " Lenguaje de Programación: " + "Ninguno"
					+". "+ "Sueldo base: "+ getSalariobase()+"€");
		}
		
	}
	
	
	
	}
	
	

