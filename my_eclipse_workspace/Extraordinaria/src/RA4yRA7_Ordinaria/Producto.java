package RA4yRA7_Ordinaria;

public class Producto {

	private String nombre ;
	private double precio;
	
	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	
	public String getNombre() {
		return this.nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	@Override
    public String toString() {

        return "Producto: " + nombre + "\n"
             + String.format("Precio Base: %.2f€", precio);
    }
	
}
