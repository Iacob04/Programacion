import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Ejercicio7 {

    // Clase interna para guardar los datos de cada contacto
    static class Contacto {
        String nombre;
        String apellido;
        int edad;

        public Contacto(String nombre, String apellido, int edad) {
            this.nombre   = nombre;
            this.apellido = apellido;
            this.edad     = edad;
        }

        public void mostrar() {
            System.out.println(nombre + " " + apellido + " - " + edad + " años");
        }
    }

    public static void main(String[] args) {

        ArrayList<Contacto> agenda = leerAgenda("agenda.txt");

        // Mostramos todos los contactos correctos que se han leído
        System.out.println("Contactos cargados correctamente: " + agenda.size());
        for (Contacto c : agenda) {
            c.mostrar();
        }
    }

    public static ArrayList<Contacto> leerAgenda(String fichero) {

        ArrayList<Contacto> lista = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String nombre;
            while ((nombre = lector.readLine()) != null) {

                String apellido = lector.readLine();
                String edadTxt  = lector.readLine();

                // Solo añadimos el contacto si las tres líneas son válidas
                if (apellido == null || edadTxt == null) {
                    System.out.println("Registro incompleto, se descarta: " + nombre);
                    break;
                }

                if (esTexto(nombre) && esTexto(apellido) && esEntero(edadTxt)) {
                    int edad = Integer.parseInt(edadTxt);
                    lista.add(new Contacto(nombre, apellido, edad));
                } else {
                    System.out.println("Registro con formato incorrecto, se descarta: " + nombre);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        return lista;
    }

    public static boolean esTexto(String linea) {
        try {
            Double.parseDouble(linea);
            return false;
        } catch (Exception e) {
            return true;
        }
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
