
package ejercicios;

import java.util.Scanner;

public class Adivinar_números {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // Generar número aleatorio entre 1 y 50 con Math.random()
        int secreto = (int)(Math.random() * 50) + 1;

        int intentos = 0;
        boolean acertado = false;

        System.out.println("He pensado un número entre 1 y 50. ¡Tienes 5 intentos!");

        while (intentos < 5 && !acertado) {
            System.out.print("Intento " + (intentos + 1) + ": ");
            int num = sc.nextInt();
            intentos++;

            if (num == secreto) {
                System.out.println("¡Has acertado! El número era " + secreto);
                acertado = true;
            } else if (num < secreto) {
                System.out.println("El número secreto es mayor.");
            } else {
                System.out.println("El número secreto es menor.");
            }
        }

        if (!acertado) {
            System.out.println("Has agotado los intentos. El número era " + secreto);
        }
        sc.close();

	}

}
