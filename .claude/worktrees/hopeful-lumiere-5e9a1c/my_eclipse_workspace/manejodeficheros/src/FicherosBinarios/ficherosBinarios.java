package FicherosBinarios;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ficherosBinarios {

	//los datos sattic no se guardan
	//si no queremos guardar la prioridad hay que poner "transient"
	
	public static void main(String[] args) {
		
		String fichero = "/home/alumno/Programacion/my_eclipse_workspace/manejodeficheros/src/FicherosBinarios/binarios.bin";
		//escribirFicheroBinario(fichero);
		//leerFicheroBinario(fichero);
		Tarea t1 = new Tarea ("A33","Aprender a grabar objetos con Java",1,false);
		//grabarTarea(t1, fichero);
		Tarea t2 = new Tarea("B17", "Estudiar colecciones en Java", 2, false);
		Tarea t3 = new Tarea("C05", "Practicar excepciones personalizadas", 1, true);
		Tarea t4 = new Tarea("D89", "Repasar herencia y polimorfismo", 3, false);
		Tarea t5 = new Tarea("E12", "Hacer ejercicios de ficheros", 2, true);
		
		ArrayList<Tarea> lista = new ArrayList <> (List.of(t1,t2,t3,t4,t5));
		
		grabarLista(lista, fichero);
		
		ArrayList<Tarea> listaRecuperada = leerListaTarea(fichero);
		Tarea tnueo = new Tarea("F44", "Implementar menú interactivo con Scanner", 1, false);
		listaRecuperada.add(tnueo);
		grabarLista(listaRecuperada, fichero);
		
		ArrayList<Tarea> listaRecuperadaNueva = leerListaTarea(fichero);
		for(Tarea tarea : listaRecuperadaNueva) {
			tarea.mostararTarea();																																				
		}
			
		/*Tarea trecuerada = leerTarea(fichero);
		if(trecuerada != null) {
		trecuerada.mostararTarea();
		}*/
		
	}
	
	public static ArrayList<Tarea> leerListaTarea(String fichero) {
		ArrayList<Tarea>lista = null;
		
		try(ObjectInputStream binario = new ObjectInputStream(new FileInputStream(fichero))){
			
			lista = (ArrayList<Tarea>)binario.readObject();
			
		}catch(Exception e) {
			System.out.println("Error" + e.getMessage());
		}
		return lista;
		}
	
	public static void grabarLista(ArrayList<Tarea>lista, String fichero) {
		
		try(ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(fichero))){
				
				binario.writeObject(lista);
				
			}catch(Exception e) {
				System.out.println("Error" + e.getMessage());
			}
		}
	
	
	public static void grabarTarea(Tarea tarea, String fichero) {
		
	try(ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(fichero))){
			
			binario.writeObject(tarea);
			
		}catch(Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}
	
	public static Tarea leerTarea(String fichero) {
		Tarea tarea = null;
		
		try(ObjectInputStream binario = new ObjectInputStream(new FileInputStream(fichero))){
			
			tarea = (Tarea)binario.readObject();
			
		}catch(Exception e) {
			System.out.println("Error" + e.getMessage());
		}
		return tarea;
		}
	
	
	public static void escribirFicheroBinario(String fichero) {
		
		try(DataOutputStream binario = new DataOutputStream(new FileOutputStream(fichero))){
			
			binario.writeInt(3456);
			binario.writeDouble(3.123456);
			binario.writeBoolean(false);
			binario.writeChar('X');
			binario.writeUTF("Hola mundo binario");
			
		}catch(Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}
	
	public static void leerFicheroBinario(String fichero) {
		
		try(DataInputStream binario = new DataInputStream(new FileInputStream(fichero))){
			
			System.out.println(binario.readInt());
			System.out.println(binario.readDouble());
			System.out.println(binario.readBoolean());
			System.out.println(binario.readChar());
			System.out.println(binario.readUTF());
			
		}catch(Exception e) {
			System.out.println("Error" + e.getMessage());
		}
		
	}

}
