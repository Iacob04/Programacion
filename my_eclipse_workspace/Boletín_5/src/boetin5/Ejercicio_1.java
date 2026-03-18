
package boetin5;

public class Ejercicio_1 {

	public static void main(String[] args) {
		
		System.out.println("Se van a generar 6 números aleatorios entre el 1 y el 49");
		
		int i = 1;
	
		while(i!=7) {
			
			int numeroAleatorio = (int)(Math.random()*(49-1+1)+1);
			System.out.print(numeroAleatorio + ",");
			
			i++;
			
		}
		
		

	}

}
