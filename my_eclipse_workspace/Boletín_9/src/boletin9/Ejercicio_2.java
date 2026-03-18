
package boletin9;
import java.util.Scanner;
public class Ejercicio_2 {

	public static void main(String[] args) {
	
    Scanner teclado = new Scanner(System.in);
    
   int n1 = 0;
	boolean entero = false;
	while (entero == false) {
		System.out.print("Introduce un numero para comprobar si es perfecto: ");
		n1 = teclado.nextInt();
		if(n1 > 0)
			entero = true;
		else
			System.out.println("Error, debes introducir un número entero positivo.");
	}
	
	
	
	if (sonPerfectos(n1) == true)
		 System.out.println("El número " + n1 + " es perfecto");
	else
		System.out.println("El número " + n1 + " no es perfecto");
	
	 
	 
	}
	public static int suma(int n) {
		int suma = 0;
		for(int i = 1; i<n; i++) {
			 if (n%i == 0){
		 	  suma += i;
			 }
		 }
		return suma;
		
	}
	public static boolean sonPerfectos(int n1) {
		boolean perfecto = false;
		int suma1 = suma(n1);
		 if(suma1 == n1)
			 perfecto = true;
		 return perfecto;
		 
	}
	
}
