package la_liga;

import java.util.ArrayList;
import sun.swing.SwingAccessor;
import java.util.HashSet;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		Competicion laLiga = new Competicion ("La Liga EA Sports");
		
		Equipo AtleticoMadrid = new Equipo ("Atletico de Madrid");
		Equipo RealMadrid = new Equipo ("Real Madrid FC");
		Equipo Barça = new Equipo ("Barcelona FC");
		Equipo Rayo = new Equipo ("Rayo Vallecano");
		
		HashSet<Equipo> listaEquipos = new HashSet<>(List.of(Barça,Rayo	));
		
		laLiga.anyadeEquipo (RealMadrid);
		laLiga.anyadeEquipo (AtleticoMadrid);
		laLiga.anyadeEquipos(listaEquipos);
		
		Jugador jugador1 = new Jugador ("Cristiano Ronaldo", 7, RealMadrid);
		Jugador jugador2 = new Jugador ("Messi");
		
		
		Entrenador entrenador1 = new Entrenador ("Cholo Simeone", AtleticoMadrid);
		Entrenador entrenador2 = new Entrenador ("Xabi Alonso", RealMadrid);
		
		Arbitro arbitro1 = new Arbitro ("Alexandru");
		Arbitro arbitro2 = new Arbitro ("Andrea");
		
		Partido p1 = new Partido(AtleticoMadrid,RealMadrid);
		p1.Resultado(3, 1);
		Partido p2 = new Partido(AtleticoMadrid,Barça);
		p2.Resultado(4, 1);
		Partido p3 = new Partido(RealMadrid,Barça);
		p3.Resultado(3, 6);
		Partido p4 = new Partido(RealMadrid,Rayo);
		p4.Resultado(3, 6);
		
		laLiga.verClasificacion();
		
		
		/*ArrayList<Integer> numeros = new ArrayList(List.of(7,5,1,3,14,5,4,6));
		
		//ArrayList<Integer> ordenada1 = ordenarPorSeleccion(numeros);
		//System.out.println(ordenada1);
		ArrayList<Integer> ordenada2 = ordenarPorBurbuja(numeros);
		System.out.println(ordenada2);		*/
	}
	
		/*public static ArrayList<Integer> ordenarPorBurbuja(ArrayList<Integer> desordenada){
		ArrayList<Integer> ordenada = new ArrayList<>();
		boolean hayCambios = true;
		while(hayCambios == true) {
			hayCambios=false;
		for(int i=0; i<desordenada.size()-1; i++) {
			//System.out.println(desordenada.get(i)+" - "+ desordenada.get(i+1) );
			
			if (desordenada.get(i+1)> desordenada.get(i)) {
				
				int n =desordenada.remove(i);
				desordenada.add(i+1,n);
				hayCambios=true;
				
			}
		}
			
		}
		System.out.println(desordenada);
		return ordenada;
		
		
	}*/
	
		
		/*public static ArrayList<Integer> ordenarPorSeleccion(ArrayList<Integer> desordenada){
			ArrayList<Integer> ordenada = new ArrayList<>();
		
			while(desordenada.size()!=0){
				
			int mayor = -1;
			for(int n:desordenada)
				if(n>mayor)
					mayor =n;
			desordenada.remove((Integer)mayor);
			ordenada.add(mayor);
			
			}
			return ordenada;
		}*/
		

	

}
