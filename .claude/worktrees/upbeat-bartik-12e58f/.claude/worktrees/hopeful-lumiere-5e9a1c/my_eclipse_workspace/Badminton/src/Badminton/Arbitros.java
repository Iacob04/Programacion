package Badminton;

public class Arbitros extends Persona{
	
	protected int nivel;
	
	public Arbitros(int generos,String nombre, String apellido, String DNI,String nacionalidad, int nivel,int licencia) {
		super(licencia,apellido,DNI,nacionalidad,nombre,generos);
		
		this.nivel = nivel;
	}

}
