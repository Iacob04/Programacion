
package default1;

public class Ejercicio_17 {

	public static void main(String[] args) {

		System.out.println("Tus quince numeros aleatorios para la quiniela son: ");
		for(int i=0; i<15; i++){
			int numloto = (int)(Math.random()*(4 - 1))+1;
			
			if (numloto == 3) {
		
			System.out.print(" X ");
			}
			else {
				
			System.out.print(" " + numloto + " ");
			}
		
		}
	}

}
