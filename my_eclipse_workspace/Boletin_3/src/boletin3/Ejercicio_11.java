 
package boletin3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio_11 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduzca su NIF para combrobar si es correcto: ");
		String nifonie = teclado.nextLine();
		
		Pattern patronNIF = Pattern.compile("[0-9]{8}[a-zA-Z]{1}");
		Matcher coincidenciaNIF = patronNIF.matcher (nifonie);
		
		Pattern patronNIE = Pattern.compile("[x-zX-Z]{1}[0-9]{7}[a-zA-Z]{1}");
		Matcher coincidenciaNIE = patronNIE.matcher (nifonie);
		
		while (!patronNIF.matcher(nifonie).matches() && !patronNIE.matcher(nifonie).matches()) {
			System.out.println("El formato del NIE o NIF introducido es incorrecto, vuelva a intentarlo");
			System.out.print("Introduzca su NIF para combrobar si es correcto: ");
			nifonie = teclado.nextLine();
			coincidenciaNIF = patronNIF.matcher (nifonie);
			coincidenciaNIE = patronNIE.matcher (nifonie);
			}
		if (coincidenciaNIF.find() == true) 
			System.out.println("Has introducido un NIF válido");
			
		 
		else 
			System.out.println("Has introducido un NIE válido");
		
		
		 
		
		
		teclado.close();

	}

}
