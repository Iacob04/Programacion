package JJOO;

public class Jugador {
	
	private String nombre;
	private String nacionalidad;
	
	public Jugador (String nombre,String nacionalidad) {
		this.nombre=nombre;
		this.nacionalidad=nacionalidad;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
}
