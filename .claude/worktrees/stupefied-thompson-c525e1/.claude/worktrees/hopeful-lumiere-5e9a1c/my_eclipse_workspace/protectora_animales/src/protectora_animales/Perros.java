package protectora_animales;

public class Perros extends Animales {

	public Perros(int edad, String nombre, boolean vacunado) {

		super(edad, nombre, vacunado);

	}

	public void datosPerros() {

		System.out.println("La edad del perro es de: " + this.edad + " años");

		if (this.nombre.equals("null"))
			System.out.println("Sin nombre asignado");
		else {
			System.out.println("Nombre: " + this.nombre);

		}

		if (this.vacunado == false) {
			System.out.println("El perro no está vacunado");

		} else {
			System.out.println("El perro está vacunado");
		}

	}

}
