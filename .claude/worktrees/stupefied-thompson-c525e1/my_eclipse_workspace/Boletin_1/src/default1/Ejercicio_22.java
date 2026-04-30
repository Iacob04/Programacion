
package default1;

public class Ejercicio_22 {

	public static void main(String[] args) {
		boolean esPrimo;
		int azar;
	    do {
	    	esPrimo =true;
	    	azar = (int)(Math.random()*(400000000+1))+100000000;
	    	int raiz = (int)Math.sqrt(azar)+1;
	    	if(azar%2==0)
	    		esPrimo = false;
	    	else {
	    	
	    	System.out.println("Probando el numero " + azar + "...");
	    	
	    	for(int divisor=3; divisor<raiz && esPrimo == true; divisor+=2)
	    		if(azar%divisor == 0)
	    			esPrimo = false ;
	    }  }
	    
	    while(esPrimo == false);{
	    	System.out.println("El numero " + azar + " es primo");
	    	
	    }

	}

}
