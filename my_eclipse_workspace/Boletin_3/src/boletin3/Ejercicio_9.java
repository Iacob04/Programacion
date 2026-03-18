
package boletin3;

import java.util.Scanner;

public class Ejercicio_9 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		String pais;
		
		do {
		System.out.print("Elige entre los siguientes cuatro destinos (Francia, Italia, Chile o Japón): ");
		 pais = teclado.nextLine();
		
				
		if (pais.equalsIgnoreCase("Francia")) {
			System.out.println( "El pais seleccionado para viajar es Francia");
			System.out.println("La capital de su destino es Paris.");
		
		}
		else if (pais.equalsIgnoreCase("Italia")) {
			System.out.println( "El pais seleccionado para viajar es Italia");
			System.out.println("La capital de su destino es Roma.");
		
		}
		else if (pais.equalsIgnoreCase("Chile")) {
			System.out.println( "El pais seleccionado para viajar es Chile");
			System.out.println("La capital de su destino es Santiego de Chile.");
		
		}
		else if (pais.equalsIgnoreCase("Japón")) {
			System.out.println( "El pais seleccionado para viajar es Japón");
			System.out.println("La capital de su destino es Tokio.");
		
		}
		   else {
               System.out.println("El país introducido no está en la lista. Inténtalo de nuevo.");
           }
	
		} while (!pais.equalsIgnoreCase("Francia") && 
	            !pais.equalsIgnoreCase("Italia") && 
	            !pais.equalsIgnoreCase("Chile") && 
	            !pais.equalsIgnoreCase("Japón") && 
	            !pais.equalsIgnoreCase("Japon"));
		
		teclado.close();

	}

}
