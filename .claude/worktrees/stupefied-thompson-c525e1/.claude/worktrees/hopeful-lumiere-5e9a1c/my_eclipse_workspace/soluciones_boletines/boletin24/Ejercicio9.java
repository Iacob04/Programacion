import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio9 {

    public static void main(String[] args) {

        String fichero = "/home/alumno/empleados.txt";

        // Leemos todas las líneas del fichero en una lista
        ArrayList<String> lineas = leerFichero(fichero);

        if (lineas == null || lineas.size() == 0) {
            System.out.println("El fichero está vacío o no existe");
            return;
        }

        // Pedimos la edad para cada persona y añadimos el campo al final
        ArrayList<String> lineasConEdad = añadirEdades(lineas);

        // Sobreescribimos el mismo fichero con las líneas actualizadas
        escribirFichero(fichero, lineasConEdad);
    }

    public static ArrayList<String> leerFichero(String fichero) {

        ArrayList<String> lista = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                lista.add(linea);
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return null;
        }

        return lista;
    }

    public static ArrayList<String> añadirEdades(ArrayList<String> lineas) {

        Scanner teclado = new Scanner(System.in);
        ArrayList<String> lineasConEdad = new ArrayList<>();

        for (String linea : lineas) {

            // El formato es: Apellidos, Nombre;Puesto;Salario
            // Separamos los campos por ;
            String[] campos = linea.split(";");

            // El primer campo tiene "Apellidos, Nombre"
            // Separamos por ", " para obtener apellidos y nombre por separado
            int posicionComa = campos[0].indexOf(",");
            String apellidos = campos[0].substring(0, posicionComa).trim();
            String nombre    = campos[0].substring(posicionComa + 2).trim();

            // Mostramos "Nombre Apellidos" (nombre primero, luego apellidos)
            System.out.print(nombre + " " + apellidos + ". ¿Cuál es su edad? ");

            // Pedimos la edad y validamos que sea un entero entre 18 y 66
            int edad = 0;
            boolean edadValida = false;

            while (edadValida == false) {
                try {
                    edad = Integer.parseInt(teclado.nextLine());
                    if (edad < 18 || edad >= 67) {
                        System.out.print("La edad debe estar entre 18 y 66 años: ");
                    } else {
                        edadValida = true;
                    }
                } catch (Exception e) {
                    System.out.print("Eso no es un número entero. Introduce la edad: ");
                }
            }

            // Añadimos el campo de edad al final de la línea original
            lineasConEdad.add(linea + ";" + edad);
        }

        teclado.close();
        return lineasConEdad;
    }

    public static void escribirFichero(String fichero, ArrayList<String> lineas) {

        // Sin true en el FileWriter → sobreescribe el fichero original
        try (PrintWriter pluma = new PrintWriter(fichero)) {

            for (String linea : lineas) {
                pluma.println(linea);
            }

            System.out.println("Fichero actualizado correctamente");

        } catch (Exception e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}
