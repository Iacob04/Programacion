import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio11 {

    public static void main(String[] args) {

        String fichero = "/home/alumno/login.txt";

        // Cargamos el fichero en un HashMap: clave=usuario, valor=contraseña
        HashMap<String, String> usuarios = leerFichero(fichero);

        // Si usuarios es null hubo un error al leer el fichero
        if (usuarios == null) {
            return;
        }

        // Si el diccionario está vacío, el fichero existe pero no tiene datos
        if (usuarios.size() == 0) {
            System.out.println("El fichero de usuarios está vacío");
            return;
        }

        comprobarLogin(usuarios);
    }

    public static HashMap<String, String> leerFichero(String fichero) {

        HashMap<String, String> diccionario = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                // Separamos por : para obtener usuario y contraseña
                int posicion = linea.indexOf(":");
                String usuario     = linea.substring(0, posicion);
                String contrasena  = linea.substring(posicion + 1);
                diccionario.put(usuario, contrasena);
            }

        } catch (Exception e) {
            // El fichero no existe o no se puede acceder
            System.out.println("Fichero inexistente o imposible acceder a él");
            return null;
        }

        return diccionario;
    }

    public static void comprobarLogin(HashMap<String, String> usuarios) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Usuario: ");
        String usuario = teclado.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = teclado.nextLine();
        teclado.close();

        if (usuarios.containsKey(usuario) == false) {
            System.out.println("Usuario no encontrado");
        } else if (usuarios.get(usuario).equals(contrasena) == false) {
            System.out.println("Contraseña incorrecta");
        } else {
            System.out.println("Login correcto. Bienvenido, " + usuario);
        }
    }
}
