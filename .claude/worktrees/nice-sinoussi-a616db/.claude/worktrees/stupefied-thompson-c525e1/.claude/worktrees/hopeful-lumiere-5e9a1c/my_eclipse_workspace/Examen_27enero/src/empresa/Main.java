package empresa;

public class Main {

	public static void main(String[] args) {
		
		Programadores p1 = new Programadores ("Alexandru Iacob", 001, 3550.80,true,true	);
		p1.mostrarProgramador();
		Programadores p2 = new Programadores ("Felipe Lotas", 004, 3550.80,true,false	);
		p2.mostrarProgramador();
		Programadores p3 = new Programadores ("Rosa Flor", 005, 3550.80,false,true	);
		p3.mostrarProgramador();
		Programadores p4 = new Programadores ("Sara Garcia", 006, 3550.80,false,false	);
		p4.mostrarProgramador();
		System.out.println("---------------------------------------------------------------------------------------");
		
		Jefes_proyecto j1 = new Jefes_proyecto("Mario Carcalete", 002,4500.90);
		j1.mostrarJefes();
		Jefes_proyecto j2 = new Jefes_proyecto("Sergio Serrano", 003,4500.90);
		j2.mostrarJefes();
		System.out.println("----------------------------------------------------------------------------------------");
		
		Proyecto proyecto1 = new Proyecto ("PRO-001",j1,"Software de gestion de empleos.");
		proyecto1.mostrarProyecto();
		Proyecto proyecto2 = new Proyecto ("PRO-002",4,j2,"Software de gestion de despidos.");
		proyecto2.mostrarProyecto();
	}

}
