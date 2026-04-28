import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        tabla();
    }

    public static void tabla() {

        Scanner teclado = new Scanner(System.in);
        int numero = 0;
        boolean correcto = false;

        // Pedimos el número hasta que sea válido (entre 1 y 10)
        while (correcto == false) {
            System.out.print("Introduce un número entre 1 y 10: ");
            try {
                numero = teclado.nextInt();
                if (numero < 1 || numero > 10) {
                    System.out.println("El número tiene que estar entre el 1 y el 10");
                } else {
                    correcto = true;
                }
            } catch (Exception e) {
                System.out.println("Eso no es un número entero");
                teclado.nextLine();  // limpiamos el buffer del Scanner
            }
        }
        teclado.close();

        // El nombre del fichero incluye el número introducido
        String nombreFichero = "tabla-" + numero + ".txt";

        try (PrintWriter pluma = new PrintWriter("/home/alumno/" + nombreFichero)) {

            // %2d reserva 2 caracteres para el número → columnas alineadas
            for (int i = 1; i <= 10; i++) {
                int resultado = numero * i;
                pluma.printf("%2d x %2d = %3d%n", numero, i, resultado);
            }

            System.out.println("Fichero " + nombreFichero + " creado correctamente");

        } catch (Exception e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }
}
