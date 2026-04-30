import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Ejercicio8 {

    // Las tres categorías válidas como constante
    static final String[] CATEGORIAS_VALIDAS = {"Familia", "Amigo", "Conocido"};

    static class Contacto {
        String nombre;
        String apellido;   // puede ser null si no tiene apellido
        String categoria;
        int edad;

        public Contacto(String nombre, String apellido, String categoria, int edad) {
            this.nombre    = nombre;
            this.apellido  = apellido;
            this.categoria = categoria;
            this.edad      = edad;
        }

        public void mostrar() {
            String nombreCompleto = (apellido != null) ? nombre + " " + apellido : nombre;
            System.out.println(nombreCompleto + " [" + categoria + "] - " + edad + " años");
        }
    }

    public static void main(String[] args) {

        ArrayList<Contacto> agenda = leerAgenda("agenda.txt");

        System.out.println("Contactos cargados: " + agenda.size());
        for (Contacto c : agenda) {
            c.mostrar();
        }
    }

    public static ArrayList<Contacto> leerAgenda(String fichero) {

        ArrayList<Contacto> lista = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                // La primera línea es siempre el nombre (texto no numérico)
                String nombre = linea;
                String apellido   = null;
                String categoria  = null;
                String edadTxt    = null;

                // Leemos la siguiente línea: puede ser apellido o categoría
                String siguiente = lector.readLine();
                if (siguiente == null) {
                    System.out.println("Registro incompleto, se descarta: " + nombre);
                    break;
                }

                // Si la siguiente línea es una categoría válida, no hay apellido
                if (esCategoriaValida(siguiente)) {
                    categoria = siguiente;
                } else {
                    // Es el apellido, la siguiente será la categoría
                    apellido = siguiente;
                    String sigCategoria = lector.readLine();
                    if (sigCategoria == null || esCategoriaValida(sigCategoria) == false) {
                        System.out.println("Categoria invalida en registro: " + nombre + ", se descarta");
                        continue;
                    }
                    categoria = sigCategoria;
                }

                // La siguiente línea es la edad
                edadTxt = lector.readLine();
                if (edadTxt == null || esEntero(edadTxt) == false) {
                    System.out.println("Edad invalida en registro: " + nombre + ", se descarta");
                    continue;
                }

                int edad = Integer.parseInt(edadTxt);
                lista.add(new Contacto(nombre, apellido, categoria, edad));
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        return lista;
    }

    public static boolean esCategoriaValida(String linea) {
        for (String cat : CATEGORIAS_VALIDAS) {
            if (cat.equals(linea)) {
                return true;
            }
        }
        return false;
    }

    public static boolean esEntero(String linea) {
        try {
            Integer.parseInt(linea);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
