package RA4yRA7_Ordinaria;

import java.util.ArrayList;

public class Receta {
	
	private String nombre;
	private int personas;
	private ArrayList<Ingredientes> ingredientes ;
	
	public Receta(String nombre, int personas) {
		
		this.nombre = nombre;
		this.personas = personas;
		this.ingredientes = new ArrayList<>();
	}
	
	

    // añadir uno
    public void addIngrediente(Ingredientes i) {
        ingredientes.add(i);
    }

    // añadir lista
    public void addIngredientes(ArrayList<Ingredientes> lista) {
        ingredientes.addAll(lista);
    }

    @Override
    public String toString() {

        String titulo = nombre.toUpperCase()
                + " PARA " + personas + " PERSONAS";

        StringBuilder sb = new StringBuilder();
        sb.append(titulo).append("\n");

        for (int i = 0; i < titulo.length(); i++) {
            sb.append("=");
        }
        sb.append("\n");

        for (Ingredientes i : ingredientes) {

            sb.append(i.getNombre());

            if (i.getCantidad() != null && i.getMedida() != null) {
                sb.append(": ")
                  .append(i.getCantidad())
                  .append(" ")
                  .append(i.getMedida());
            }

            sb.append("\n");
        }

        return sb.toString();
    }
    
    /* ALTERNATIVA
     @Override
public String toString() {

    String titulo = nombre.toUpperCase()
            + " PARA " + personas + " PERSONAS";

    String resultado = titulo + "\n";

    for (int i = 0; i < titulo.length(); i++) {
        resultado += "=";
    }

    resultado += "\n";

    for (Ingredientes ing : ingredientes) {

        resultado += ing.getNombre();

        if (ing.getCantidad() != null && ing.getMedida() != null) {
            resultado += ": " + ing.getCantidad() + " " + ing.getMedida();
        }

        resultado += "\n";
    }

    return resultado;
}
     */

}
