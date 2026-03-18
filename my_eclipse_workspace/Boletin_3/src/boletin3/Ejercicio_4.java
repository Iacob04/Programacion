
package boletin3;
import java.util.Scanner;
public class Ejercicio_4 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		
		System.out.println("Introduce una cadena de texto y el programa eliminara los espacios que haya en ella");
		String texto = teclado.nextLine();
	
		String sinEspacio = "";
		int contador = 0;
				for (int i=0; i<texto.length(); i++)
					if(texto.charAt(i) != ' ')
					{
						sinEspacio = sinEspacio + texto.charAt(i);
					
					}
					else 
						contador++;
				
				System.out.println("Cadena sin espacios :" + sinEspacio );
				System.out.println("Numero de espacios eliminados :" + contador);
				
		
		
		
		teclado.close();
		
		

	}

}
