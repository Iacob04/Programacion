package RA5;

import java.io.BufferedReader;
import java.io.FileReader;

public class LigaDeFutbol {

	public static void main(String[] args) {
		String ficheroEquipos = "equipos.txt";
        String ficheroJugadores = "jugadores.txt";

	}
	
	public static void leerFichero(String ficheroEqipos, String ficheroJugadores) {
		
	
		try (BufferedReader lec = new BufferedReader(new FileReader(ficheroEqipos))) {
		    String linea;
		    while ((linea = lec.readLine()) != null) {   // null = fin
		        System.out.println(linea);
		    	}
			} catch (Exception e) { System.out.println(e.getMessage()); 
		}
	}

}
