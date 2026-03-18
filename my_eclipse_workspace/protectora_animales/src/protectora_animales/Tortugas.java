package protectora_animales;

public class Tortugas extends Animales {

	public Tortugas(int edad, String nombre) {

		super(edad, nombre);

	}

	public void datosTortugas() {

		System.out.println("La edad de la tortuga es de: " + this.edad + " años");

		if (this.nombre.equals("null"))
			System.out.println("Sin nombre asignado");
		else {
			System.out.println("Nombre: " + this.nombre);

		}

	}
}
