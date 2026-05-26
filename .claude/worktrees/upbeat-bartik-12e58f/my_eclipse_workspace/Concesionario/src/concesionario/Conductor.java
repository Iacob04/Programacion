package concesionario;

import java.time.LocalDate;

public class Conductor {
	protected String nombre;
	protected String NIF;
	protected int anyoNacimiento;
	protected int anyoCarnet;
	protected int puntos;

	public Conductor(String nombre, String NIF, int anyoNacimiento, int anyoCarnet, int puntos) {

		this.nombre = nombre;
		this.NIF = NIF;
		this.anyoNacimiento = anyoNacimiento;
		this.anyoCarnet = anyoCarnet;
		this.puntos = puntos;

	}

	public int obtenerEdad() {

		int anyoActual = LocalDate.now().getYear();
		return anyoActual - anyoNacimiento;

	}

	public int obtener() {

		int anyoActual = LocalDate.now().getYear();
		return anyoActual - anyoNacimiento;

	}
	
	public String getNIF() {
		
		return this.NIF;
	}

	public void datosConductor() {
		
		int anyoActual = LocalDate.now().getYear();
		System.out.println("Datos del conductor; ");
		System.out.println("------------------------------------");
		System.out.println("Nombre: " + this.nombre);
		System.out.println("NIF: " + this.NIF);
		System.out.println("Año de nacimiento: " + this.anyoNacimiento);
		System.out.println("Edad: " + obtenerEdad());
		System.out.println("Año de obtención del carnet: " + this.anyoCarnet);
		if(anyoCarnet == anyoActual) {
			System.out.println("Puntos de carnet: 8");
		}
		else {
			System.out.println("Puntos de carnet: 10");
		}

	}

}
