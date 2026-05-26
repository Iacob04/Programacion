package orientacionObjetosFInalCurso;

public interface Combate {
	
	int SALUD_MINIMA = 25;
	
	//void fueraDeCombate();
	
	default void fueraDeCombate() {
		System.out.println("El pokemon está fuera de combate");
	}
	
	static void finDelCombate() {
		System.out.println("El combate ha terminado");
	}

}
