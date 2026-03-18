package Badminton;

abstract class Persona {
	
	protected String nombre;
	protected String apellido;
	protected String DNI;
	public String nacionalidad;
	public int licencia;
	public int generos;
	
	
	protected Persona(int generos,String nombre, String apellido, String DNI, String nacionalidad,int licencia) {
		
		this.apellido = apellido;
		this.DNI = DNI;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.licencia = licencia;
		this.generos = generos;
	}

}
