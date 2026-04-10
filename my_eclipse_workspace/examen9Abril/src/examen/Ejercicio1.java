package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Ejercicio1 {

    public static void main(String[] args) {
        String ficheroAnimes = "animes.txt";
        String ficheroPersonajes = "personajes.txt";
        mostrarFicheros(ficheroPersonajes, ficheroAnimes);
    }

    public static void mostrarFicheros(String ficheroPersonajes, String ficheroAnimes) {

        HashMap<String, ArrayList<String>> diccionarioPersonajes = new HashMap<>();
        HashMap<String, String> diccionarioAnimes = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(ficheroPersonajes))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                int pos = linea.indexOf(" ");
                String clavePersonaje = linea.substring(0, pos);
                String valorPersonaje = linea.substring(pos + 1);
                diccionarioPersonajes
                    .computeIfAbsent(clavePersonaje, k -> new ArrayList<>())
                    .add(valorPersonaje);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(ficheroAnimes))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                int pos = linea.indexOf(" ");
                String claveAnime = linea.substring(0, pos);
                String valorAnime = linea.substring(pos + 1);
                diccionarioAnimes.put(claveAnime, valorAnime);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (Entry<String, ArrayList<String>> personajes : diccionarioPersonajes.entrySet()) {
            boolean encontrado = false;

            for (Entry<String, String> anime : diccionarioAnimes.entrySet()) {
                if (personajes.getKey().contains(anime.getKey())) {
                    System.out.println(anime.getValue());
                    for (String nombrePersonaje : personajes.getValue()) {
                        System.out.println("- " + nombrePersonaje);
                    }
                    System.out.println();
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println();
                System.out.println("Personaje sin anime");
                for (String nombrePersonaje : personajes.getValue()) {
                    System.out.println("- " + nombrePersonaje);
                    System.out.println();
                }
            }
        }

        // Animes sin personajes
        for (Entry<String, String> anime : diccionarioAnimes.entrySet()) {
            boolean tienePersonaje = false;

            for (Entry<String, ArrayList<String>> personajes : diccionarioPersonajes.entrySet()) {
                if (personajes.getKey().contains(anime.getKey())) {
                    tienePersonaje = true;
                }
            }

            if (!tienePersonaje) {
                System.out.println();
                System.out.println("Anime sin personajes: " + anime.getValue());
            }
        }
    }
}
