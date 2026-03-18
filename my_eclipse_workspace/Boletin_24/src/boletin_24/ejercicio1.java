package boletin_24;

import java.io.PrintWriter;
import java.util.Scanner;

public class ejercicio1 {

	public static void main(String[] args) {
		
		tabla();

	}
	
	public static void tabla() {
		
		System.out.println("Introduzca un número para calcular su tabla de multiplicar y guardarlo en el documento: ");
		Scanner teclado = new Scanner(System.in);
		int numero = 0;
		boolean correcto = true;
		
		
		try {
		 numero = teclado.nextInt();
		
		if(numero<1 || numero >10) {
			System.out.println("El numero tine que estar entre el 1 y el 10");
		}
		
		}catch (Exception e) {
			System.out.println("Eso no es un numero entero  !!!");
			correcto = false;
		}
		
		if(correcto = true) {
			
			String nombreFichero = ("tabla-"+String.valueOf(numero)+".txt");
		
		try(PrintWriter pluma = new PrintWriter("/home/alumno/"+ nombreFichero)) {
			
			pluma.printf("Tabla de multiplicar del numero: %d\n", numero);
				for(int i = 0; i<=10 ; i++) {
					
					int calculo = numero *i ;
					pluma.printf("%d x %d = %d\n", numero ,i ,calculo);
					
				}
				System.out.println("El documento fue creado con éxito.");
	
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			}
		
		}
		
		
		
	}

}
