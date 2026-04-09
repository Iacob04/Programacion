import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio4 {

    public static void main(String[] args) {

        if (compararFicheros("fichero1.txt", "fichero2.txt")) {
            System.out.println("El contenido de los ficheros es el mismo");
        } else {
            System.out.println("El contenido de los ficheros no es el mismo");
        }
    }

    public static boolean compararFicheros(String fichero1, String fichero2) {

        String contenido1 = null;
        String contenido2 = null;

        // Leemos los dos ficheros como String y comparamos
        try {
            contenido1 = Files.readString(Path.of(fichero1));
            contenido2 = Files.readString(Path.of(fichero2));
        } catch (Exception e) {
            System.out.println("Error al leer los ficheros: " + e.getMessage());
            return false;
        }

        // equals compara el contenido carácter a carácter
        return contenido1.equals(contenido2);
    }
}
