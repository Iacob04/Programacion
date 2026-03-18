package boletin_15;

import java.util.ArrayList;

abstract class Persona {
	
	protected ArrayList<Consulta> consultas = new ArrayList<>();
	
	protected String nombre;
	protected String apellidos;
	protected Centro_medico centro;
	
	public Persona(String nombre,String apellidos, Centro_medico centro) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.centro = centro;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellidos;
	}
	
	public Centro_medico getCentro() {
		return this.centro;
	}
	
	public void anyadeConsulta(Consulta c) {
		consultas.add(c);
	}
	
	abstract public void cambioCentro(Centro_medico c) ;
		
	public void listaConsultas() {
		
		for(Consulta c: consultas)
			c.muestraConsulta();
		
	}
	

}
