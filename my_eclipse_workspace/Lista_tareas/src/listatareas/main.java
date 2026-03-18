package listatareas;

public class main {

	public static void main(String[] args) {
		
		String nombreFichero = "listaTareas.txt";
		Tarea t1 = new Tarea("E55","Dar de comer a los peces",8,false);
		Tarea t2 = new Tarea("A33","Ganar la 33",1,false);
		
		Tarea.mostrarTareas();
		Tarea.grabarFicheroTareas(nombreFichero);
		System.out.println("-----------");
		Tarea.ordenarPorBurbuja();
		Tarea.mostrarTareas();
	}

}
