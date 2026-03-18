
package boletin3;
import java.util.Scanner;
public class Ejercicio_7 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		
		String texto;
		System.out.println("Escribe tu texto");
	
		texto = teclado.nextLine();
		
		if (texto.contains("a") || texto.contains("A") ) {
			texto = texto.replace ("a", "4");
			texto = texto.replace("A", "4");
		}
		if (texto.contains("e") || texto.contains("E") ) {
			texto = texto.replace ("e", "3");
			texto = texto.replace("E", "3");
		}
		if (texto.contains("i") || texto.contains("I") ) {
			texto = texto.replace ("i", "1");
			texto = texto.replace("I", "1");
		}
		if (texto.contains("o") || texto.contains("O") ) {
			texto = texto.replace ("o", "0");
			texto = texto.replace("O", "0");
		}
		
		System.out.println("El texto en alfabeto de hackers es: " + texto);
	
		teclado.close();
		

	}

}
