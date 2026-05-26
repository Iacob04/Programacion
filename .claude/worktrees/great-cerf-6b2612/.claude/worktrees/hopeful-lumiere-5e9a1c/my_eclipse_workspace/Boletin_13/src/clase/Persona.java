package clase;

abstract class Persona {

	protected String nombre;
	protected String apellido;
	private int edad;
	private static int numPersonas = 0;

	// Constructor sin edad
	public Persona(String nom, String ape) {
		this.nombre = nom;
		this.apellido = ape;
		numPersonas++;
	}

	// Constructor con edad
	public Persona(String nom, String ape, int edad) {
		this.nombre = nom;
		this.apellido = ape;
		this.edad = edad;
		numPersonas++;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static int getNumPersonas() {
		return numPersonas;
	}

	public String getNombreCompleto() {
		return this.nombre + " " + this.apellido;
	}

	public String getNombre() {
		return this.apellido + " , " + this.nombre;
	}

	public int getEdad() {
		return this.edad;
	}
}
