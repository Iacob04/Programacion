
package default1;

public class Ejercicio_16 {

	public static void main(String[] args) {
		System.out.println("Tus seis numeros aleatorios para la primitiva son: ");
		for(int i=0; i<6; i++){
			int numloto = (int)(Math.random()*(49 - 1))+1;
			System.out.print(" " + numloto + " ");
		}
	}

}
