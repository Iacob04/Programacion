import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio6 {

    public static void main(String[] args) {

        String fSoluciones = "/home/alumno/soluciones.txt";
        String fRespuestas = "/home/alumno/respuestas.txt";
        String fNotas      = "/home/alumno/notas.txt";

        // Leemos las soluciones. Si hay error no hacemos nada más
        String[] soluciones = leerSoluciones(fSoluciones);
        if (soluciones == null) {
            return;
        }

        // Leemos las respuestas de cada alumno
        HashMap<String, String[]> respuestas = leerRespuestas(fRespuestas);

        // Generamos el fichero de notas
        grabarNotas(fNotas, soluciones, respuestas);
    }

    public static String[] leerSoluciones(String fichero) {

        String linea = null;
        try {
            linea = Files.readString(Path.of(fichero));
        } catch (Exception e) {
            System.out.println("Error al leer soluciones: " + e.getMessage());
            return null;
        }

        // Las soluciones están separadas por ", "
        String[] soluciones = linea.trim().split(",\\s*");

        // Verificamos que solo hay letras A, B, C o D
        for (String sol : soluciones) {
            if (sol.equals("A") == false && sol.equals("B") == false &&
                sol.equals("C") == false && sol.equals("D") == false) {
                System.out.println("Error en fichero de soluciones: valor inválido '" + sol + "'");
                return null;
            }
        }

        return soluciones;
    }

    public static HashMap<String, String[]> leerRespuestas(String fichero) {

        HashMap<String, String[]> diccionario = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                int posicion = linea.indexOf(":");
                if (posicion < 0) {
                    System.out.println("Fila con formato incorrecto, se descarta: " + linea);
                    continue;
                }

                String alumno    = linea.substring(0, posicion).trim();
                String respTxt   = linea.substring(posicion + 2).trim();
                String[] respuestas = respTxt.split(",\\s*");

                diccionario.put(alumno, respuestas);
            }

        } catch (Exception e) {
            System.out.println("Error al leer respuestas: " + e.getMessage());
        }

        return diccionario;
    }

    public static void grabarNotas(String fichero, String[] soluciones,
                                   HashMap<String, String[]> respuestas) {

        try (PrintWriter pluma = new PrintWriter(fichero)) {

            for (Map.Entry<String, String[]> entrada : respuestas.entrySet()) {

                String alumno = entrada.getKey();
                double nota   = calcularNota(soluciones, entrada.getValue());

                System.out.printf("%s: %.1f%n", alumno, nota);
                pluma.printf("%s: %.1f%n", alumno, nota);
            }

        } catch (Exception e) {
            System.out.println("Error al escribir notas: " + e.getMessage());
        }
    }

    public static double calcularNota(String[] soluciones, String[] respuestas) {

        double nota = 0;

        for (int i = 0; i < soluciones.length; i++) {
            // Comparamos solo el primer carácter para evitar problemas de espacios
            if (soluciones[i].charAt(0) == respuestas[i].charAt(0)) {
                nota += 1;
            } else {
                nota -= 0.3;
            }
        }

        // La nota mínima es 0, nunca negativa
        if (nota < 0) {
            nota = 0;
        }

        return nota;
    }
}
