
package boletin3;
import java.util.Scanner;
public class Ejercicio_8 {

	public static void main(String[] args) {

		String texto;
		System.out.println("Escribe tu texto");
		Scanner teclado = new Scanner(System.in);
		texto = teclado.nextLine();
		String sinvocales = "";
		String minusculas = texto.toLowerCase();
		
		  // Recorremos cada carácter del texto original
		for(int i=0; i<texto.length(); i++) {
			// Guardamos el carácter actual en la variable 'c'
			char c = minusculas.charAt(i);
			 // Usamos un switch para comprobar si el carácter es una vocal
			switch(c) {
			case 'a':
				break;
			case 'e':
				break;
			case 'i':
				break;
			case 'o':
				break;
			case 'u':
				break;
				// Si no es una vocal, lo añadimos a la nueva cadena
				default:sinvocales = sinvocales+minusculas.charAt(i);
			}
		
		}
	
		
		System.out.println("Cadena de texto sin vocales : " + sinvocales);
		
		
		teclado.close();
		
		

	}

}
