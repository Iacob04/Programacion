import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String fichero = "";
        boolean existe = false;

        // Seguimos pidiendo el fichero hasta que exista
        while (existe == false) {
            System.out.print("Introduce el nombre del fichero: ");
            fichero = teclado.nextLine();
            // Intentamos abrir el fichero para ver si existe
            try {
                new BufferedReader(new FileReader(fichero)).close();
                existe = true;
            } catch (Exception e) {
                System.out.println("El fichero " + fichero + " no existe");
            }
        }

        System.out.print("Introduce la palabra a buscar: ");
        String palabra = teclado.nextLine();
        teclado.close();

        contarLineasYPalabra(fichero, palabra);
    }

    public static void contarLineasYPalabra(String fichero, String palabra) {

        int numLineas = 0;
        int numVeces  = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                numLineas++;

                // Dividimos la línea en palabras por espacios y comparamos
                String[] palabras = linea.split(" ");
                for (String p : palabras) {
                    if (p.equals(palabra)) {
                        numVeces++;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        System.out.println("El fichero tiene " + numLineas + " líneas");
        System.out.println("La palabra " + palabra + " aparece " + numVeces + " veces");
    }
}
