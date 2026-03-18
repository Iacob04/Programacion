
package boletin4;

import java.util.Scanner;

public class Ejercicio_13 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce un caracter");
		String caracter = teclado.nextLine();
		System.out.println("Introduce un número de repeticiones: ");
		int repeticiones = teclado.nextInt();

		for (int i = 0; i < repeticiones; i++) {
			for (int j = 0; j < repeticiones; j++) {
				System.out.print(caracter);
			}
			System.out.println();
		}
		teclado.close();
	}

}
