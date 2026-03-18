
package ejercicios;

public class Generador_de_matrículas {

	public static void main(String[] args) {
		
		String letras = "BCDFGHJKLMNPKRSTUVWXYZ";
		
		for (int i = 0; i<10; i++ ) {
			int numero = (int)(Math.random()*9000)+1000;
			 String letrasFinal = "";

	            for (int j = 0; j < 3; j++) {

	                char letra = letras.charAt((int)(Math.random() * letras.length()));
	                letrasFinal += letra;
	            }

	            System.out.println(numero + " " + letrasFinal);
		}

	}

}
