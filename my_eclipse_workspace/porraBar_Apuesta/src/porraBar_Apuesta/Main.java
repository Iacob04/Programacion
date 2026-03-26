package porraBar_Apuesta;

import java.io.RandomAccessFile;
import java.util.HashMap;

public class Main {
	
	static final int numBoletos = 100;
	static final int TAMANYO_NOMBRE = 30;
	static final int TAMANYO_REGISTRO = TAMANYO_NOMBRE*2;
	private int numero;
	private String nombre;

	public static void main(String[] args) {
		
		String fichero = "porraBar.dat";
		
		try {
			crearFichero(fichero);
			apuestaPorNumero(6,"Alexandru", fichero);
			listaParticipantes(fichero);
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		
		
		

	}
	
	public static void crearFichero(String fichero)throws Exception{
		
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			
			for (int i = 1; i<= numBoletos; i++) {
				raf.writeInt(i);
			}
			
			
			
		}
		
	}
	
	
	public static void apuestaPorNumero(int numero , String nombre, String fichero)throws Exception {
		
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			raf.seek(raf.length());
			escribirNombre(raf, nombre);
			raf.writeInt(numero);
			System.out.println("Registro añadidio correctamente");
			
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

}
