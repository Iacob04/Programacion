
package boletín_2;
import java.util.Scanner;
public class Ejercicio_6 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		double nota1; //Trabajo en clase
		double nota2; // Deberes
		double nota3; //Examen
		double media;
		
		System.out.println("Calcula la media ponderada del trimestre");
		System.out.println("Ten en cuenta que la nota del trabajo en clase cuenta el 5% de la nota final, los ejercicios prácticos el 15% y el examen 80%");
		
		System.out.println("Introduce la nota del trabajo en clase");
		nota1 = teclado.nextDouble();
		
		while (nota1<0 || nota1>10) {
			System.out.println("La nota del trabajo en clase no puede ser inferior a 0 o superiror a 10, vuelve a introducir la nota");
			nota1 = teclado.nextDouble();
		}
		
		System.out.println("Introduce la nota de los ejercicios prácticos");
		nota2 = teclado.nextDouble();
		
		while (nota2<0 || nota2>10) {
			System.out.println("La nota de los ejercicios prácticos no puede ser inferior a 0 o superiror a 10, vuelve a introducir la nota");
			nota2 = teclado.nextDouble();
		}
		
		System.out.println("Introduce la nota del examen");
		nota3 = teclado.nextDouble();
		
		while (nota3<0 || nota3>10) {
			System.out.println("La nota del examen no puede ser inferior a 0 o superiror a 10, vuelve a introducir la nota");
			nota3 = teclado.nextDouble();
		}
		
		nota1 = nota1*0.05;
		nota2 = nota2*0.15;
		nota3 = nota3*0.8;
		media = nota1+nota2+nota3 ;
		
		System.out.println("La media de tus notas sin redodndeo es de : " + media );
		
		if (media >= 5) {
            media = Math.round(media); // redondeo al entero más cercano
        } else {
            media = Math.floor(media); // truncar decimales si es < 5
        }
		
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
