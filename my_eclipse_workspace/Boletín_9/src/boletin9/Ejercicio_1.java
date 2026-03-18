
package boletin9;
import java.util.Scanner;
public class Ejercicio_1 {

	public static void main(String[] args) {
	
    Scanner teclado = new Scanner(System.in);
	System.out.println("Introduce dos números para comprobar si son amigos");
	System.out.print("Introduzca el primer número: ");
	int n1 = teclado.nextInt();
	System.out.print("Introduzca el segundo número: ");
	int n2 = teclado.nextInt();
	
	
	if (sonAmigos(n1,n2) == true)
		 System.out.println("Los números son amigos");
	 else
		 System.out.println("Los números introducidos no son amigos");
	 
	 
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
	public static boolean sonAmigos(int n1, int n2) {
		boolean amigos = false;
		int suma1 = suma(n1);
		int suma2 = suma(n2);
		 if(suma1 == n2 && suma2 == n1)
			 amigos = true;
		 return amigos;
		 
	}
	
}