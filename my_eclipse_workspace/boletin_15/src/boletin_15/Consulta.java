package boletin_15;

import java.time.LocalDate;
import java.util.ArrayList;

public class Consulta {
	
	
	
	private Medico medico;
	private Paciente paciente;
	private LocalDate fecha;
	private String sintomas;
	private String remedio;
	
	
	public Consulta(Paciente p, Medico m, LocalDate f, String s, String r) {
		
		this.paciente = p;
		this.medico = m;
		this.fecha = f;
		this.sintomas = s;
		this.remedio = r;
		
		
		Centro_medico centro =this.medico.getCentro();
		centro.anyadeConsulta(this);
		this.paciente.anyadeConsulta(this);
		this.medico.anyadeConsulta(this);
		
	}
	
	public void muestraConsulta() {
		
		System.out.println("("+ this.fecha + ") - Doctor: "+ this.medico.getApellido());
		System.out.println("Paciente: "+ this.paciente.getApellido());
		System.out.println("Sintomas: "+ this.sintomas);
		System.out.println("Diagnóstico: "+ this.remedio);
		System.out.println("--------------------------------------");
		
		
		
		
	}

}
