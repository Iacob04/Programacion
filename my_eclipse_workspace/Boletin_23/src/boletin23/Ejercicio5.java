import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio5 {

    public static void main(String[] args) {
        estadisticas("estadisticas.txt");
    }

    public static void estadisticas(String fichero) {

        int numHombres = 0;
        int numMujeres = 0;
        double sumaAlturas = 0;
        int totalPersonas  = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                // El formato es: sexo en una línea, altura en la siguiente
                // Por eso leemos de dos en dos
                if (linea.equals("Hombre")) {
                    numHombres++;
                } else if (linea.equals("Mujer")) {
                    numMujeres++;
                }

                // La siguiente línea es la altura
                String lineaAltura = lector.readLine();
                if (lineaAltura != null) {
                    sumaAlturas += Double.parseDouble(lineaAltura);
                    totalPersonas++;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        double media = sumaAlturas / totalPersonas;

        System.out.println("Hombres: " + numHombres + ".");
        System.out.println("Mujeres: " + numMujeres + ".");
        System.out.printf("Estatura media: %.2f%n", media);
    }
}
