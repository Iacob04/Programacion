
package ejercicios;
import java.util.Scanner;
public class Array_con_validación_y_comprobación {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Se van a generar un array con 10 números aleatorios");
		
		int[] numeros = new int[10];
		
		for(int i=0; i<numeros.length; i++) {
			
			numeros[i] = (int)(Math.random()*50)+1;	
		}
		
		System.out.println("El array generado contiene estos valores en el: ");
		
		for(int i=0; i<numeros.length; i++) {
			
			System.out.print(numeros[i] + " ");
			
		}
		System.out.println();
		System.out.print("Introduce un número cualquiera entre el 1 y el 50: ");
		int comparar = teclado.nextInt();
		
	     while(comparar < 1 || comparar > 50) {
	            System.out.print("Error introduce un número válido: ");
	            comparar = teclado.nextInt();
	        }
			
		int contador = 0;
		for(int i =0; i<numeros.length;i++) {
			
			if(comparar > numeros[i]) {
				contador ++;
			}
			
		}
		
		System.out.println("En el array hay " + contador + " números mayores que " + comparar);
		
		
		teclado.close();
		
	
	}
	
}
