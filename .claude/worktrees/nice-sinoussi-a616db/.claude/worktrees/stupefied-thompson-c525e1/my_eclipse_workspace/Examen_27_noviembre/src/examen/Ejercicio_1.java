
package examen;
import java.util.Scanner;
public class Ejercicio_1 {

	public static void main(String[] args) {
	
		Scanner teclado = new Scanner(System.in);
		int cantidadDados = 0;
		
		while(cantidadDados<1) {
			System.out.print("Cuantos dados vas a tirar? ");
			cantidadDados = teclado.nextInt();
			if (cantidadDados<1) {
				System.out.println("No es una opción válida");
			}
			
		}
		
		int[] dados = new int[cantidadDados];
		
		for(int i = 0; i<dados.length;i++) {
			
			dados[i] = (int)(Math.random()*6)+1;	
		}
		System.out.println("Has tirado " + cantidadDados + " dados y ha salido lo siguiente: ");
		
		for(int i=0; i<dados.length; i++) {
					
			System.out.print(dados[i]);
			if(i==dados.length) {
				System.out.print(dados[i]);
			}
			else {
				System.out.print(", ");
			}
					
		}
		
		int contadorUnos = 0;
		int contadorSeises = 0;
		
		for(int i = 0; i<dados.length; i++) {
			
			if(dados[i]== 1) {
				contadorUnos++;
			}
			else if(dados[i]== 6) {
				contadorSeises++;
			}
		}
		System.out.println();
		System.out.println("En " + contadorUnos + " dados ha salido un 1");
		System.out.println("En " + contadorSeises + " dados ha salido un 6");
		
		int sumaDados = 0;
		
		for(int i = 0; i<dados.length; i++) {
					
			sumaDados = sumaDados+dados[i];
				
		}
		System.out.println("La suma de todos los dados da "+sumaDados);
		
		int totalPosible = cantidadDados * 6;
		int mitad = totalPosible/2;	
		
		if (sumaDados==mitad) {
			System.out.println("Tu tirada es exactamente la mitad ("+mitad+")");
		}
		else if(sumaDados > mitad) {
			System.out.println("Tu tirada esta por encima de la mitad ("+mitad+")");
		}
		else {
			System.out.println("Tu tirada esta por debajo de la mitad ("+mitad+")");
		}
		
		teclado.close();
	}

}
