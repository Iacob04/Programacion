import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int numero = 0;
        boolean correcto = false;

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
                teclado.nextLine();
            }
        }
        teclado.close();

        String nombreFichero = "/home/alumno/tabla-" + numero + ".txt";

        // Intentamos leer el fichero. Si no existe, informamos al usuario
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreFichero))) {

            System.out.println("Tabla de multiplicar del " + numero + ":");
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (Exception e) {
            System.out.println("El fichero tabla-" + numero + ".txt no existe");
            System.out.println("Genera primero la tabla con el ejercicio 1");
        }
    }
}
