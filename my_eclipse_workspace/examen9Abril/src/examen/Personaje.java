package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class Personaje {
	
	private HashMap<String, String> diccionarioPersonajes = new HashMap<>();
	private HashMap<String, String> diccionarioAnimes = new HashMap<>();
	
	public static void mostrarPersonajeYAnime(String ficheroPersonajes,String ficheroAnimes) {
		
		HashMap<String, String> diccionarioPersonajes = new HashMap<>();
		HashMap<String, String> diccionarioAnimes = new HashMap<>();
		
		try (BufferedReader lector = new BufferedReader(new FileReader(ficheroPersonajes))) {
		    String linea;
		    while ((linea = lector.readLine()) != null) {
		        int pos = linea.indexOf(" ");  
		        String clavePersonaje = linea.substring(0, pos);
		        String valorPersonaje = linea.substring(pos+1);
		        diccionarioPersonajes.put(clavePersonaje, valorPersonaje);
		      
		    }
		} catch (Exception e) { System.out.println(e.getMessage()); 
		
		}

		
		try (BufferedReader lector = new BufferedReader(new FileReader(ficheroAnimes))) {
		    String linea;
		    while ((linea = lector.readLine()) != null) {
		        int pos = linea.indexOf(" ");  
		        String claveAnime = linea.substring(0, pos);
		        String valorAnime = linea.substring(pos+1);
		        diccionarioAnimes.put(claveAnime, valorAnime);
		        
		    }
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		for(Entry<String, String> personaje : diccionarioPersonajes.entrySet()) {
			//System.out.println(personaje.getValue());
			
			for(Entry<String, String> anime : diccionarioAnimes.entrySet()) {
				
				if(personaje.getKey().contains(anime.getKey())) {
					System.out.println(personaje.getValue()+ " ("+anime.getValue()+" )");
					
				}
				
			}
				
		}
		    
		
		 
	}
	
	
		
		
	


}
