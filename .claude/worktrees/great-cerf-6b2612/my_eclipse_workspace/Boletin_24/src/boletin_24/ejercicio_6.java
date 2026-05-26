package boletin_24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ejercicio_6 {

	public static void main(String[] args) {

		String fSoluciones = "/home/alumno/soluciones.txt";
		String fRespuestas = "/home/alumno/respuestas.txt";
		String fNotas = "/home/alumno/notas.txt";
		int numPreguntas = 10;

		String soluciones[] = new String[numPreguntas];
		HashMap<String, String[]> respuestas;

		soluciones = leeSoluciones(fSoluciones);
		respuestas = leeRespuestas(fRespuestas);

		grabarNotas(fNotas, soluciones, respuestas);
	}

	public static String[] leeSoluciones(String fichero) {

		Path ruta = Path.of(fichero);
		String linea = null;

		try {
			linea = Files.readString(ruta);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		String[] soluciones = linea.split(",\\s*");

		return soluciones;
	}

	public static HashMap<String, String[]> leeRespuestas(String fichero) {

		HashMap<String, String[]> diccionario = new HashMap<>();

		try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

			String linea;

			while ((linea = lector.readLine()) != null) {

				int posicion = linea.indexOf(":");

				String alumno = linea.substring(0, posicion);
				String respuestas = linea.substring(posicion + 2);

				diccionario.put(alumno, respuestas.split(",\\s*"));
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return diccionario;
	}

	public static void grabarNotas(String fichero, String[] soluciones, HashMap<String, String[]> respuestas) {
		
		try(PrintWriter pluma = new PrintWriter(fichero)){
		
		for (Map.Entry<String, String[]> respuesta : respuestas.entrySet()) {

			System.out.print(respuesta.getKey() + ": ");
			System.out.println(calcularNota(soluciones, respuesta.getValue()));
			pluma.printf("%s: %.1f", respuesta.getKey(),calcularNota(soluciones,respuesta.getValue()));
			pluma.println();
		}
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static double calcularNota(String[] soluciones, String[] respuesta) {

		double nota = 0;

		for (int i = 0; i < soluciones.length; i++) {

			if (soluciones[i].charAt(0)== respuesta[i].charAt(0)) {
				nota += 1;
			} else {
				nota -= 0.3;
			}
		}
		if (nota < 0){
			nota = 0;
		}

		return nota;
	}
}
