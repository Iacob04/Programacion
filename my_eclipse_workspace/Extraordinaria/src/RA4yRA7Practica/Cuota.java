package RA4yRA7Practica;

public class Cuota {
	
	private String tipo;
	private double precioMensual;
	
	public Cuota(String tipo , double precoMensual) {
		this.precioMensual = precoMensual;
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	public double getPrecioMensual() {
		return this.precioMensual;
	}
	
	@Override
    public String toString() {

        return "Cuota: " + tipo + "\n"
             + String.format("Precio Mensual: %.2f€", precioMensual);
    }

}
