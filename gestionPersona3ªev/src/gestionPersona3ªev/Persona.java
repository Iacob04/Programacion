package gestionPersona3ªev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona implements Baja , Jubilacion, Accidente{

	
	private int añosCotizados = 0;
	private String nombre;
	private String apellidos;
	private int edad;
	private LocalDate fechaNacimiento;
	//boolean estaDeBaja;
	private LocalDate fechaJubilacion;
	private LocalDate fechaBaja;
	private LocalDate fechaFallecimiento;
	
	public Persona(String nombre , String apellidos , LocalDate nacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = nacimiento;
		
		
	}
	
	@Override
	public String toString() {
		String linea1 = "Cotizante: " + this.apellidos+ ", " + this.nombre + "\n";
		String linea2 = "";
		String linea3 = "";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		if(this.fechaFallecimiento != null) {
			String fechaFormateada = this.fechaFallecimiento.format(formato);
			linea2 = "Estado: fallecimiento. Fecha de defuncion: " + fechaFormateada + "\n";
		}
		else if(this.fechaJubilacion != null) {
			String fechaFormateada = this.fechaJubilacion.format(formato);
			linea2 = "Estado: jubilado. Fecha de jubilacion: " + fechaFormateada + "\n";
		}
		else if(this.fechaBaja != null) {
			String fechaFormateada = this.fechaBaja.format(formato);
			linea2 = "Estado: de Baja. Fecha de jubilacion: " + fechaFormateada + "\n";
		}
		else {
			linea2 = "Estado: En Activo";
		}
		return (linea1+linea2+linea3);
	}
	
}
