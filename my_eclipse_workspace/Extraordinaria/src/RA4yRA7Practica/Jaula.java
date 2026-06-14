package RA4yRA7Practica;

import java.util.ArrayList;

public class Jaula {

	private String tipo;
	private ArrayList<Animal>animales ;
	
	public Jaula(String tipo) {
		this.tipo = tipo;
		this.animales = new ArrayList<>();
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	
	public void addAnimales(Animal a) {
		animales.add(a);
	}
	
	public void addAnimales(ArrayList<Animal>lista) {
		animales.addAll(lista);
	}
	
	
	@Override
	public String toString() {
		
		String animal_es = "animales";
		
		if(animales.size()== 1) {
			animal_es = "animal";
		}
		
		
		String titulo =  "  JAULA: " + tipo.toUpperCase() + " ("+ animales.size()+ " "+ animal_es +")";
		StringBuilder sb = new StringBuilder();
        sb.append(titulo).append("\n");

        for (int i = 0; i < titulo.length(); i++) {
            sb.append("=");
        }
        sb.append("\n");
        
        for(Animal a : animales) {
        	String anyos = "años";
        	if(a.getEdad()== 1) {
        		anyos = "año";
        	}
        	sb.append(a.getNombre());
        	if (a.getEdad() != 0 ) {
                sb.append(" - " + a.getEdad()+" "+ anyos);
                 
        	}
        	
        	 sb.append("\n");
        	
        }
        return sb.toString();
	}
	
	
}
