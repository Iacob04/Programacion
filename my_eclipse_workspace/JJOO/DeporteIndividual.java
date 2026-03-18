package JJOO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DeporteIndividual extends Deporte{

	private HashMap <Jugador,Double> listaJugadores=new HashMap<>();
	
	public DeporteIndividual(String nombre) {
		super(nombre);
		
	}

	public void resultado(Jugador j, Double d) {
		if(this.listaJugadores.containsKey(j)) {
			if(this.listaJugadores.get(j)<d)
				this.listaJugadores.put(j, d);
		}
		else
			this.listaJugadores.put(j, d);
	}

	public void obtenerPodium() {
		System.out.printf("Medallero %s\n", this.nombre);
		System.out.println("-------------------------------");

		HashMap <Jugador,Double> copia=new HashMap<>(this.listaJugadores);
		
		obtenerMedalla("ORO",copia);
		obtenerMedalla("PLATA",copia);
		obtenerMedalla("BRONCE",copia);
	}
	public void obtenerMedalla(String medalla,HashMap<Jugador,Double>lista) {
		System.out.println(medalla);
		if(lista.size()!=0) {
			Jugador j = obtenerMayor(lista);
			double mayor=lista.get(j);
			System.out.printf("%s con %.2f puntos\n",j.getNombre(),mayor);
			lista.remove(j);
			Iterator<Map.Entry<Jugador,Double>>iterator=lista.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<Jugador, Double>elemento = iterator.next();
				if(elemento.getValue()==mayor){
					System.out.printf("%s con %.2f puntos\n",elemento.getKey().getNombre(),mayor);
					iterator.remove();
				}
			}
		}else
			System.out.println("No hay participantes en esta competición");
	}
	public Jugador obtenerMayor(HashMap<Jugador,Double>lista) {
		double mayor=-1;
		Jugador jMayor=null;
		for(Jugador j: lista.keySet())
			if(lista.get(j)>mayor) {
				mayor=lista.get(j);
				jMayor = j;
			}
		return jMayor;
	}

}
