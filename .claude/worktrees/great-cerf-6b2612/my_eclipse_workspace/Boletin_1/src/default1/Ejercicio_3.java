
package default1;

import java.util.Scanner;

public class Ejercicio_3 {

	public static void main(String[] args) {
	
		Scanner teclado = new Scanner(System.in);
		int numero;
		
			System.out.println("Escribe un numero:");
			numero = teclado.nextInt();
			for (int i = 1; i < 6; i++) {
				int total= numero*i;
				System.out.println(total);
			}		
			System.out.println("Estos son los 5 primeros multiplos de : " + numero );
		
		
		teclado.close();
		

	}

}
