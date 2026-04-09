import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio6 {

    public static void main(String[] args) {
        verificarAgenda("agenda.txt");
    }

    public static void verificarAgenda(String fichero) {

        boolean formatoCorrecto = true;
        int numRegistro = 1;

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String nombre;
            // Leemos de tres en tres líneas: nombre, apellido, edad
            while ((nombre = lector.readLine()) != null) {

                String apellido = lector.readLine();
                String edadTxt  = lector.readLine();

                // Comprobamos que las tres líneas existen
                if (apellido == null || edadTxt == null) {
                    System.out.println("Error en registro " + numRegistro + ": faltan líneas");
                    formatoCorrecto = false;
                    break;
                }

                // El nombre y apellido no deben ser numéricos
                boolean nombreEsTexto   = esTexto(nombre);
                boolean apellidoEsTexto = esTexto(apellido);

                // La edad debe ser un entero
                boolean edadEsEntero = esEntero(edadTxt);

                if (nombreEsTexto == false || apellidoEsTexto == false || edadEsEntero == false) {
                    System.out.println("Error en registro " + numRegistro + ": formato incorrecto");
                    formatoCorrecto = false;
                }

                numRegistro++;
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        if (formatoCorrecto) {
            System.out.println("El formato del fichero es correcto");
        }
    }

    // Devuelve true si el String no puede convertirse a número (es texto)
    public static boolean esTexto(String linea) {
        try {
            Double.parseDouble(linea);
            return false;  // sí es número → no debería ser texto → error
        } catch (Exception e) {
            return true;   // no es número → es texto → correcto
        }
    }

    // Devuelve true si el String es un entero válido
    public static boolean esEntero(String linea) {
        try {
            Integer.parseInt(linea);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
