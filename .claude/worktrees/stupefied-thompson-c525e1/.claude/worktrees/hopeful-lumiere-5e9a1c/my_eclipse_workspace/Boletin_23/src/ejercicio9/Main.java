package ejercicio9;

public class Main {

	public static void main(String[] args) {
		
		String fichero = "src/ejercicio9/redes.txt";
		String ficheroBinario = "src/ejercicio9/Redes.bin";
		
		
		Alumno.leerAlumnos(fichero);
		Alumno.procesarNotasAlumnos(fichero);
		Alumno.salvarAlumnosBinario(ficheroBinario);
	}

}
