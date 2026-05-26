import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        fibonacci();
    }

    public static void fibonacci() {

        Scanner teclado = new Scanner(System.in);
        int cantidad = 0;
        boolean correcto = false;

        // Pedimos cuántos números de fibonacci queremos
        while (correcto == false) {
            System.out.print("¿Cuántos números de la sucesión de Fibonacci quieres? ");
            try {
                cantidad = teclado.nextInt();
                if (cantidad < 2) {
                    System.out.println("El número debe ser 2 o mayor");
                } else {
                    correcto = true;
                }
            } catch (Exception e) {
                System.out.println("Eso no es un número entero");
                teclado.nextLine();
            }
        }
        teclado.close();

        try (PrintWriter pluma = new PrintWriter("fibonacci.txt")) {

            // Los dos primeros de fibonacci siempre son 0 y 1
            long anterior  = 0;
            long actual    = 1;

            // Escribimos el primero sin coma delante
            pluma.print(anterior);

            if (cantidad >= 2) {
                pluma.print(", " + actual);
            }

            // A partir del tercero, cada número es la suma de los dos anteriores
            for (int i = 2; i < cantidad; i++) {
                long siguiente = anterior + actual;
                pluma.print(", " + siguiente);
                // Desplazamos: el actual pasa a ser el anterior
                anterior = actual;
                actual   = siguiente;
            }

            System.out.println("Fichero fibonacci.txt creado correctamente");

        } catch (Exception e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }
}
