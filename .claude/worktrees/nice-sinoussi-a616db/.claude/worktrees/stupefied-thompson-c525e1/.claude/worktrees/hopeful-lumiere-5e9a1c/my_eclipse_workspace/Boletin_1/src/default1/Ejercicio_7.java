
package default1;

import java.util.Scanner;

public class Ejercicio_7 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		double precio;
		double precioiva;
		
			System.out.println("Escribe el importe del articulo");
			precio = teclado.nextInt();
			precioiva = precio + (precio*0.21);
			System.out.println("El articulo tras aplicarle los impuestos , cuesta : " + precioiva);
		
			teclado.close();
	}

}
