package examen;

public class Pruebas_juego {

	public static void main(String[] args) {
		
		Juego laGamba = new Juego(456);
		laGamba.verJugadores();
		
		laGamba.nuevaPrueba(300);
		laGamba.verJugadores();
		
		laGamba.nuevaPrueba(2);
		laGamba.verJugadores();
		
		laGamba.nuevaPrueba(152);
		laGamba.verJugadores();
		laGamba.nuevaPrueba(1);

	}

}
