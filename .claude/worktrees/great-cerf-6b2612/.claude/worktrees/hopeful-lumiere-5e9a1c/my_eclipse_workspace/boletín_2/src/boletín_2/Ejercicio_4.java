
package boletín_2;
import java.util.Scanner;
public class Ejercicio_4 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		double nota1;
		double nota2;
		double media;
		
		System.out.println("Calcula la media aritmética con la nota de los dos examenes");
		
		System.out.println("Introduce la nota del primer examen");
		nota1 = teclado.nextDouble();
		
		while (nota1<0 || nota1>10) {
			System.out.println("La nota del examen no puede ser inferior a 0 o superiror a 10, vuelve a introducir la nota");
			nota1 = teclado.nextDouble();
		}
		
		System.out.println("Introduce la nota del segundo examen");
		nota2 = teclado.nextDouble();
		
		while (nota2<0 || nota2>10) {
			System.out.println("La nota del examen no puede ser inferior a 0 o superiror a 10, vuelve a introducir la nota");
			nota2 = teclado.nextDouble();
		}
		
		media = (nota1 +nota2)/2 ;
		System.out.println("La media de tus dos notas sin redodndeo es de : " + media );
		media = (int)Math.round(media);
		System.out.println("La media final de tus dos notas es de : " + media + " sobre 10 puntos");
		
		if(media<5) {
			System.out.println("Estas suspenso, ¡Vas a recuperacion!");
		}
		else {
			System.out.println("Estas aprobado, ¡ENHORABUENA!");
		}
		teclado.close();
	}

}
