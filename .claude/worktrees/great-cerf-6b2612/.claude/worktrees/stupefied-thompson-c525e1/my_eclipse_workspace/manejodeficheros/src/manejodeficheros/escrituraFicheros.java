package manejodeficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class escrituraFicheros {

	public static void main(String[] args) {
		
		//escribir1();
		//escribir2();
		//escribir3();
		//escribir4();
		escribir5();
	}
	
	public static void escribir1() {
		
		try(FileWriter pluma = new FileWriter("/home/alumno/ficheroprubas.txt",true);) {
			
			//FileWriter pluma = new FileWriter("/home/alumno/ficheroprubas.txt",true);
			
			pluma.write("Hola mundo en un fichero\n");
			pluma.write("Segunda linea\n");
			pluma.close();
			
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	public static void escribir2() {
		
		try(BufferedWriter pluma = new BufferedWriter(new FileWriter("/home/alumno/ficheroprubas.txt",true))) {
			
			pluma.write("Hola mundo en un fichero");
			pluma.newLine();
			pluma.write("Segunda linea");
			pluma.newLine();
	
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	public static void escribir3() {
		
		try(PrintWriter pluma = new PrintWriter(new FileWriter("/home/alumno/ficheroprubas.txt",true))) {
			
			pluma.print("Hola mundo en un fichero");
			pluma.println(" Sigo en la primera linea");
			pluma.println("Segunda Linea");
			String nombre = "Ana";
			String apellido = "Campos Moro";
			int edad = 37;
			double salario = 2345.39;
			pluma.printf("Nombre: %s, %s. Edad: %d. Sueldo: %.2f",apellido,nombre,edad,salario);
	
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	public static void escribir4() {
		
		Path ruta = Paths.get("/home/alumno/ficheroprubas.txt");
		ArrayList<String> lineas = new ArrayList <> (List.of("Primera línea", "Segunda línea", "Tercera línea"));
		try{
			
			Files.write(ruta, lineas, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	public static void escribir5() {
		
		Path ruta = Paths.get("/home/alumno/ficheroprubas.txt");
		String contenido = "Hola mundo. Último método de escritura :)\n";
		try{
			
			Files.writeString(ruta, contenido, StandardCharsets.UTF_8);
			Files.writeString(ruta, contenido, StandardCharsets.UTF_8,StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	

}
