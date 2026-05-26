
package boletin_8;
import java.util.Scanner;
public class Ejercicio_3_boletin_8 {

	public static void main(String[] args) {
		System.out.print("Introduce una cadena para realizar un cifrado: ");
		Scanner teclado = new Scanner(System.in);
		String cadena = teclado.nextLine();
		String cifrado = "";
		
		int i = 0;
		int j = cadena.length()-1;		
		
		while(i<=j) 
		{
			if(i==j)
				cifrado += cadena.charAt(j);
			else {
			cifrado += cadena.charAt(j);
			cifrado += cadena.charAt(i);
			}
			i++;
			j--;
		}
		//if(cadena.length()%2!=0)
			//cifrado += cadena.charAt(cadena.length()/2);
	
		teclado.close();
		System.out.println(cifrado);

	}

}
