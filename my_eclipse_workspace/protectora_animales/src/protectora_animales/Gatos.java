package protectora_animales;

public class Gatos extends Animales {

	public Gatos(int edad, String nombre, boolean vacunado) {

		super(edad, nombre, vacunado);

	}

	public void datosGatos() {

		System.out.println("La edad del gato es de: " + this.edad + " años");

		if (this.nombre.equals("null"))
			System.out.println("Sin nombre asignado");
		else {
			System.out.println("Nombre: " + this.nombre);

		}

		if (this.vacunado == false) {
			System.out.println("El gato no está vacunado");

		} else {
			System.out.println("El gato está vacunado");
		}

	}

}
