package boletin_15;

import java.util.ArrayList;


public class Centro_medico {
	
	private String nombre;
	private String codigo;
	
	private ArrayList<Paciente> pacientes = new ArrayList<>();
	private ArrayList<Medico> medicos= new ArrayList<>();
	private ArrayList<Consulta> consultas = new ArrayList<>();
	
	public Centro_medico(String nombre, String codigo) {
	
		this.codigo = codigo;
		this.nombre = nombre;
	
	}
	
	public void anyadeMedico(Medico m) {
		
		this.medicos.add(m);
	}
	
	public void eliminaMedico(Medico m) {
		
		this.medicos.remove(m);
		
	}

	
	public void anyadePaciente(Paciente p) {
		
		this.pacientes.add(p);
	}
	
	public void eliminaPaciente(Paciente p) {
		
		this.pacientes.remove(p);
		
	}
	
	public void listaMedicos() {
		
		for(Medico m: medicos) {
			System.out.println(m.getNombre()+ " "+ m.getApellido()); 
			
		}
	}
		
		public void listaPacientes() {
			
			for(Paciente p: pacientes) {
				
				System.out.println(p.getNombre()+ " "+ p.getApellido());
			}
				
			
			}
			
		public void anyadeConsulta(Consulta c) {
				
				consultas.add(c);
				
			}
		
		public void listaConsultas() {
			
			for(Consulta c: consultas)
				c.muestraConsulta();
			
		}
			
		
		
	
	
	
	
	
	

}
