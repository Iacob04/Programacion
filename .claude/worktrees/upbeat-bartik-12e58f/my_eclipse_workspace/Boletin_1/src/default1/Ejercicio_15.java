
package default1;

import java.util.Scanner;

public class Ejercicio_15 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int num1;
		int num2;
		
			System.out.println("Generar un numero aleatorio entre los siguentes dos que introduzcas");
			
			System.out.print("Escribe el primer numero");
			num1 = teclado.nextInt();
			
			System.out.print("Escribe el segundo numero");
			num2 = teclado.nextInt();
			
			while(num1>num2);{
			System.out.println("Debes introducir el numero mayor pimero");
			System.out.print("Escribe el primer numero");
			num1 = teclado.nextInt();
			
			System.out.print("Escribe el segundo numero");
			num2 = teclado.nextInt();
			}
			int resultado = (int) (Math.random()*(num2 - num1)+num1 );
			System.out.println(resultado);
			
		teclado.close();

	}

}
