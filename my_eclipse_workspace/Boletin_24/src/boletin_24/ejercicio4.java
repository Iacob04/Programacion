package boletin_24;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ejercicio4 {

	public static void main(String[] args) {
		darLaVuelta("origen.txt", "destino.txt");
	}
	
	public static void darLaVuelta(String origen, String destino) {
		// Inicializar listaDestino (faltaba esto)
		ArrayList<String> listaDestino = new ArrayList<>();
		
		// Leo el fichero origen como una lista
		ArrayList<String> listaOrigen = leerFichero(origen);
		
		if (listaOrigen != null) {
			// Invierto el orden de las líneas (de atrás hacia adelante)
			for (int i = listaOrigen.size() - 1; i >= 0; i--) {
				String lineaInvertida = invertirContenido(listaOrigen.get(i));
				listaDestino.add(lineaInvertida);
			}
			
			// Escribo en el fichero destino
			escribirFichero(destino, listaDestino);
		}
	}
	
	public static ArrayList<String> leerFichero(String fichero) {
		ArrayList<String> lista = new ArrayList<>(); // Inicializar vacío, no null
		Path ruta = Path.of(fichero);
		try {
			// Files.readAllLines devuelve List<String>, no ArrayList
			List<String> lineas = Files.readAllLines(ruta);
			lista = new ArrayList<>(lineas); // Convertir a ArrayList
		} catch (Exception e) {
			System.out.println("Error al leer: " + e.getMessage());
			return null; // Devolver null si hay error
		}
		return lista;
	}
	
	public static String invertirContenido(String linea) {
		String invertida = "";
		// El return estaba dentro del bucle, debe estar fuera
		for (int i = 0; i < linea.length(); i++) {
			invertida = linea.charAt(i) + invertida;
		}
		return invertida; // Return fuera del for
	}
	
	public static void escribirFichero(String fichero, ArrayList<String> lista) {
		Path ruta = Path.of(fichero);
		try {
			Files.write(ruta, lista);
		} catch (Exception e) {
			System.out.println("Error al escribir: " + e.getMessage());
		}
	}
}