
package default1;

import java.util.Scanner;

public class Ejercicio_19 {

	public static void main(String[] args) {
		
		 Scanner teclado = new Scanner(System.in);
			
		 System.out.print("Introduce un numero: ");
	        int numero = teclado.nextInt();

	        System.out.println("Los divisores de " + numero + " son:");

	        for (int i = 1; i <= numero; i++) {
	            if (numero % i == 0) { 
	                System.out.println(i);
	            }
	        }
	        teclado.close();
	}

}
