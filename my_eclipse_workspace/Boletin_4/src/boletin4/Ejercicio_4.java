
package boletin4;
import java.util.Scanner;
public class Ejercicio_4 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca un numero y contar las cifras que tiene:");
		int num = teclado.nextInt();
		
		String numero= String.valueOf(num);
		int numCifras = numero.length();
		
		System.out.println("El número " +num+ " tiene " +numCifras + " cifras.");
		
		teclado.close();
	}

}
