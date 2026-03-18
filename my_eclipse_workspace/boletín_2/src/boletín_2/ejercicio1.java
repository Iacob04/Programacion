package boletín_2;

import java.util.Scanner;

public class ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		if(palabra1.compareTo(palabra2) == 0 && palabra1.compareTo(palabra3)== 0)
			System.out.println("Las tres palabras son iguales");
		
		else if (palabra1.compareTo(palabra2)<0 && palabra1.compareTo(palabra3)<0 && palabra2.compareTo(palabra3)<0)
			System.out.println("El orden de las palabras son : " + palabra1 + " , " + palabra2 + " , " + palabra3);
		
		else if (palabra1.compareTo(palabra2)>0 && palabra1.compareTo(palabra3)>0 && palabra2.compareTo(palabra3)>0)
			System.out.println("El orden de las palabras son : " + palabra3 + " , " + palabra2 + " , " + palabra1);
		
		teclado.close();
		
	}

}
