
package default1;

public class Ejercicio_10 {

	public static void main(String[] args) {

		int inicio = 1 ;
		int fin = 6 ;
		
	 
	for (int i = 0; i < 2 ; i++) {	
		int dado = (int)(Math.random()*(fin-inicio+1))+inicio;
		System.out.println(dado); 
		
	}

	}

}
