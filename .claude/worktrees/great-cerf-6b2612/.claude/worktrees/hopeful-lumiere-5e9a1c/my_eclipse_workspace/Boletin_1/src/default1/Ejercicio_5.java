
package default1;

import java.util.Scanner;

public class Ejercicio_5 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int numero;
		
			System.out.println("Escribe un numero");
			numero = teclado.nextInt();
			
		if(numero%2==0)
			System.out.println("El numero " + numero + " es par");
		else
			System.out.println("El numero " + numero + " es impar");
		
		teclado.close();

	}

}
