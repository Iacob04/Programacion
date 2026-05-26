package boletin_15;

public class Paciente extends Persona{
	
	
	private String DNI;
	private int telefono;


	
	public Paciente(String nombre, String apellido, String DNI, int telefono,Centro_medico centro) {
		
		super(nombre,apellido,centro);
		
		this.DNI = DNI;
		this.telefono = telefono;
		this.centro.anyadePaciente(this);
		
	}

	public void mostrarPaciente() {
		
		System.out.println("El paciente: "+this.nombre+" "+this.apellidos+ " Con DNI: "+ this.DNI + 
				" y Num de Tel: "+ this.telefono+ " Pertenece al Centro médico: "+ this.centro);
		
		
	}

	
	public void cambioCentro(Centro_medico c) {
		

		this.centro = c;
		this.centro.eliminaPaciente(this);
		this.centro.anyadePaciente(this);
		
	}
	
	
}
