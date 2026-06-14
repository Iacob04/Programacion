package RA5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LigaDeFutbol {

	public static void main(String[] args) {
		String ficheroEquipos = "C:\\Users\\alexi\\Programacion\\my_eclipse_workspace\\Extraordinaria\\src\\RA5\\equipos.txt";
        String ficheroJugadores = "C:\\Users\\alexi\\Programacion\\my_eclipse_workspace\\Extraordinaria\\src\\RA5\\jugadores.txt";
        leerFichero(ficheroEquipos, ficheroJugadores);
        Jugador alex = new Jugador("Atletico Madrid", "Alexandru Iacob");
        alex.mostrarJugador();
        Jugador.guardarJugadoresConEquipo(ficheroEquipos, ficheroJugadores);
        Jugador.leerJugador();

	}
	
	public static void leerFichero(String ficheroEqipos, String ficheroJugadores) {
		
		HashMap<Integer, String> equipos = new HashMap<>();
		HashMap<Integer, ArrayList<String>> jugadoresEquipo = new HashMap<>();
		ArrayList<String> sinEquipo = new ArrayList<>();
	
		try (BufferedReader lec = new BufferedReader(new FileReader(ficheroEqipos))) {
		    String linea;
		    while ((linea = lec.readLine()) != null) {   
		    	 int pos = linea.indexOf(" ");
		        int clave = Integer.parseInt(linea.substring(0,pos));
		        String club = linea.substring(pos+1);
		        equipos.put(clave, club);
		    	}
			} catch (Exception e) { System.out.println(e.getMessage()); 
		}
		
		try (BufferedReader lec = new BufferedReader(new FileReader(ficheroJugadores))) {
		    String linea;
		    while ((linea = lec.readLine()) != null) {

		    	int pos = linea.indexOf(" ");
		        int clave = Integer.parseInt(linea.substring(0,pos));
		        String jugador = linea.substring(pos+1);

		        if (equipos.containsKey(clave)) {

		            if (!jugadoresEquipo.containsKey(clave)) {
		                jugadoresEquipo.put(clave, new ArrayList<>());
		            }

		            jugadoresEquipo.get(clave).add(jugador);

		        } else {
		            sinEquipo.add(jugador);
		        }
		    }
			} catch (Exception e) { System.out.println(e.getMessage()); 
		}
		
		for (Integer clave : equipos.keySet()) {

		    System.out.println(equipos.get(clave));

		    if (!jugadoresEquipo.containsKey(clave)
		            || jugadoresEquipo.get(clave).isEmpty()) {

		        System.out.println("- Sin jugadores");

		    } else {

		        for (String jugador : jugadoresEquipo.get(clave)) {
		            System.out.println("- " + jugador);
		        }

		    }
		}

		System.out.println("Jugadores sin equipo");

		for (String jugador : sinEquipo) {
		    System.out.println("- " + jugador);
		}
	}
	
	

	

}
