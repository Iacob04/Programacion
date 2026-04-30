
package default1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio_20 {

	public static void main(String[] args) {

System.out.println("Comparacion de numeros ");
		
		
		Scanner teclado = new Scanner(System.in);
		
		 System.out.print("Introduce el primer numero: ");
	        int numero1 = teclado.nextInt();
	        
	     System.out.print("Introduce el segundo numero: ");
	        int numero2 = teclado.nextInt();
	        
	     System.out.print("Introduce el tercer numero: ");
	        int numero3 = teclado.nextInt();   
		
	        int[] numeros = {numero1, numero2, numero3};
	        
	        Arrays.sort(numeros);
	        
	        System.out.println("Los numeros ordenados de menor a mayor son:");
	        for (int num : numeros) {
	            System.out.print(num + " ");
		
	        }
	        teclado.close();
	}

}
