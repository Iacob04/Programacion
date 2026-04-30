
package ejercicios;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validar_Correo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // Expresión regular para validar correos
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Creamos el patrón
        Pattern pattern = Pattern.compile(regex);

        System.out.print("Introduce un correo electrónico: ");
        String correo = sc.nextLine();

        // Creamos el matcher
        Matcher matcher = pattern.matcher(correo);

        if (matcher.matches()) {
            System.out.println("El correo es válido.");
        } else {
            System.out.println("El correo NO es válido.");
        }

        sc.close();

	}

}
