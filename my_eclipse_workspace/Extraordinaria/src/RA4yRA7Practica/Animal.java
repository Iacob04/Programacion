package RA4yRA7Practica;

public class Animal {
	
	private String nombre;
	private int edad;
	
	public Animal(String nombre, int edad) {
		
		this.nombre = nombre;
		this.edad = edad;
		
		
	}
	
	public Animal(String nombre) {
		
		this.nombre = nombre;
		this.edad = 0;
		
		
	}
	
	public String getNombre() {
		return this.nombre ;
	}
	
	public int getEdad() {
		return this.edad;
	}

}
