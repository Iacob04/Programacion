package protectora_animales;

abstract class Animales {

	protected int edad;
	protected String nombre = null;
	protected boolean vacunado;

	public Animales(int edad, String nombre, boolean vacunado) {

		this.edad = edad;
		this.nombre = nombre;
		this.vacunado = vacunado;
	}

	public Animales(int edad, String nombre) {

		this.edad = edad;
		this.nombre = nombre;

	}

}
