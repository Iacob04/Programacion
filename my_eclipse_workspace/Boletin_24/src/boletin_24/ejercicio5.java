package boletin_24;

import java.util.HashMap;
import java.util.Scanner;

public class ejercicio5 {

	public static void main(String[] args) {
		
		String fichero = "compra.txt";
		anyadirCompra();
		
		HashMap<String, Integer> lista = new HashMap<>();
		
		
	}
	
	public static void anyadirCompra() {
		
		boolean introduciendo = true;
		
		do{
		System.out.println("Introduce un artículo: ");
		Scanner teclado = new Scanner(System.in);
		String articulo = teclado.next();
		
		}while(introduciendo == false);
		
		
	}

}
