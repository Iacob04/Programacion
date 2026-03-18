package examenesAutoescuela;

import java.util.ArrayList;

public class Examen {

    private ArrayList<Preguntas> preguntas = new ArrayList<>();

    public void añadirPregunta(Preguntas p) {
        preguntas.add(p);
    }

    public void mostrarExamen() {

        if (preguntas.isEmpty()) {
            System.out.println("No hay preguntas en el examen.");
            return;
        }

        // Crear copia para no modificar la original
        ArrayList<Preguntas> copia = new ArrayList<>(preguntas);

        
        for (int i = 0; i < copia.size(); i++) {
            int indiceAleatorio = (int) (Math.random() * copia.size());
            Preguntas temp = copia.get(i);
            copia.set(i, copia.get(indiceAleatorio));
            copia.set(indiceAleatorio, temp);
        }

        // Mostrar examen en orden aleatorio
        for (Preguntas p : copia) {
            System.out.println(p.getTexto());
            for (Respuestas r : p.getRespuestas()) {
                System.out.println(" - " + r.getTexto());
            }
            System.out.println();
        }
    }
}


