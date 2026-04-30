package boletin18;
import java.util.HashSet;
public class Ejercicio_1 {

	public static void main(String[] args) {
		
		
		HashSet<Integer> numeros = new HashSet<>();
		
		boolean esPrimo;

			do {
				esPrimo =true;
				int numero = ((int)(Math.random()*100)+1);
				int raiz = (int)Math.sqrt(numero)+1;
		    	if(numero%2==0) {
		    		esPrimo = false;
		    	}
		    	else {
		    		
		    	for(int divisor=3; divisor<raiz && esPrimo == true; divisor+=2)
		    		if(numero%divisor == 0)
		    			esPrimo = false ;
		    	numeros.add(numero);
		    	
		    	}
		    	
		    	}while(numeros.size()!=10);
			
			
			
		
			System.out.println(numeros);
			

			
		
		
	}

}
