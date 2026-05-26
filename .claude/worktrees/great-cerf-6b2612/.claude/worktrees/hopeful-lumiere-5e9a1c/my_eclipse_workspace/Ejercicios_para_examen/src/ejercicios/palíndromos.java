
package ejercicios;
import java.util.Scanner;
public class palíndromos {

	public static void main(String[] args) {
		

		        Scanner sc = new Scanner(System.in);
		        System.out.print("Introduce una frase: ");
		        String frase = sc.nextLine().toLowerCase().replace(" ", "");

		        String invertida = "";
		        for (int i = frase.length() - 1; i >= 0; i--) {
		            invertida += frase.charAt(i);
		        }

		        if (frase.equals(invertida)) {
		            System.out.println("Es palíndromo");
		        } else {
		            System.out.println("No es palíndromo");
		        }
		  sc.close();
	}

}
