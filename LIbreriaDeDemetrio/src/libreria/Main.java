package libreria;

import java.io.BufferedReader;
import java.io.FileReader;


public class Main {
	

	public static void main(String[] args) {
		
		String fichero = "libros.txt";
		mostrarInventario(fichero);
		

	}
	
	public static void mostrarInventario(String fichero) {
		
		  
	    try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
	        
	        String linea;
	        
	        
	        while ((linea = lector.readLine()) != null) {
	            
	         
	          
	            
	            
	            
	            
	            
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Error con el fichero");
	        System.out.println(e.getMessage());
	    }
		
	}

}
