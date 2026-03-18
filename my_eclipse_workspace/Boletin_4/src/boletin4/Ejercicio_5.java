
package boletin4;
import java.util.Scanner;
public class Ejercicio_5 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el número que deseas calcular factorialmente que sea positivo");
		int numero = teclado.nextInt();
	     
		 String texto = String.valueOf(numero); 
		 String alreves = "";
		 for (int i=texto.length()-1; i>=0; i--) {
				
				alreves = alreves + texto.charAt(i);
		
			
			}
			if (texto.equals(alreves)) 
			{
				System.out.println("El número es capicua");
			}
			else {
				System.out.println("El número no es capicua");
			}
				teclado.close();
	}

}
