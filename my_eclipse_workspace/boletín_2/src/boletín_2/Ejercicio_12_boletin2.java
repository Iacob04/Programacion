
package boletín_2;

import java.util.Scanner;

public class Ejercicio_12_boletin2 {

	public static void main(String[] args) {

		int azar = (int)(Math.random()*50)+1;
		//System.out.println(azar);
		Scanner teclado = new Scanner(System.in);
		boolean acertado = false;
		
		for(int intentos=1; intentos<6 && acertado == false; intentos++) {
			
			System.out.println("Intento número " + intentos + " ¿En que número estoy pensando?");
			int eleccion = teclado.nextInt();
			if (eleccion == azar) {
				System.out.println("¿Enhorabuena! ¡Has acertado!");
				acertado = true;
				}
			else if (eleccion > azar)
				System.out.println("vayaaaaa... Te has pasado");
			else
				System.out.println("noo... te has quedado corto");
			
		}
		if (acertado == false)
			System.out.println("Has agotado tus intentos para adivinar el número. El número era " + azar);
		
		teclado.close();
		
		

	}

}
