
package boletin3;
import java.util.Scanner;
public class Ejercicio_3 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		
		System.out.println("Introduce tu nombre (sin apellido)");
		String nombre = teclado.nextLine();
		System.out.println("Introduce tus apellidos");
		String apellido = teclado.nextLine();
		
		System.out.println(nombre + " "+apellido);
		teclado.close();
		

	}

}
