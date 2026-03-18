
package default1;

import java.util.Scanner;

public class Ejercicio_8 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		double pago;
		int mensualidad;
		double pago_mensual;
		
			System.out.println("Escribe el importe del la cantidad que quiere abonar");
			pago = teclado.nextInt();
			
			System.out.println("Escribe las mensualidades que desea tardar en pagar la cantidad de " + pago);
			mensualidad = teclado.nextInt();
			
			pago_mensual = pago / mensualidad ;
			System.out.println("La cuota mensual es de : " + pago_mensual);
			
			teclado.close();

	}

}
