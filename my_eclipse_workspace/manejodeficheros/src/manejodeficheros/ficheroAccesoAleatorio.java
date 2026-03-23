package manejodeficheros;

import java.io.RandomAccessFile;
import java.util.HashMap;




public class ficheroAccesoAleatorio {
	
	static final int TAMANYO_NOMBRE = 20;
	static final int TAMANYO_REGISTRO = (TAMANYO_NOMBRE *2)+4;

	public static void main(String[] args) {
		String fichero = "agenda.dat";
		HashMap<String, Integer> agenda = new HashMap<>();
		agenda.put("Alonso", 33);
		agenda.put("Kevin", 25);
		agenda.put("Mario", 18);
		agenda.put("Pini", 19);
		
		
		try {
			crearAgenda(fichero,agenda);
			
			leerRegistro(fichero,2);
			leerRegistro(fichero,5);
			modificarRegistro(fichero,2,"Ana María", 15);
			nuevoRegistro(fichero, "Jose", 34);
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		
		
	}
	
	public static void nuevoRegistro(String fichero, String nombre, int edad) throws Exception{
		
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			raf.seek(raf.length());
			escribirNombre(raf, nombre);
			raf.writeInt(edad);
			System.out.println("Registro añadidio correctamente");
			
		}
		
	}
	
	public static void crearAgenda(String fichero , HashMap<String, Integer>agenda)throws Exception {
		
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			
		for(String nombre: agenda.keySet()) {
			int edad = agenda.get(nombre);
			escribirNombre(raf,nombre);
			raf.writeInt(edad);
			
			
			}
			System.out.println("Agenda creada. Tamaño: "+ raf.length() + " bytes");
		}
	}
	
	public static void escribirNombre(RandomAccessFile raf, String nombre) throws Exception {
		
		char[] chars = new char[TAMANYO_NOMBRE];
		for(int i=0; i<TAMANYO_NOMBRE; i++) {
			if(i<nombre.length()) {
				chars[i] = nombre.charAt(i);
			}
			else {
				chars[i] = ' ';
			}
		}
		for(char c: chars) {
			raf.writeChar(c);
		}
	}
	
	public static void modificarRegistro(String fichero, int registro, String nombre, int edad) throws Exception{
		
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			long posicion = TAMANYO_REGISTRO * (registro-1);
			if(posicion>=raf.length()) {
				System.out.println("El registro "+registro+" no existe");
				System.out.println("El registro más alto es el "+ raf.length()/TAMANYO_REGISTRO);
			}
			else {
				raf.seek(posicion);
				escribirNombre(raf,nombre);
				raf.writeInt(edad);
				System.out.println("Registro " + registro + " modificado correctamente");
			}
		}
		
	}
	
	public static void leerRegistro(String fichero, int registro) throws Exception {
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "r")){
			long posicion = TAMANYO_REGISTRO * (registro-1);
			if(posicion>=raf.length()) {
				System.out.println("El registro "+registro+" no existe");
				System.out.println("El registro más alto es el "+ raf.length()/TAMANYO_REGISTRO);
			}
			else {
				raf.seek(posicion);
				String nombre = leerNombre(raf);
				int edad = raf.readInt();
				System.out.printf("Registro: %d - Nombre: %s. Eadd: %d\n",registro,nombre,edad);
			}
		}
	}
	
	public static String leerNombre(RandomAccessFile raf) throws Exception{
		
		String nombre = "";
		for(int i=0; i<TAMANYO_NOMBRE; i++) {
			char c = raf.readChar();
			nombre = nombre +c;
		}
		return nombre.trim();
		
	}

}
