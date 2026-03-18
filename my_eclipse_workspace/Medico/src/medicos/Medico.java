package medicos;

import java.util.ArrayList;


public class Medico extends Persona{
	
	private Especialidad especialidad;
	private ArrayList<Paciente> listaEspera = new ArrayList<>();
	private int contadorCitas = 0;
	
	
	public Medico(String nombre, String apellidos,Especialidad especialidad) {
		
		super(nombre,apellidos);
		this.especialidad = especialidad;
		especialidad.nuevoMedico(this);
		
	}
	
	public void incrementaContadorCitas() {
		this.contadorCitas++;
	}
	
	public void decrementaContadorCitas() {
		this.contadorCitas--;
	}


	
	public int getContadorCitas() {
		return this.contadorCitas;
	}

	
	
	
	
	

}
