import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio7 {

    public static void main(String[] args) {

        String fichero = "/home/alumno/login.txt";

        // Cargamos el fichero en un HashMap para comprobar duplicados
        HashMap<String, String> usuarios = leerFichero(fichero);

        nuevoUsuario(usuarios, fichero);
    }

    public static HashMap<String, String> leerFichero(String fichero) {

        HashMap<String, String> diccionario = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                int posicion = linea.indexOf(":");
                diccionario.put(linea.substring(0, posicion), linea.substring(posicion + 1));
            }

        } catch (Exception e) {
            // Si no existe el fichero simplemente empezamos con el diccionario vacío
            System.out.println("El fichero no existe, se creará uno nuevo");
        }

        return diccionario;
    }

    public static void nuevoUsuario(HashMap<String, String> usuarios, String fichero) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el nombre del usuario: ");
        String usuario = teclado.nextLine();

        System.out.print("Introduce la contraseña: ");
        String password = teclado.nextLine();

        System.out.print("Vuelve a introducir la contraseña de nuevo: ");
        String passwordRepetida = teclado.nextLine();
        teclado.close();

        // Validamos que las contraseñas coinciden
        if (password.equals(passwordRepetida) == false) {
            System.out.println("Las contraseñas no son iguales. No se puede grabar la nueva cuenta");
            return;
        }

        // Validamos que el usuario no existe ya
        if (usuarios.containsKey(usuario)) {
            System.out.println("Ese usuario ya existe");
            return;
        }

        // Validamos que ni usuario ni contraseña contienen ':'
        if (usuario.contains(":") || password.contains(":")) {
            System.out.println("Ni el usuario ni la contraseña pueden contener el carácter ':'");
            return;
        }

        // Grabamos en el fichero en modo añadir (true)
        try (PrintWriter pluma = new PrintWriter(new FileWriter(fichero, true))) {
            pluma.printf("%s:%s%n", usuario, password);
            System.out.println("Cuenta de usuario grabada correctamente");
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}
