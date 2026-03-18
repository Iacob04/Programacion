
package boletín_2;

import java.util.Scanner;

public class Ejercicio_10 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		String texto = "";
		
		int contador = 0;
		int suma = 0;
	
		
		 while (!texto.equals("FIN")) {
	            System.out.print("Introduce un número entre el 1 y el 100 (o FIN para salir): ");
	            texto = teclado.nextLine();

	            if (!texto.equals("FIN")) {
	              
	                if (texto.matches("\\d+")) { 
	                    int numero = Integer.parseInt(texto);
	                    if (numero >= 1 && numero <= 100) {
	                    	contador++;
	                    	suma+=numero;
	                    	
	                        System.out.println("Número válido: " + numero);
	                    } else {
	                        System.out.println("Error: el número debe estar entre 1 y 100.");
	                    }
	                } else {
	                    System.out.println("Error: entrada no válida. Escribe un número o 'FIN'.");
	                }
	            }
	        }
		 	
		 
		 	System.out.println("Se han introducido " + contador + " números correctamente");
		 	System.out.println("La media aritética de los números introducidos es de " + suma/contador);
		 	
 	        System.out.println("Programa finalizado.");
	        teclado.close();   
	}

}
