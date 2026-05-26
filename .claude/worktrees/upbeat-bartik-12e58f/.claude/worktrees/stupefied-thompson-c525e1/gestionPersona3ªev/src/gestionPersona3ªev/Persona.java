package gestionPersona3ªev;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Persona implements Baja , Jubilacion, Accidente{

	
	private int anyosCotizados = 0;
	private String nombre;
	private String apellidos;
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
	
	public Persona(String nombre , String apellidos , LocalDate nacimiento, int anyosCotizados) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = nacimiento;	
		this.anyosCotizados = anyosCotizados; 
	}
	
	@Override
	public void cosultaJubilacion() {
		if(this.fechaJubilacion !=null)
			System.out.println("El cotizante ya está jubilado");
		
		else if ( this.fechaFallecimiento != null)
			System.out.println("El cotizante ha fallecido");
		else {
			LocalDate hoy = LocalDate.now()	;
			Period edad = Period.between(this.fechaNacimiento, hoy);
			if(edad.getYears()< EDAD_MINIMA_JUBILACION ) {
				System.out.println("El cotizante no puede jubilarse sin penalización económica");
				System.out.printf("le faltan %d años\n", Jubilacion.EDAD_MINIMA_JUBILACION - edad.getYears());
			}
			else if (this.anyosCotizados >= Jubilacion.ANYOS_MINIMOS_COTIZADOS_100) {
				System.out.println("El cotizante puede jubilarse sin penalización");
			}
			else if (this.anyosCotizados >= Jubilacion.ANYOS_MINIMOS_COTIZADOS_50) {
				System.out.println("El cotizante puede jubilarse con penalizacion de 50%");
				
			}
			else
				System.out.println("Insuficientes años cotizados");
		}
		
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

	
	@Override
	public void darDeBaja() {
		
		if(this.fechaJubilacion !=null)
			System.out.println("El cotizante ya está jubilado");
		
		else if ( this.fechaFallecimiento != null)
			System.out.println("El cotizante ha fallecido");
		else if (this.fechaBaja != null)
			System.out.println("El cotizante ya esta de baja");
		else {
			fechaBaja = LocalDate.now()	;
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			System.out.printf("El cotizante %s  %s se ha dado de baja con fecha de %s. ", this.nombre, this.apellidos, this.fechaBaja.format(formato));
		}
		
	}


	
}
