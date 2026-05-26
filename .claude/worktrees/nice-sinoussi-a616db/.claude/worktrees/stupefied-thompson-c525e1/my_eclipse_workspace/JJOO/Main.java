package JJOO;

public class Main {

	public static void main(String[] args) {
		// Jugador (nombre)
			
		// Deportes 	(nombre)
			//individuales
			//Equipo(nº personas equipo)
		// Equipos
			//(nacionalidad)
			//LISTA personas
		
		Jugador j1 = new Jugador("Alex","Rumanía");
		Jugador j2 = new Jugador("Sara","España");
		Jugador j3 = new Jugador ("Kevin","Ecuador");
		Jugador j4 = new Jugador ("Jordan","Ecuador");
		Jugador j5 = new Jugador ("Sergio","España");
		Jugador j6 = new Jugador ("Mario","Rumanía");
		
		DeporteIndividual d1 = new DeporteIndividual("Snowboard");
		DeporteEquipo d2 = new DeporteEquipo("Curling");
		DeporteEquipo d3 = new DeporteEquipo("Hockey");
		
		Equipo e1 = new Equipo ("España",d2);
		Equipo e2 = new Equipo ("Rumanía",d3);
		
		e1.anyadeJugador(j1);
		e1.anyadeJugador(j2);
		e2.anyadeJugador(j1);
		
		d1.resultado(j1,45.4);
		d1.resultado(j2,21.14);
		d1.resultado(j3,55.67);
		d1.resultado(j4,51.33);
		d1.resultado(j3,57.67);
		d1.resultado(j4,41.33);
		d1.resultado(j5, 57.67);
		d1.resultado(j6, 45.40);
		
		
		d1.obtenerPodium();
		
		}

}
