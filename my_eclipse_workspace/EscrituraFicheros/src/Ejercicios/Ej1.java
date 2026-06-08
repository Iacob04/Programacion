package Ejercicios;

import java.io.FileWriter;
import java.util.Scanner;
public class Ej1 {
	public static void main(String[] args) {
		
		escritura1();
	}
		public static void escritura1() {
			//intentar {
				// si el fichero no existe se crea. Si existe se borra su contenido antes de
				// escribir
			try {
			System.out.println("Introduce un numero : ");
			Scanner teclado =  new Scanner(System.in);
			int numero=teclado.nextInt();
				FileWriter escritor = new FileWriter("/home/alumno/Tabla-" + numero + " de multiplicar");
			for(int i =0; i<=10;i++) {
				escritor.write(numero + " x " + i + " = " + numero*i + " \n");
			}

				System.out.println("Archivo escrito correctamente.");
				escritor.close();
			} catch (Exception e) {
				System.err.println("Error al escribir el archivo: " + e.getMessage());
			}
			
		}

	

}
