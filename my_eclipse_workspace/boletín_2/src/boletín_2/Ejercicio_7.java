
package boletín_2;
import java.util.Scanner;
public class Ejercicio_7 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce el número que deseas multiplicar");
		int numero = teclado.nextInt();
		
		for (int i = 1; i < 10; i++) {
            int resultado = (int) (numero*i);
            System.out.println(numero + " * " + i + " = " + resultado);
		}
		teclado.close();
	}

}
