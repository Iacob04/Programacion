
package boletin_8;
import java.util.Scanner;
public class Ejercicio_7_boletin_8 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduce un número para averiguar si es Armstrong o Narcisista: ");
		
		String num = teclado.nextLine();
		
		int exponente = num.length();
		int suma = 0;
		
			for(int i=0; i<num.length();i++) {
				
				int n = Character.getNumericValue(num.charAt(i));
				suma += Math.pow(n, exponente);
				
				
			}
			if(suma == Integer.parseInt(num))
				System.out.println(num + " es narcisista");
			else
				System.out.println(num + " no es narcisista");
		
	
		teclado.close();
		

	}

}
