
package ejercicios;
import java.util.Scanner;
public class Mezclar_caracteres_pricipio_final {

	public static void main(String[] args) {
	
				Scanner teclado = new Scanner(System.in);
				System.out.print("Escriba una cadena de texto deseada para cifrar: ");
				String texto = teclado.nextLine();
				int i = 0;
				int j = texto.length() - 1;
				String cifrado = "";

				while (i < j) {
					cifrado += texto.charAt(j);
					cifrado += texto.charAt(i);
					i++;
					j--;
				}
				if (texto.length()%2!=0) {
					cifrado+=texto.charAt(texto.length()/2);
				}
				System.out.println(cifrado);
			
		teclado.close();
	
	}
	
}
