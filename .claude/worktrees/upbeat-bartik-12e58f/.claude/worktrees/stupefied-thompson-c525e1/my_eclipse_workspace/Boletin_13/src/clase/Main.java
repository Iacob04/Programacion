package clase;

public class Main {
	public static void main(String[] args) {

		Profesor profesor1 = new Profesor("Jose María", "Morales", "Informatica");

		Alumno alumno1 = new Alumno("Gabriel Alexandru", "Iacob", 21, "Desarrollo de aplicaciones multiplataforma",
				"DAM1");

		Modulo programacion = new Modulo("Programación", 8, 1, false);
		Modulo python = new Modulo("Python", 3, 2, true);
		Modulo fundamentos = new Modulo("Fundamentos de Programación", 2, 1, true);

		Ciclo dam = new Ciclo("Desarrollo de aplicaciones multiplataforma", "SUPERIOR");
		dam.anadeModulo(programacion);
		dam.anadeModulo(fundamentos);
		dam.anadeModulo(python);

		Grupo dam1 = new Grupo("DAM1", dam, 1, 4);
		dam1.anadeTutor(profesor1);
		dam1.anadeAlumno(alumno1);

		// Mostrar información completa del grupo
		dam1.verGrupo();
	}
}
