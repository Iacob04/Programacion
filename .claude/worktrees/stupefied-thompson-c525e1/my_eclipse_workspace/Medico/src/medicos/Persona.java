package medicos;

public abstract class Persona {
	
	protected String nombre;
	protected String apellidos;
	protected int edad;
	protected String DNI;
	
	protected Persona(String nombre, String apellidos) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		
	}
	protected Persona(String nombre, String apellidos,int edad, String DNI) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.DNI = DNI;
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public String getApellido() {
		
		return this.apellidos;
	}

}
