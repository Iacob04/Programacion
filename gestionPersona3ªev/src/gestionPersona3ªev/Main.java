package gestionPersona3ªev;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		LocalDate f1 = LocalDate.of(2004, 10, 10);
		LocalDate f2 = LocalDate.of(2003, 12, 10);
		Persona p1 = new Persona("Alexandru","Iacob", f1);
		Persona p2 = new Persona("Jordan","Zuñiga",f2);
	}

}
