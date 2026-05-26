import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio4 {

    public static void main(String[] args) {
        darLaVuelta("origen.txt", "destino.txt");
    }

    public static void darLaVuelta(String origen, String destino) {

        ArrayList<String> listaOrigen  = leerFichero(origen);
        ArrayList<String> listaDestino = new ArrayList<>();

        if (listaOrigen == null) {
            return;
        }

        // Recorremos la lista del origen de atrás hacia adelante
        // y a cada línea le invertimos también el contenido
        for (int i = listaOrigen.size() - 1; i >= 0; i--) {
            String lineaInvertida = invertirContenido(listaOrigen.get(i));
            listaDestino.add(lineaInvertida);
        }

        escribirFichero(destino, listaDestino);
    }

    public static ArrayList<String> leerFichero(String fichero) {

        ArrayList<String> lista = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Path.of(fichero));
            lista = new ArrayList<>(lineas);
        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return null;
        }
        return lista;
    }

    public static String invertirContenido(String linea) {

        String invertida = "";
        // Vamos cogiendo caracteres de atrás hacia adelante
        for (int i = linea.length() - 1; i >= 0; i--) {
            invertida = invertida + linea.charAt(i);
        }
        return invertida;
    }

    public static void escribirFichero(String fichero, ArrayList<String> lista) {

        try {
            Files.write(Path.of(fichero), lista);
            System.out.println("Fichero " + fichero + " creado correctamente");
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}
