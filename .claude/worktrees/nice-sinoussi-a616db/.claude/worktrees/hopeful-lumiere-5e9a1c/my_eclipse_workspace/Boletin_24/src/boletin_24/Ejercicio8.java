import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Ejercicio8 {

    public static void main(String[] args) {

        String origen  = "/home/alumno/origen.txt";
        String destino = "/home/alumno/salida.txt";

        verificarSintaxis(origen, destino);
    }

    public static void verificarSintaxis(String origen, String destino) {

        try (BufferedReader lector = new BufferedReader(new FileReader(origen));
             PrintWriter pluma = new PrintWriter(destino)) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                if (lineaEsCorrecta(linea)) {
                    // La línea cumple el formato → la escribimos en el destino
                    pluma.println(linea);
                } else {
                    // La línea es incorrecta → la mostramos por consola
                    System.out.println("Línea incorrecta: " + linea);
                }
            }

            System.out.println("Proceso completado. Las líneas correctas están en: " + destino);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean lineaEsCorrecta(String linea) {

        // El formato es: Apellidos, Nombre;Puesto;Salario
        String[] partes = linea.split(";");

        // Deben ser exactamente 3 campos separados por ;
        if (partes.length != 3) {
            return false;
        }

        String campoNombre  = partes[0];
        String campoPuesto  = partes[1];
        String campoSalario = partes[2];

        // --- Validar campo de nombre: "Apellidos, Nombre" ---
        // Debe contener exactamente una coma
        int posicionComa = campoNombre.indexOf(",");
        if (posicionComa < 0) {
            return false;
        }

        String apellidos = campoNombre.substring(0, posicionComa).trim();
        String nombre    = campoNombre.substring(posicionComa + 1).trim();

        // Apellidos y nombre solo pueden tener letras y espacios (sin números)
        if (soloLetrasYEspacios(apellidos) == false) return false;
        if (soloLetrasYEspacios(nombre)    == false) return false;
        if (apellidos.isEmpty() || nombre.isEmpty()) return false;

        // --- Validar puesto: no puede estar vacío ---
        if (campoPuesto.trim().isEmpty()) {
            return false;
        }

        // --- Validar salario: debe ser un número (con o sin decimales) ---
        try {
            double salario = Double.parseDouble(campoSalario.trim());
            if (salario < 0) return false;  // salario negativo no tiene sentido
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // Devuelve true si el String solo tiene letras (a-z, A-Z) y espacios
    public static boolean soloLetrasYEspacios(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c) == false && c != ' ') {
                return false;
            }
        }
        return true;
    }
}
