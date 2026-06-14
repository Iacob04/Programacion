package RA5;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Jugador implements Serializable  {
	
	private String nombreEquipo;
	private String nombreJugador;

	public Jugador(String nombreEquipo, String nombreJuagador) {
		
		this.nombreEquipo = nombreEquipo;
		this.nombreJugador = nombreJuagador;
		
	}
	
	public void mostrarJugador() {
		
		System.out.printf("%s (%s)%n", nombreJugador, nombreEquipo);
		
	}
	
	public static void guardarJugadoresConEquipo(String ficheroEquipos, String ficheroJugadores) {
		
		 HashMap<Integer, String> equipos = new HashMap<>();

		    try (BufferedReader br = new BufferedReader(
		            new FileReader(ficheroEquipos))) {

		        String linea;

		        while ((linea = br.readLine()) != null) {

		        	int pos = linea.indexOf(" ");
			        int codigo = Integer.parseInt(linea.substring(0,pos));
		            String nombreEquipo = linea.substring(pos+1);

		            equipos.put(codigo, nombreEquipo);
		        }

		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }

		    try (BufferedReader br = new BufferedReader(
		            new FileReader(ficheroJugadores));

		         ObjectOutputStream oos =
		            new ObjectOutputStream(
		                new FileOutputStream("jugadores.dat"))) {

		        String linea;

		        while ((linea = br.readLine()) != null) {

		        	int pos = linea.indexOf(" ");
			        int codigo = Integer.parseInt(linea.substring(0,pos));
		            String nombreJugador = linea.substring(pos+1);

		            if (equipos.containsKey(codigo)) {

		                Jugador jugador = new Jugador(
		                        equipos.get(codigo),
		                        nombreJugador);

		                oos.writeObject(jugador);
		            }
		        }

		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
	}
	public static void leerJugador() {

	    try (ObjectInputStream ois =
	            new ObjectInputStream(
	                new FileInputStream("jugadores.dat"))) {

	        while (true) {

	            Jugador jugador =
	                    (Jugador) ois.readObject();

	            jugador.mostrarJugador();
	        }

	    } catch (EOFException e) {

	        // Fin del fichero

	    } catch (Exception e) {

	        System.out.println(e.getMessage());
	    }
	}

}

