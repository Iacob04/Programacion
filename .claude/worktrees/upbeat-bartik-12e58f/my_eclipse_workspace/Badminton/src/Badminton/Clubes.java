package Badminton;

import java.util.ArrayList;
import java.util.HashSet;




public class Clubes {
	
	protected String nombreClub;
	public Entrenadores entrenador;
	public Jugadores jugador;
	
	private static ArrayList<Clubes> listaClubes = new ArrayList<>();
	private HashSet<Jugadores> listaJugadores = new HashSet<>();
	private HashSet<Entrenadores> listaEntrenadores = new HashSet<>();
	
	
	
	protected Clubes (String nombreClub) {
		
		this.nombreClub = nombreClub;
		listaClubes.add(this);
	}
	public void anyadeJugador(Jugadores jugador) {   
		this.listaJugadores.add(jugador);
	}
	public void anyadeEntrenador(Entrenadores entrenador) {   
		this.listaEntrenadores.add(entrenador);
	}
	
	public void anyadeClub(Clubes club) {
		listaClubes.add(club);
	}
	
	public static void listarClubes() {
		System.out.println("    Clubes de Bádminton ");
		System.out.println("---------------------------");
		for(Clubes c : listaClubes) {
			System.out.println(" |- "+c.nombreClub);
		}
	}
	
	public void listarJugadoresDeCadaClub() {
	    System.out.println("  Club: " + this.nombreClub);
	    System.out.println();
	    System.out.println("  Jugadores:");
	    System.out.println("----------------------------------------------------------------------------------------");
	    
	    for (Jugadores j : this.listaJugadores) {
	        System.out.println("  - " + j.nombre + " " + j.apellido + 
	                          " | Categoría: " + j.Categorias() +
	                          " | Género: " + j.Genero() +
	                          " | Licencia: " + j.Licencia());
	    }
	    System.out.println();
	}
	
	public void listarEntrenadoresDeCadaClub() {
		
		System.out.println("  Club: " + this.nombreClub);
	    System.out.println();
	    System.out.println("Entrenadores:  ");
	    System.out.println("----------------------------------------------------------------------------------------");
	    
	    for(Entrenadores e : this.listaEntrenadores) {
	    	System.out.println("  - " + e.nombre + " " + e.apellido + "| Licencia: " + e.Licencia());
	    }
	    
		
	}
	
	

}
