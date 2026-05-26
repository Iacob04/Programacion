
package default1;

public class Ejercicio_11 {

	public static void main(String[] args) {

		int inicio = 1 ;
		int fin = 6 ;
		int dado1 = 0;
		int dado2 = 1;
		int contador = 0;
	 //1ª forma
		/*while (dado1 != dado2) {
			contador = contador+1;
			
			dado1 = (int)(Math.random()*(fin-inicio+1))+inicio;
			System.out.println("El dado 1 salio : " + dado1); 
			
			dado2 = (int)(Math.random()*(fin-inicio+1))+inicio;
			System.out.println("El dado 2 salio : " + dado2); 
						
			}*/
		//2ª forma
		do {
			contador++;
			dado1 = (int)(Math.random()*(fin-inicio+1))+inicio;
			System.out.println("El dado 1 salio : " + dado1); 
			
			dado2 = (int)(Math.random()*(fin-inicio+1))+inicio;
			System.out.println("El dado 2 salio : " + dado2); 	
			
						
		}while (dado1 != dado2) ;
		
		System.out.println("Ambos dados son iguales : " + dado1 + " = " + dado2);
	    System.out.print("El dado ha sido lanzado " + contador);
	    if (contador == 1)
	    	System.out.println(" vez");
	    else
	    	System.out.println(" vezes");

	}

}
