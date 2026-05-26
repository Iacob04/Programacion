
package default1;

import java.util.Scanner;

public class Ejercicio_14 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		double num1;
		double num2;
		
			System.out.println("Generar un numero aleatorio entre los siguentes dos que introduzcas");
			
			System.out.print("Escribe el primer numero");
			num1 = teclado.nextDouble();
			
			System.out.print("Escribe el segundo numero");
			num2 = teclado.nextDouble();
			
			double resultado = (double) num1 + (Math.random()*(num2 - num1));
			System.out.println(resultado);
		
		teclado.close();

	}

}
