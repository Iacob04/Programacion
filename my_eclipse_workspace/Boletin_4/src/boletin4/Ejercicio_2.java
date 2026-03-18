
package boletin4;
import java.util.Scanner;
public class Ejercicio_2 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca la natidad de números fibonacci que desea calcular:");
		int num = teclado.nextInt();
		int a = 0;
		int b = 1;
		
		for(int i= 0; i< num; i++) {
			System.out.print(a);
			if(i<num -1) {
				System.out.print(", ");
			}
			int suma = a+b;
			a=b;
			b=suma;
			
		}
		
		
		teclado.close();
	}

}
