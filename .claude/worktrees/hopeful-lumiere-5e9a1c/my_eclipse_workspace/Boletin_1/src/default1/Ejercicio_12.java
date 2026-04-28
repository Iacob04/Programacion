
package default1;

import java.util.Scanner;

public class Ejercicio_12 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int dados;
		int caras_dados;
		int contador_dados = 0;
		
			System.out.print("Escribe cuantos dados deseas lanzar");
			dados = teclado.nextInt();
				
			System.out.print("Escribe cuantas caras tienen los dados");
			caras_dados = teclado.nextInt();
			
			System.out.println("Vamosd a tirar " + dados + " dados de " + caras_dados + " caras");
			
			for(int i=0; i<dados; i++){
				int dado = (int)(Math.random()*caras_dados)+1;
				System.out.println(dado);
			
			teclado.close();
			}

	}

}
