
package default1;

import java.util.Scanner;

public class Ejercicio_6 {

	public static void main(String[] args) {
	
		Scanner teclado = new Scanner(System.in);
		int numero;
		
			System.out.println("Escribe un numero");
			numero = teclado.nextInt();
			
		if(numero%3==0)
			System.out.println("El numero " + numero + " es divisible por 3");
		else
			System.out.println("El numero " + numero + " no es divisible por 3");
		
		teclado.close();
		
	}

}
