package orientacionObjetosFInalCurso;

import java.io.Serializable;

public class Pokemon implements Serializable, Comparable<Pokemon>{
	private int codigo;
	private String nombre;
	private String[] tipo = new String[2];
	private int salud = 100;
	
	
	public Pokemon(int codigo, String nombre , String tipo1) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo[0] = tipo1;
		this.tipo[1] = null;
	}
	public Pokemon(int codigo, String nombre , String tipo1, String tipo2) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo[0] = tipo1;
		this.tipo[1] = tipo2;
	}
	
	public void fueraDeCombate() {
		this.salud = 0;
	}
	
	@Override
	public String toString() {
		return "(" + this.codigo + ") " + this.nombre;
	}
	
	@Override
	public boolean equals(Object otro) {
		Pokemon otroPokemon = (Pokemon) otro;
		boolean iguales = false;
		
		if (this.codigo == otroPokemon.codigo) 
			iguales = true;
		return iguales;
	}
	
	@Override
	public int compareTo(Pokemon otro) {
		int devolver = 0;
		if (this.codigo>otro.codigo)
			devolver = 1;
		else if(this.codigo<otro.codigo)
			devolver = -1;
		return devolver;
	}
}


