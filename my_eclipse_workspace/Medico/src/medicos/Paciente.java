package medicos;

import java.util.HashMap;
import java.time.LocalDate;

public class Paciente extends Persona{
	
	private Especialidad especialidad;
	HashMap<String,Cita> listaCitas = new HashMap<>();
	
	
	public Paciente(String nombre, String apellidos,int edad, String DNI) {
		
		super(nombre,apellidos,edad,DNI);
	
	}
	
	public void pideCita(Especialidad especialidad) {
	
		if(this.listaCitas.containsKey(especialidad.getNombre())) {
			//Medico medico = listaCitas.get(especialidad.getNombre());
			Cita cita = listaCitas.get(especialidad.getNombre());
			System.out.println("Este paciente ya tiene una cita para la especialidad "+ especialidad.getNombre() + " con "
		+ cita.getMedico().getNombre()+""+cita.getMedico().getApellido() + " el día "+ cita.getFechaCita());
		}
		
		else if(especialidad.numMedicos()==0)
			System.out.println("No hay medicos de la especialidad: "+ especialidad.getNombre());
		else{
			Medico medico = especialidad.getMedico();
			medico.incrementaContadorCitas();
			Cita cita = new Cita(medico);
			listaCitas.put(especialidad.getNombre(), cita);
			System.out.println("Cita asignada para la especialidad de "+ especialidad.getNombre()+" con "+ medico.getNombre()+
					" "+ medico.getApellido());
		}
			
		}
	public void anulaCita(Especialidad especialidad) {
		
		if(this.listaCitas.containsKey(especialidad.getNombre())) {
			Cita cita = listaCitas.remove(especialidad.getNombre());
			cita.getMedico().decrementaContadorCitas();
			System.out.println("Se ha eliminado la cita con el médico "+ cita.getMedico().getNombre()+ " "+cita.getMedico().getApellido()
					+ " el día "+ cita.getFechaCita()+ " ha sido eliminada");
		}
		else {
			System.out.println("El paciente no tiene ninguna cita asignada a la especialidad "+ especialidad.getNombre());
		}
		
	}
	
	
	

}
