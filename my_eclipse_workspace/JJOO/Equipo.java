package JJOO;

import java.util.HashSet;

public class Equipo {

	private String nacionalidad;
	private DeporteEquipo deporte;
	private HashSet <Jugador>listaJugadores = new HashSet<>();
	
	public Equipo (String nacionalidad, DeporteEquipo deporte ) {
		this.nacionalidad=nacionalidad; 
		this.deporte=deporte;
	}
	public void anyadeJugador(Jugador j) {
		
		if(j.getNacionalidad().equals(this.nacionalidad)==false)
			System.out.printf("El jugador %s no puede participar en el equipo de %s de %s porque su nacionalidad es de %s\n",j.getNombre(),this.deporte.getNombre(),this.nacionalidad,j.getNacionalidad());
		else
			listaJugadores.add(j);
		
		
		
	}
	
}
