package boletín_2;

import java.util.Scanner;

public class Ejercicio_1 {

	public static void main(String[] args) {
		

		Scanner teclado = new Scanner(System.in);
		
		String palabra1;
		String palabra2;
		String palabra3;
		
		System.out.println("Escribe la primera palabra");
		palabra1 = teclado.nextLine();
		
		System.out.println("Escribe la segunda palabra");
		palabra2 = teclado.nextLine();
		
		System.out.println("Escribe la tercera palabra");
		palabra3 = teclado.nextLine();
		
		if (palabra1.compareTo(palabra2) <= 0 && palabra2.compareTo(palabra3) <= 0) {
		    System.out.println(palabra1 + " , " + palabra2 + " , " + palabra3);
		    
		} else if (palabra1.compareTo(palabra3) <= 0 && palabra3.compareTo(palabra2) <= 0) {
		    System.out.println(palabra1 + " , " + palabra3 + " , " + palabra2);
		    
		} else if (palabra2.compareTo(palabra1) <= 0 && palabra1.compareTo(palabra3) <= 0) {
		    System.out.println(palabra2 + " , " + palabra1 + " , " + palabra3);
		    
		} else if (palabra2.compareTo(palabra3) <= 0 && palabra3.compareTo(palabra1) <= 0) {
		    System.out.println(palabra2 + " , " + palabra3 + " , " + palabra1);
		    
		} else if (palabra3.compareTo(palabra1) <= 0 && palabra1.compareTo(palabra2) <= 0) {
		    System.out.println(palabra3 + " , " + palabra1 + " , " + palabra2);
		    
		} else {
		    System.out.println(palabra3 + " , " + palabra2 + " , " + palabra1);
		}

		teclado.close();
		
	}

}
