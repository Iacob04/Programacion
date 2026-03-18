package listatareas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tarea {
	
	private String id;
	private String titulo;
	private int prioridad;
	private boolean completada;
	
	private static ArrayList<Tarea> listaTareas = new ArrayList<>();
	
	public Tarea(String id, String titulo, int prioridad, boolean completada) {
		
		this.id = id;
		this.titulo = titulo;
		this.prioridad = prioridad;
		this.completada = completada;
		Tarea.listaTareas.add(this);
			
		
	}
	
	public static void leerFicheroTareas(String fichero) {
	    
	    try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
	        
	        String linea;
	        
	        while ((linea = lector.readLine()) != null) {
	            
	            String[] lista = linea.split(":");
	            boolean completado = true;
	            
	            if (lista[3].equals("1")) {
	                completado = false;
	            }
	            
	            new Tarea(lista[0], lista[1], Integer.parseInt(lista[2]), completado);
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Error con el fichero");
	        System.out.println(e.getMessage());
	    }
	}
	
	public void mostararTarea() {
		String completada = " ";
		if(this.completada == true)
			completada = "X";
		System.out.printf("%s [%s] %s (Prioridad: %d)\n", completada, this.id, this.titulo,this.prioridad);
	}
	
	public static void mostrarTareas() {
		for(Tarea tarea:listaTareas) {
			tarea.mostararTarea();
		}
	}
	
	public static void grabarFicheroTareas(String fichero) {
		try (PrintWriter pluma = new PrintWriter(fichero)) {
			int completada = 0;
			
	        for( Tarea tarea : Tarea.listaTareas) {
	        	if(tarea.completada == true)
					completada = 1;
	        	pluma.printf("%s:%s:%d:%d",tarea.id, tarea.titulo,tarea.prioridad, completada);
	        	pluma.println();
	        }
			
	
	        
	    } catch (Exception e) {
	        System.out.println("Error con el fichero");
	        System.out.println(e.getMessage());
	    }
	
	
	
	}
	
	   public static ArrayList<Integer> ordenarPorBurbuja(ArrayList<Integer> desordenada) {
	        boolean hayCambios = true;
	        while (hayCambios == true) {
	            hayCambios = false;
	            for (int i=0; i<listaTareas.size()-1; i++) {
	               
	                if (listaTareas.get(i+1) > listaTareas.get(i)) {
	                    int n = listaTareas.remove(i);
	                    listaTareas.add(i+1,n);
	                    hayCambios = true;
	                }
	            }
	        }
	        return desordenada;
	    }
	}

