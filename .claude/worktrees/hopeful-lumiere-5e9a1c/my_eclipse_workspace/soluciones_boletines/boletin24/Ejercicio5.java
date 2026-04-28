import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {
        listaCompra("compra.txt");
    }

    public static void listaCompra(String fichero) {

        Scanner teclado = new Scanner(System.in);
        boolean seguir    = true;
        int     totalArt  = 0;
        double  totalPrecio = 0;

        // Abrimos el fichero en modo añadir para ir escribiendo artículo a artículo
        try (PrintWriter pluma = new PrintWriter(fichero)) {

            while (seguir) {

                System.out.print("Introduce un artículo: ");
                String articulo = teclado.nextLine();

                // Validamos la cantidad (debe ser número)
                double cantidad = 0;
                boolean cantidadValida = false;
                while (cantidadValida == false) {
                    System.out.print("Introduce la cantidad: ");
                    try {
                        cantidad = Double.parseDouble(teclado.nextLine());
                        cantidadValida = true;
                    } catch (Exception e) {
                        System.out.println("La cantidad debe ser un número");
                    }
                }

                // Validamos el precio (debe ser número positivo)
                double precio = 0;
                boolean precioValido = false;
                while (precioValido == false) {
                    System.out.print("Introduce el precio: ");
                    try {
                        precio = Double.parseDouble(teclado.nextLine());
                        if (precio <= 0) {
                            System.out.println("El precio debe ser mayor que 0");
                        } else {
                            precioValido = true;
                        }
                    } catch (Exception e) {
                        System.out.println("El precio debe ser un número");
                    }
                }

                // Escribimos la línea en el fichero
                pluma.printf("%.1f %s%n", cantidad, articulo);
                totalArt++;
                totalPrecio += cantidad * precio;

                // Preguntamos si quiere seguir añadiendo artículos
                System.out.print("¿Quieres seguir introduciendo artículos en la lista (si/no)? ");
                String respuesta = teclado.nextLine();
                if (respuesta.equalsIgnoreCase("no")) {
                    seguir = false;
                }
            }

            // Al final escribimos el resumen
            pluma.println("Total artículos en la lista: " + totalArt);
            pluma.printf("Precio de la compra: %.2f%n", totalPrecio);

        } catch (Exception e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }

        teclado.close();
        System.out.println("Tu lista de la compra se encuentra en el fichero " + fichero);
    }
}
