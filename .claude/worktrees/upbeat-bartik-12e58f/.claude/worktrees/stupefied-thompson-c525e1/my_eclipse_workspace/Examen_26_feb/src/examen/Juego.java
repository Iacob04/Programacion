package examen;

import java.util.ArrayList;

public class Juego {

    private ArrayList<Integer> jugadores = new ArrayList<>();
    private ArrayList<Integer> jugadoresEliminados = new ArrayList<>();
    private int totalInicial;
    private int pruebas;

    public Juego(int numParticipantes) {
        
        totalInicial = numParticipantes;
        pruebas = 0;

        for (int i = 1; i <= numParticipantes; i++) {
            jugadores.add(i);
        }
    }

    
    public void verJugadores() {
        for (int i = 1; i <= totalInicial; i++) {

            if (jugadores.contains(i)) {
                System.out.printf("%03d ", i);
            } else {
                System.out.print("--- ");
            }

            if (i % 12 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    
    public void nuevaPrueba(int eliminar) {

        if (eliminar >= jugadores.size()) {
            System.out.println("No se pueden eliminar tantos jugadores.");
            return;
        }

        pruebas++;
        System.out.printf("Prueba número: %d. Expulsados: %d. Restantes: %d%n",
                pruebas, eliminar, jugadores.size() - eliminar);

        for (int i = 0; i < eliminar; i++) {
            int indice = (int) (Math.random() * jugadores.size());
            int eliminado = jugadores.remove(indice);
            jugadoresEliminados.add(eliminado);
        }

        verJugadores();

        if (jugadores.size() == 1) {
            System.out.println("¡El juego ha terminado!");
            System.out.printf("El ganador es el jugador %03d%n", jugadores.get(0));
        } else if (jugadores.isEmpty()) {
            System.out.println("¡Nos hemos quedado sin ganador!");
        }
    }
}
