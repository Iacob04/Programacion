package boletin_15;

import java.util.ArrayList;

public class Medico  extends Persona{

	
	private String especialidad;
	private String numColegiado;
	
	private ArrayList<Paciente> pacientes = new ArrayList<>();
	
	public Medico(String nombre, String apellido, String especialidad, String numColegiado,Centro_medico centro) {
		
		super(nombre,apellido,centro);
	
		this.especialidad = especialidad;
		this.numColegiado = numColegiado;
		this.centro.anyadeMedico(this);
		
		
	}

	
	public void cambioCentro(Centro_medico c) {
	
			
			this.centro = c;
			this.centro.eliminaMedico(this);
			this.centro.anyadeMedico(this);
		
		
	}
	public void listaConsultas() {
		
		for(Consulta c: consultas)
			c.muestraConsulta();
		
	}
	
}
