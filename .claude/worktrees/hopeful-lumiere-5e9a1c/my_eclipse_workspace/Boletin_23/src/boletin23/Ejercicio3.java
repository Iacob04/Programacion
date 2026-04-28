import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el nombre del fichero: ");
        String fichero = teclado.nextLine();
        teclado.close();

        contarPalabras(fichero);
    }

    public static void contarPalabras(String fichero) {

        int numPalabras = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                // trim() elimina espacios al principio y al final de la línea
                linea = linea.trim();

                // Si la línea no está vacía, contamos sus palabras
                if (linea.isEmpty() == false) {
                    // split("\\s+") divide por uno o más espacios seguidos
                    // así evitamos contar vacíos si hay dobles espacios
                    String[] palabras = linea.split("\\s+");
                    numPalabras += palabras.length;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        System.out.println("El fichero contiene " + numPalabras + " palabras");
    }
}
