
package boletin3;
import java.util.Scanner;
public class Ejercicio_1 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Crea una contraseña");
		String contraseña = teclado.nextLine();
		
		System.out.println("Vuelva a introducir la contraseña para registrarla ");
		String confirmación = teclado.nextLine();
		
		while (!contraseña.equals(confirmación)) {
			System.out.println("Las contraseñas no coinciden, intentalo denuevo ");
			confirmación = teclado.nextLine();
		}
		System.out.println("Se ha registrado correctamente tu contraseña");
		
		teclado.close();
		

	}

}
