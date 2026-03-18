package la_liga;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Competicion {
	
	private String nombre;
	
	HashSet<Equipo> listaEquipos = new HashSet<>();
	
	
	public Competicion(String nombre) {
		this.nombre = nombre;
	}
	
	public void anyadeEquipo(Equipo equipo) {
		listaEquipos.add(equipo);
	}
	
	public void anyadeEquipos(HashSet<Equipo> equipos){
		listaEquipos.addAll(equipos);
	}
	
	public void verClasificacion() {
		LocalDate fecha = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
		String fechaHoy = fecha.format(formato);
		
		System.out.println("------------------------------------------------------------------");
		System.out.println(" Competición: "+ this.nombre + " | Clasificación a día " + fechaHoy);
		System.out.println("------------------------------------------------------------------");
		System.out.printf("%-25s | %3s | %2s | %2s | %2s | %2s | %3s | %3s","EQUIPO","Pts",
				"PJ","PG","PE","PP","GE","GC");
		System.out.println();
		System.out.println("------------------------------------------------------------------");
		
		// Ordenar lista de equipos
		ArrayList<Equipo> listaEquiposOrdenada = this.ordenarClasificacion();
		
		for(Equipo e : listaEquiposOrdenada) {
			int PJ = e.getPG() + e.getPE() + e.getPP();
			System.out.printf("%-25s | %3d | %2d | %2d | %2d | %2d | %3d | %3d\n",
					e.getNombre(),e.getPts(),PJ,e.getPG(),e.getPE(),e.getPP(),e.getGA(),e.getGE());
		}
	}
	
	public ArrayList<Equipo> ordenarClasificacion() {
		ArrayList<Equipo> desordenada = new ArrayList<>(this.listaEquipos);
		ArrayList<Equipo> ordenada = new ArrayList<>();
		
		while(desordenada.size()!=0) {
			int puntosMayor = -1;
			Equipo elQueMasPtsTiene = null;
			for(Equipo equipo : desordenada)
				if(equipo.getPts() > puntosMayor) {
					puntosMayor = equipo.getPts();
					elQueMasPtsTiene = equipo;
				}else if(equipo.getPts() == puntosMayor) {
					int dif1 = elQueMasPtsTiene.getGA() - elQueMasPtsTiene.getGE();
					int dif2 = equipo.getGA() - equipo.getGE();
					if(dif2 > dif1)
						elQueMasPtsTiene = equipo;
				}
			desordenada.remove(elQueMasPtsTiene);
			ordenada.add(elQueMasPtsTiene);
		}
		
		/*// Ordenar por puntos descendente, luego por diferencia de goles, etc.
		Collections.sort(ordenada, new Comparator<Equipo>() {
			
			public int compare(Equipo e1, Equipo e2) {
				// Primero por puntos (descendente)
				if (e1.getPts() != e2.getPts()) {
					return Integer.compare(e2.getPts(), e1.getPts());
				}
				// Si empatan en puntos, por diferencia de goles (descendente)
				int difGoles1 = e1.getGA() - e1.getGE();
				int difGoles2 = e2.getGA() - e2.getGE();
				if (difGoles1 != difGoles2) {
					return Integer.compare(difGoles2, difGoles1);
				}
				// Si aún empatan, por goles a favor (descendente)
				if (e1.getGA() != e2.getGA()) {
					return Integer.compare(e2.getGA(), e1.getGA());
				}
				// Finalmente por nombre (ascendente)
				return e1.getNombre().compareTo(e2.getNombre());
			}
		});*/
		
		return ordenada;
	}
	
	public Equipo decideCualVaAntes(Equipo equipo1, Equipo equipo2) {
		
		Equipo elegido = null;
		
		if(equipo2 == null)
			elegido = equipo1;
		else if(equipo1.getPts() > equipo2.getPts()) {
			elegido = equipo1;
		}else if (equipo1.getPts() == equipo2.getPts()) {
			int dif1 = equipo1.getGA() - equipo1.getGE();
			int dif2 = equipo2.getGA() - equipo2.getGE();
			if(dif2>dif1)
				elegido = equipo2;
			else
				elegido = equipo1;
		}else
			elegido = equipo2;
		return elegido;
	}
	
	
	
	
}