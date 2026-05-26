import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio10 {

    public static void main(String[] args) {
        analizarDatos("datos.txt");
    }

    public static void analizarDatos(String fichero) {

        int numValidos   = 0;
        int numInvalidos = 0;
        double suma      = 0;
        double minimo    = Double.MAX_VALUE;
        double maximo    = Double.MIN_VALUE;

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                // Intentamos convertir la línea a double
                // Si falla, es un dato inválido
                try {
                    double valor = Double.parseDouble(linea.trim());
                    numValidos++;
                    suma += valor;

                    // Actualizamos mínimo y máximo
                    if (valor < minimo) minimo = valor;
                    if (valor > maximo) maximo = valor;

                } catch (NumberFormatException e) {
                    // No es un número → dato inválido
                    numInvalidos++;
                    System.out.println("Dato inválido encontrado: " + linea);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        System.out.println("Número de datos válidos: " + numValidos);
        System.out.println("Número de datos inválidos: " + numInvalidos);

        if (numValidos > 0) {
            double media = suma / numValidos;
            System.out.println("Mínimo: " + minimo);
            System.out.println("Máximo: " + maximo);
            // La media con máximo 3 decimales
            System.out.printf("Media aritmética: %.3f%n", media);
        }
    }
}
