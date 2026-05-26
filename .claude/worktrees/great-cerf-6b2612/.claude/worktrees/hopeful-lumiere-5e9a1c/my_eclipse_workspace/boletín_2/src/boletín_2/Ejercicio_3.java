
package boletín_2;
import java.util.Scanner;
public class Ejercicio_3 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		double preciosiniva;
		double preciofinal;
		
		System.out.print("Introduzca el precio del articulo para aplcarle el 21% de iva : ");
		preciosiniva = teclado.nextDouble();
		
		preciofinal = preciosiniva + preciosiniva*0.21;
		preciofinal = Math.round(preciofinal * 100.0) / 100.0;
		System.out.println("El precio final del producto es de " + preciofinal + " Euros");
		
		teclado.close();
	}

}
