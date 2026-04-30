package medicos;

import java.util.ArrayList;
import java.util.Collections;

public class Especialidad {
	
	private String nombre;
	private Medico medico;
	private ArrayList<Medico> listaMedicos = new ArrayList<>();
	
	
	public Especialidad (String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public void nuevoMedico(Medico medico) {
		
		this.listaMedicos.add(medico);
		
	}
	
	public void listarMedicos() {
		
		System.out.println("Medicos de la especialidad: "+ this.nombre);
		if(this.listaMedicos.size()==0)
			System.out.println("No hay ningun médico de esta especialidad.");
		for(Medico medico:this.listaMedicos)
			System.out.println(medico.getNombre()+"  "+medico.getApellido()+" ("+medico.getContadorCitas()+") ");
		
	}

	public String getNombre() {
		
		return this.nombre;
	}
	
	public int numMedicos() {
		return this.listaMedicos.size();
	}
	
	public Medico getMedico() {
		
		Medico medico = null;
		if(this.listaMedicos.size()==1)
			medico = listaMedicos.get(0);
		else {
			//1- Encontrar el menor numero de citas de unoo o unos medicos
			int menor = Integer.MAX_VALUE;
			for(Medico m:listaMedicos)
				if(m.getContadorCitas()<menor)
					menor = m.getContadorCitas();
			
			//2- Encontrar cuantos medicos tienen ese número de citas menor
			ArrayList<Medico> medicosConMenosCitas = new ArrayList<>();
			for(Medico m:listaMedicos)
				if(m.getContadorCitas()==menor)
					medicosConMenosCitas.add(m);
			
			//3- Elegir uno de ellos
			Collections.shuffle(medicosConMenosCitas);
			medico = medicosConMenosCitas.get(0);
			
			
		}
		return medico;
	}

	
	
	
	

}
