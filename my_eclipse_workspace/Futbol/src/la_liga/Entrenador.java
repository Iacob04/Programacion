package la_liga;

public class Entrenador extends Persona{
	
	public Equipo equipo;
	
	public Entrenador(String nombre, Equipo equipo) {
		
		super(nombre);
		this.equipo = equipo;
		
		this.equipo.setEntrenador(this);
	}

	
	
	

}
