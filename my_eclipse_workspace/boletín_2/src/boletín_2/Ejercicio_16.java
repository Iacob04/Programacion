
package boletín_2;

import java.util.Scanner;

public class Ejercicio_16 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		double radio ;
		
		double pi = Math.PI;
		
		System.out.print("Introduzca el rádio de su circunferencia en cm: ");
		
		radio = teclado.nextDouble();
		
		double calculoarea = pi*(radio*radio);
		calculoarea = Math.round(calculoarea * 100000.0) / 100000.0;
		
		double calculolongitud = 2*pi*radio;
		calculolongitud = Math.round(calculolongitud * 100000.0) / 100000.0;
		
		System.out.println("El área de su circunferencia es de: " + calculoarea + " cm");
		System.out.println("La longitud de su circunferencia es de: " + calculolongitud + " cm");
		
		teclado.close();
	}

}
