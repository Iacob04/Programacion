import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio2 {

    public static void main(String[] args) {
        estadisticas("fichero1.txt");
    }

    public static void estadisticas(String fichero) {

        int numLineas    = 0;
        int lineasBlanco = 0;
        int numChars     = 0;  // caracteres sin espacios
        int numEspacios  = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                numLineas++;

                // Si la línea está vacía es una línea en blanco
                if (linea.isEmpty()) {
                    lineasBlanco++;
                } else {
                    // Recorremos cada carácter de la línea
                    for (int i = 0; i < linea.length(); i++) {
                        if (linea.charAt(i) == ' ') {
                            numEspacios++;
                        } else {
                            numChars++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        System.out.println("Número de líneas: " + numLineas);
        System.out.println("Líneas en blanco: " + lineasBlanco);
        System.out.println("Cantidad de caracteres sin contar los espacios: " + numChars);
        System.out.println("Cantidad de espacios: " + numEspacios);
    }
}
