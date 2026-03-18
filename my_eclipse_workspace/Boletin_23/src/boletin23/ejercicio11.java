package boletin23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class ejercicio11 {

	public static void main(String[] args) {
		String fichero = "/home/alumno/login.txt";
		HashMap<String,String>diccionario = leerFichero(fichero);
		comprobarUsuario(diccionario);
		nuevoUsuario();
	}
	
	public static HashMap<String, String> leerFichero(String fichero) {
		
		HashMap<String,String>diccionario = new HashMap<>();
		try(BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
			
			String linea;
			
			while((linea = lector.readLine())!= null) {
				int posicion = linea.indexOf(":");
				diccionario.put(linea.substring(0, posicion), linea.substring(posicion+1));
			
			}
			
		}catch(Exception e) {
			
			System.out.println("Fichero inexistente o imposible acceder a el");
		}
		if (diccionario.size() == 0) {
			System.out.println("Fichero vacío");
		}
		return diccionario;
		
		
		
	}
	
	public static void comprobarUsuario(HashMap<String,String> diccionario) {
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Usuario: ");
		String usuario = teclado.nextLine()	;
		System.out.println("Contraseña: ");
		String password = teclado.nextLine();
		teclado.close();
		
		if(diccionario.containsKey(usuario)== false) {
			System.out.println("Usuario no encontrado");
		}
		else if(diccionario.get(usuario).equals(password)== false) {
			System.out.println("La contraseña es incorrrecta");
				
		}
		else {
			System.out.println("Usuario y contraseña correctas");
		}
		
		
	}
	
	public static void nuevoUsuario() {
		
		try(PrintWriter pluma = new PrintWriter("/home/alumno/login.txt")) {
			
			pluma.print("josemaria:abc\n");
			pluma.print("sara:romeo1\n");
			pluma.print("alberto:M4d4g4scar+\n");
			pluma.print("juan:TOPO\n");
			
	
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
	}
}
