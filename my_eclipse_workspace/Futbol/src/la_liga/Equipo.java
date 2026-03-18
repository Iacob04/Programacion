package la_liga;

import java.util.HashSet;

public class Equipo {
	
	public String nombre;
	public Entrenador entrenador;
	
	private HashSet<Jugador> alineacion = new HashSet<>();
	private int partidosGanados = 0;
	private int partidosEmpatados = 0;
	private int partidosPerdidos = 0;
	private int golesAFavor =0;
	private int golesEnContra = 0;
	private int puntos = 0;
	
	
	public Equipo(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	
	public void anyadeJugador(Jugador jugador) {   
		this.alineacion.add(jugador);
	}
	
	public int getPG() {
		return this.partidosGanados;
	}
	public int getPP() {
		return this.partidosPerdidos;
	}
	public int getPE() {
		return this.partidosEmpatados;
	}
	public int getGA() {
		return this.golesAFavor;
	}
	public int getGE() {
		return this.golesEnContra;
	}
	public int getPts() {
		return this.puntos;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void ganaPartido() {
		this.partidosGanados++;
		this.puntos += 3;
		
	}
	public void pierdePartido() {
		this.partidosPerdidos++;
		
	}
	public void empataPartido() {
		this.partidosEmpatados++;
		this.puntos++;
		
	}
	public void cambiaGoles(int aFavor, int enContra) {
		this.golesAFavor+=aFavor;
		this.golesEnContra+=enContra;
	}
	
	

}
