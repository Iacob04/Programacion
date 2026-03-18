package empresa;

import java.util.HashSet;

public class Proyecto {

	public String nombre;
	public int participantes = 0;
	public Jefes_proyecto jefe;
	public String descripcion;
	public Programadores programador;
	
	private HashSet<Programadores> programadores = new HashSet<>();
	
	
	public Proyecto (String nombre, int participantes,Jefes_proyecto jefe,String descripcion) {
		
		this.descripcion = descripcion;
		this.nombre=nombre;
		this.participantes=participantes;
		this.jefe=jefe;
		
	}
	public Proyecto (String nombre,Jefes_proyecto jefe,String descripcion) {
		
		this.descripcion = descripcion;
		this.nombre=nombre;
		this.jefe = jefe;
			
	}
	
	/*public anyadeProgramadores(Programadores programador) {
		
		
		
		this.programadores.add(p);
	}*/
	
	public void mostrarProyecto() {
		if(participantes == 0) {
			System.out.println("Proyecto: "+ nombre + ". "+ descripcion + " Jefe de Proyectos:" + jefe.getNombre());
		}
		else {
			System.out.println("Proyecto: "+ nombre + ". "+ descripcion + " Jefe de Proyectos:" + jefe.getNombre()+ 
					" Desarrolladores asignados: "+ participantes);
		}
		
	}
	
	public void mostrarProgramadoresProyecto() {
		
		
	}
	
	

	
	
}
