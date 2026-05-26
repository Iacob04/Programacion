package boletin23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Ejercicio_1 {

	public static void main(String[] args) {
		
	
		boolean ficheroExiste = false;
		
		do {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Introduzca el nombre del archivo al que desea acceder: ");
			String nombreFichero = teclado.nextLine();
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(nombreFichero));
			int contadorLineas = 0;
			int contadorPalabra = 0;
			String linea;
			System.out.println("Introduzca la palabra a buscar: ");
			String palabra = teclado.nextLine();
			
			do {
				linea = lector.readLine();
				if (linea != null) {
					contadorLineas++;
					}
				if(linea != null && linea.contains(palabra)) {
					contadorPalabra++;
					}
			} while (linea != null);
			System.out.println("El fichero tiene "+ contadorLineas+ " lineas.");
			System.out.println("La palabra "+ palabra + " aparece "+ contadorPalabra + " veces.");
			lector.close();
			
			ficheroExiste = true;
		} catch (Exception e) {
			System.out.println("El fichero "+ nombreFichero + " no existe");
			
		}
		
		}while(ficheroExiste == false);

	}

}
