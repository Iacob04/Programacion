
package boletin4;
import java.util.Scanner;
public class Ejercicio_1 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el número que deseas calcular factorialmente que sea positivo");
		int numero = teclado.nextInt();
		long factorial = 1;
			
			for(int i=numero; i>=1; i--) {
			factorial = factorial * i;
			
		
		}
			System.out.println("La factorial de " + numero + " es " + factorial);
			teclado.close();
	}

}
