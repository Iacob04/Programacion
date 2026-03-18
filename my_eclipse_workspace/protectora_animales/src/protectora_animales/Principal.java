
package protectora_animales;

public class Principal {

	public static void main(String[] args) {

		Clientes cliente1 = new Clientes("Gabriel Alexandru", "Iacob", 21, 642335111, "Perro");

		Perros perro1 = new Perros(3, "Rex", true);
		Perros perro2 = new Perros(6, "Thor", true);
		Perros perro3 = new Perros(2, "null", false);

		Protectora perros = new Protectora(perro1, perro2, perro3);

		// perros.mostrarPerros();

		Gatos gato1 = new Gatos(5, "Miau1", true);
		Gatos gato2 = new Gatos(3, "Miau2", false);
		Gatos gato3 = new Gatos(1, "null", true);

		Protectora gatos = new Protectora(gato1, gato2, gato3);

		// gatos.mostrarGatos();

		Tortugas tortuga1 = new Tortugas(20, "Tortu");
		Tortugas tortuga2 = new Tortugas(10, "Cristina");
		Tortugas tortuga3 = new Tortugas(2, "Tortle");

		Protectora tortugas = new Protectora(tortuga1, tortuga2, tortuga3);
		// tortugas.mostrarTortugas();

		// Cliente adopta a Rex
		gatos.adoptarGato(cliente1, "Miau1");

		System.out.println("Después de la adopción:");
		System.out.println("------------------");
		gatos.mostrarGatos();

	}

}
