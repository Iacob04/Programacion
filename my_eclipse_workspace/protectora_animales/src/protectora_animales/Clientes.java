package protectora_animales;

public class Clientes {

	private String nombre;
	private String apellido;
	private int edad;
	private int telefono;
	private String animalInteresado;

	public String getNombre() {
		return this.nombre;
	}

	public Clientes(String nombre, String apellido, int edad, int telefono, String animalInteresado) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.telefono = telefono;
		this.animalInteresado = animalInteresado;

	}

	public void datosClientes() {

		if (this.animalInteresado.equals("Perro") || this.animalInteresado.equals("Gato")
				|| this.animalInteresado.equals("Tortuga")) {

			System.out.println("Nombre completo: " + this.nombre + " " + this.apellido);
			System.out.println("Edad: " + this.edad + " año");
			System.out.println("Número de teléfono: " + this.telefono);
			System.out.println("Animal Interesado: " + this.animalInteresado);

		} else {
			System.out.println("El animal que estás buscando no lo tenemo en nuestro centro");

		}
	}

}
