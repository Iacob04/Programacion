
package ejercicios;
import java.util.Scanner;
public class Validacion_contraseña {
	
	public static void main(String[] args) {
		

		        Scanner sc = new Scanner(System.in);
		        String contrasena;

		        // Bucle hasta que la contraseña sea válida
		        do {
		            System.out.print("Introduce una contraseña: ");
		            contrasena = sc.nextLine();
		        } while (!esValida(contrasena));

		        // Confirmación de la contraseña
		        String confirmacion;
		        do {
		            System.out.print("Repite la contraseña: ");
		            confirmacion = sc.nextLine();
		            if (!confirmacion.equals(contrasena)) {
		                System.out.println("No coincide, vuelve a intentarlo.");
		            }
		        } while (!confirmacion.equals(contrasena));
		        
		        sc.close();
		        System.out.println("Contraseña válida y confirmada.");
		    }

		    // Método auxiliar para validar la contraseña
		    public static boolean esValida(String pass) {
		        if (pass.length() < 8 || pass.length() > 20) {
		            System.out.println("Error: longitud incorrecta.");
		            return false;
		        }

		        boolean mayus = false, minus = false, numero = false, simbolo = false;
		        String simbolos = "_-!?*";

		        for (int i = 0; i < pass.length(); i++) {
		            char c = pass.charAt(i);
		            if (Character.isUpperCase(c)) mayus = true;
		            else if (Character.isLowerCase(c)) minus = true;
		            else if (Character.isDigit(c)) numero = true;
		            else if (simbolos.indexOf(c) != -1) simbolo = true;
		        }

		        if (!mayus) System.out.println("Error: falta mayúscula.");
		        if (!minus) System.out.println("Error: falta minúscula.");
		        if (!numero) System.out.println("Error: falta número.");
		        if (!simbolo) System.out.println("Error: falta símbolo.");

		        return mayus && minus && numero && simbolo;
		   
		      
	}

}
