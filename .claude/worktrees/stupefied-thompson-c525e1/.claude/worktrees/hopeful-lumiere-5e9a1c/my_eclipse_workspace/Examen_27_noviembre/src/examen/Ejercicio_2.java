
package examen;
import java.util.Scanner;

public class Ejercicio_2 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String[] clientes = {"Diego Norrea", "Inés Perado", "Demetrio Imedio", "Roberto Rija",
                             "Rubén Tosidad", "Alexandru Iacob", "Martín Taboada"};

        System.out.print("¿Cuántos premios vas a repartir? ");
        int cantidadPremios = teclado.nextInt();
        int cantidadClientes = clientes.length;

        if(cantidadClientes == cantidadPremios) {
            System.out.println("\nTienes solo " + cantidadClientes +
                    " clientes. Les puedes dar un premio a cada uno.");
        }
        else if(cantidadClientes < cantidadPremios) {
            System.out.println("\nTienes solo " + cantidadClientes +
                    " clientes. Les puedes dar un premio a cada uno.");
            System.out.println("Te sobran " + (cantidadPremios - cantidadClientes) +
                    " premios sin repartir. ¡Guárdalos para el siguiente sorteo!");
        }
        else {

            System.out.println("\nLos/as afortunados/as son:");
            int[] ganadores = new int[cantidadPremios];
            boolean repetido;

            for(int i = 0; i < cantidadPremios; i++) {

                int num;
                do {
                    num = (int)(Math.random() * cantidadClientes); // índice válido
                    repetido = false;

                    for(int j = 0; j < i; j++) {
                        if(ganadores[j] == num) {
                            repetido = true; // evitar repetidos
                        }
                    }

                } while(repetido);

                ganadores[i] = num;
                System.out.println(clientes[num]);
            }

            System.out.println();
            System.out.println((cantidadClientes - cantidadPremios) +
                    " se han quedado sin premio. ¡Mucha suerte en el próximo sorteo!");
        }

        teclado.close();
    }
}

