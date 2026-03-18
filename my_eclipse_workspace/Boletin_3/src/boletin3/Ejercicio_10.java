 
package boletin3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio_10 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduzca su NIF para combrobar si es correcto: ");
		String nif = teclado.nextLine();
		
		Pattern patronNIF = Pattern.compile("[0-9]{8}[a-zA-Z]{1}");
		Matcher coincidenciaNIF = patronNIF.matcher (nif);
		
		while (coincidenciaNIF.find() == false) {
			System.out.println("No es un formato válido, vuelva a intentarlo");
			System.out.print("Introduzca su NIF para combrobar si es correcto: ");
			nif = teclado.nextLine();
			patronNIF = Pattern.compile("[0-9]{8}[a-z A-Z]{1}");
			coincidenciaNIF = patronNIF.matcher (nif);
		}
		
		System.out.println("El formato de su NIF es correcto");
		 
		
		
		teclado.close();

	}

}
