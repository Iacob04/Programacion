
package boletin3;
import java.util.Scanner;
public class Ejercicio_5 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		
		String texto;
		System.out.println("Escribe tu texto");
		texto = teclado.nextLine();
		String alreves = "";
		
				for (int i=texto.length()-1; i>=0; i--) {
					
					alreves = alreves + texto.charAt(i);
			
				
				}
		System.out.println("Cadena al revés : " + alreves);
		
		teclado.close();
		
		

	}

}
