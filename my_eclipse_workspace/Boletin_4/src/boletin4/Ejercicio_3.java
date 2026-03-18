
package boletin4;
import java.util.Scanner;
public class Ejercicio_3 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca la natidad de números fibonacci que desea calcular:");
		int num = teclado.nextInt();
		int a = 0;
		int b = 1;
		

		while (a <= num) {
            System.out.print(a);

            int siguiente = a + b;

            
            if (siguiente <= num) {
                System.out.print(",");
            }

            a = b;
            b = siguiente;
        }
		
		teclado.close();
	}

}
