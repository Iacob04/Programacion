
package ejercicios;
import java.util.Scanner;
public class Contador_de_vocales {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Frase: ");
		String frase = teclado.nextLine();
		
		char [] vocales = {'a','e','i','o','u'};
		int [] contadores = new int [5];
		teclado.close();
		
		String fraseMinuscula = frase.toLowerCase();
		
		for (int i = 0; i<fraseMinuscula.length(); i++ ) {
			char letra = fraseMinuscula.charAt(i);
			for(int j=0; j<vocales.length; j++){
				
				if (letra == vocales[j])
					contadores[j]= contadores[j]+1;
				
			}
			
			
		}
		
		System.out.println("a:" + contadores[0] + " e:" + contadores[1] + " i:" + contadores[2] + " o:" + contadores[3] + " u:" + contadores[4]);
		

	}

}
