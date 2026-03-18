package boletin_24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class ejercicio7 {

	public static void main(String[] args) {
		String fichero = "/home/alumno/login.txt";
		HashMap<String,String>diccionario = leerFichero(fichero);
		nuevoUsuario(diccionario, fichero);
		diccionario = leerFichero(fichero);
		System.out.println(diccionario);
		comprobarUsuario(diccionario);
		
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
	
	
	
	public static void nuevoUsuario(HashMap<String, String> diccionario, String fichero) {
		
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Nuevo Usuario: ");
		String usuario = teclado.nextLine()	;
		System.out.println("Contraseña: ");
		String password = teclado.nextLine();
		System.out.println("Repita la Contraseña: ");
		String passwordRepetida = teclado.nextLine();
		teclado.close();
		
		if(password.equals(passwordRepetida)== false) {
			System.out.println("Las contraseñas no coinciden");
			
		}
		else if(diccionario.containsKey(usuario)== true) {
			System.out.println("Ese usuario ya existe");
		}
		else if(usuario.indexOf(":")>=0 || password.indexOf(":")>=0) {
			System.out.println("Ni el usuario ni la contraseña ueden contener el caracter ':'");
		}
		
		else grabarEnFichero(usuario,password,fichero);
		
	}

	public static void grabarEnFichero(String usuario, String password, String fichero) {
		System.out.println("Grabando fichero");
		
		try(PrintWriter pluma = new PrintWriter(new FileWriter(fichero,true))) {
			
			pluma.printf("%s:%s\n",usuario,password);
			
	
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	public static void comprobarUsuario(HashMap<String,String> diccionario) {
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Usuario: ");
		String usuario = teclado.nextLine();
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
}
