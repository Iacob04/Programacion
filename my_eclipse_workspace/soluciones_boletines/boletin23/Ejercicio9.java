import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Ejercicio9 {

    public static void main(String[] args) {
        analizarNotas("Redes.txt");
    }

    public static void analizarNotas(String fichero) {

        // Nombre del módulo = nombre del fichero sin extensión
        String nombreModulo = fichero.replace(".txt", "");

        // Lista de alumnos con todo aprobado
        ArrayList<String> aprobados = new ArrayList<>();

        // Para cada RA (5 en total), guardamos los suspensos
        ArrayList<String>[] suspensosPorRA = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            suspensosPorRA[i] = new ArrayList<>();
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                // Separamos nombre y notas por ": "
                int posicion = linea.indexOf(":");
                String alumno  = linea.substring(0, posicion).trim();
                String notasTxt = linea.substring(posicion + 2).trim();

                // Separamos las notas por coma y espacio
                String[] notasStr = notasTxt.split(",\\s*");

                boolean todoAprobado = true;

                for (int i = 0; i < notasStr.length; i++) {
                    double nota = Double.parseDouble(notasStr[i].trim());
                    // Suspenso si la nota es menor que 5
                    if (nota < 5) {
                        suspensosPorRA[i].add(alumno);
                        todoAprobado = false;
                    }
                }

                if (todoAprobado) {
                    aprobados.add(alumno);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        // Mostramos los resultados
        System.out.println("Módulo: " + nombreModulo);
        System.out.println("Alumnos/as con todo aprobado:");
        if (aprobados.isEmpty()) {
            System.out.println("  Ninguno");
        } else {
            for (String a : aprobados) {
                System.out.println(a);
            }
        }

        System.out.println("Resultados de aprendizaje y alumnos suspensos:");
        for (int i = 0; i < 5; i++) {
            if (suspensosPorRA[i].isEmpty()) {
                System.out.println("RA" + (i + 1) + ": Todos aprobados");
            } else {
                // Construimos la lista de suspensos separada por comas
                String suspensos = "";
                for (int j = 0; j < suspensosPorRA[i].size(); j++) {
                    suspensos += suspensosPorRA[i].get(j);
                    if (j < suspensosPorRA[i].size() - 1) {
                        suspensos += ", ";
                    }
                }
                System.out.println("RA" + (i + 1) + ": " + suspensos);
            }
        }
    }
}
