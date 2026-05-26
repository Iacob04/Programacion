
package default1;

import java.util.Scanner;

public class Ejercicio_13 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int dados;
		int caras_dados;
		
			System.out.print("Escribe cuantos dados deseas lanzar");
			dados = teclado.nextInt();
				
			System.out.print("Escribe cuantas caras tienen los dados");
			caras_dados = teclado.nextInt();
			
			while(caras_dados%2 != 0) {
				
				System.out.println("No existen dados con un nÃºmero de caras impares");
				System.out.print("Escribe cuantas caras tienen los dados");
				
			caras_dados = teclado.nextInt();
			
			}
			
				System.out.println("Vamos a tirar " + dados + " dados de " + caras_dados + " caras");
			for(int i=0; i<dados; i++){
				int dado = (int)(Math.random()*caras_dados)+1;
				System.out.println(dado);
			}
			
			
		
			teclado.close();

	}

}
