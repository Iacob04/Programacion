package boletin23;

		import java.io.BufferedReader;
		import java.io.FileReader;

		public class Ejercicio_2 {

		    public static void main(String[] args) {
		        estadisticas("fichero1.txt");
		    }

		    public static void estadisticas(String nombreFichero) {

		        try (BufferedReader lector = new BufferedReader(new FileReader(nombreFichero))) {

		            String linea;
		            int contadorLineas    = 0;
		            int lineasEnBlanco    = 0;
		            int caracteres        = 0;
		            int espacios          = 0;

		            while ((linea = lector.readLine()) != null) {

		                contadorLineas++;

		                if (linea.isEmpty()) {
		                    lineasEnBlanco++;
		                }

		                for (int i = 0; i < linea.length(); i++) {
		                    char c = linea.charAt(i);
		                    if (c == ' ') {
		                        espacios++;
		                    } else {
		                        caracteres++;
		                    }
		                }
		            }

		            System.out.println("Número de líneas: "                         + contadorLineas);
		            System.out.println("Líneas en blanco: "                         + lineasEnBlanco);
		            System.out.println("Cantidad de caracteres sin contar espacios: "+ caracteres);
		            System.out.println("Cantidad de espacios: "                      + espacios);

		        } catch (Exception e) {
		            System.out.println("Error con el fichero: " + e.getMessage());
		        }
		    }
		}

	


