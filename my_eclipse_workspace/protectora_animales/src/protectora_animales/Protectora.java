package protectora_animales;

public class Protectora {
	private Gatos[] cantidadGatos = new Gatos[3];
	private Perros[] cantidadPerros = new Perros[3];
	private Tortugas[] cantidadTortugas = new Tortugas[3];

	public Protectora(Gatos gato1, Gatos gato2, Gatos gato3) {

		this.cantidadGatos[0] = gato1;
		this.cantidadGatos[1] = gato2;
		this.cantidadGatos[2] = gato3;
	}

	public void mostrarGatos() {
		int contador = 1;
		for (int i = 0; i < 3; i++) {
			if (this.cantidadGatos[i] == null) {
				System.out.println("El Gato " + (i + 1) + " ha sido adoptado");
				contador++;
			} else {
				System.out.println("Datos del Gato: " + contador);
				this.cantidadGatos[i].datosGatos();
				contador++;
				System.out.println("----------------");
			}
		}
	}

	public Protectora(Perros perro1, Perros perro2, Perros perro3) {

		this.cantidadPerros[0] = perro1;
		this.cantidadPerros[1] = perro2;
		this.cantidadPerros[2] = perro3;
	}

	public void mostrarPerros() {
		int contador = 1;
		for (int i = 0; i < 3; i++) {
			if (this.cantidadPerros[i] == null) {
				System.out.println("El perro " + (i + 1) + " ha sido adoptado");
				contador++;
			} else {
				System.out.println("Datos del Perro: " + contador);
				this.cantidadPerros[i].datosPerros();
				contador++;
				System.out.println("----------------");
			}
		}

	}

	public Protectora(Tortugas tortuga1, Tortugas tortuga2, Tortugas tortuga3) {

		this.cantidadTortugas[0] = tortuga1;
		this.cantidadTortugas[1] = tortuga2;
		this.cantidadTortugas[2] = tortuga3;
	}

	public void mostrarTortugas() {
		int contador = 1;
		for (int i = 0; i < 3; i++) {
			if (this.cantidadTortugas[i] == null) {
				System.out.println("La tortuga " + (i + 1) + " ha sido adoptada");
				contador++;
			} else {
				System.out.println("Datos de la Tortuga: " + contador);
				this.cantidadTortugas[i].datosTortugas();
				contador++;
				System.out.println("----------------");
			}
		}

	}

	public void adoptarPerro(Clientes cliente, String nombrePerro) {
		for (int i = 0; i < cantidadPerros.length; i++) {
			if (cantidadPerros[i] != null && cantidadPerros[i].nombre.equals(nombrePerro)) {
				System.out.println(cliente.getNombre() + " ha adoptado al perro " + nombrePerro);
				cantidadPerros[i] = null; // lo eliminamos del array
				System.out.println("------------------");
				return;
			}
		}
		System.out.println("El perro " + nombrePerro + " no está disponible.");
	}

	public void adoptarGato(Clientes cliente, String nombreGato) {
		for (int i = 0; i < cantidadGatos.length; i++) {
			if (cantidadGatos[i] != null && cantidadGatos[i].nombre.equals(nombreGato)) {
				System.out.println(cliente.getNombre() + " ha adoptado al gato " + nombreGato);
				cantidadGatos[i] = null;
				System.out.println("------------------");
				return;
			}
		}
		System.out.println("El gato " + nombreGato + " no está disponible.");
	}

	public void adoptarTortuga(Clientes cliente, String nombreTortuga) {
		for (int i = 0; i < cantidadTortugas.length; i++) {
			if (cantidadTortugas[i] != null && cantidadTortugas[i].nombre.equals(nombreTortuga)) {
				System.out.println(cliente.getNombre() + " ha adoptado a la tortuga " + nombreTortuga);
				cantidadTortugas[i] = null;
				System.out.println("------------------");
				return;
			}
		}
		System.out.println("La tortuga " + nombreTortuga + " no está disponible.");
	}

}
