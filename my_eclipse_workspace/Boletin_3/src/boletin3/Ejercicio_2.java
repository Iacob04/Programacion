
package boletin3;
import java.util.Scanner;
public class Ejercicio_2 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		int contador = 0;
		
		System.out.println("Crea una contraseña");
		String contraseña = teclado.nextLine();
		
		System.out.println("Vuelva a introducir la contraseña para registrarla ");
		String confirmación = teclado.nextLine();
		
		while (!contraseña.equals(confirmación)) {
			contador ++ ;
			System.out.println("Las contraseñas no coinciden, intentalo denuevo ");
			confirmación = teclado.nextLine();
		}
		System.out.println("Se ha registrado correctamente tu contraseña");
		System.out.println("Has introducido mal la confirmación de la contraseña " + contador + " veces.");
		teclado.close();
		

	}

}
