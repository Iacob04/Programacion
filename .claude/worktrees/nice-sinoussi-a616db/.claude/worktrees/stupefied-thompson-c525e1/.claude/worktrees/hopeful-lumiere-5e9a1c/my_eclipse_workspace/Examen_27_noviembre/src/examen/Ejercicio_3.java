
package examen;

public class Ejercicio_3 {

	
	public static void main(String[] args) {

		int num1 = 8;
		int num2 = 15;
		System.out.println("Numeros capicúa entre " + num1 + " y " + num2 + ":");
		
		int[] numeros = new int[num2-num1];
		int contador = num1;
		for(int i = 0; i<numeros.length;i++) {
			
			if(i== 0) {
				numeros[i]= contador;
			}
			
		else {
				numeros[i] = contador++;
			}
			
				
		}
		for(int i=0; i<numeros.length; i++) {
			
			System.out.print(numeros[i]);
			if(i==numeros.length) {
				System.out.print(numeros[i]);
			}
		}
	}

}
