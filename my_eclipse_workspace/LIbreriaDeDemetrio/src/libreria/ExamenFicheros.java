package libreria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ExamenFicheros {

    public static void main(String[] args) {

        String fichero  = "libros.txt";
        String informe  = "informe.txt";

        mostrarInventario(fichero);
        System.out.println("--------------------------------------------------");
        nuevoLibro(fichero);
        System.out.println("--------------------------------------------------");
        generarInforme(fichero, informe);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // APARTADO 1 – Leer y mostrar el inventario
    // ─────────────────────────────────────────────────────────────────────────
    public static void mostrarInventario(String fichero) {

        ArrayList<String[]> libros = leerLibros(fichero);

        if (libros == null || libros.size() == 0) {
            System.out.println("El fichero esta vacio o no existe.");
            return;
        }

        // Calcular precio medio, maximo y minimo
        double total   = 0;
        double maxPrecio = -1;
        double minPrecio = Double.MAX_VALUE;
        String libroMasCaro    = "";
        String libroMasBarato  = "";

        for (String[] libro : libros) {
            double precio = Double.parseDouble(libro[3]);
            total += precio;
            if (precio > maxPrecio) {
                maxPrecio = precio;
                libroMasCaro = libro[1];
            }
            if (precio < minPrecio) {
                minPrecio = precio;
                libroMasBarato = libro[1];
            }
        }

        double media = total / libros.size();

        // Cabecera
        System.out.println("--- INVENTARIO PAGINAS PERDIDAS ---");
        System.out.println();
        System.out.printf("%-35s %-24s %-15s %s%n",
                "Titulo", "Autor", "Categoria", "Precio");

        for (String[] libro : libros) {
            System.out.printf("%-35s %-24s %-15s %.2f EUR%n",
                    libro[1], libro[0], libro[2], Double.parseDouble(libro[3]));
        }

        // Resumen
        System.out.println();
        System.out.println("--- RESUMEN ---");
        System.out.println("Total de libros: " + libros.size());
        System.out.printf("Precio medio: %.2f EUR%n", media);
        System.out.printf("Libro mas caro:  %s (%.2f EUR)%n", libroMasCaro, maxPrecio);
        System.out.printf("Libro mas barato: %s (%.2f EUR)%n", libroMasBarato, minPrecio);
        System.out.println();
        System.out.println("Libros por encima del precio medio:");

        for (String[] libro : libros) {
            double precio = Double.parseDouble(libro[3]);
            if (precio > media) {
                System.out.printf("  - %s (%.2f EUR)%n", libro[1], precio);
            }
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // APARTADO 2 – Añadir un nuevo libro
    // ─────────────────────────────────────────────────────────────────────────
    public static void nuevoLibro(String fichero) {

        Scanner teclado = new Scanner(System.in);

        // --- Titulo ---
        String titulo = "";
        boolean tituloValido = false;
        while (tituloValido == false) {
            System.out.print("Titulo del libro: ");
            titulo = teclado.nextLine();
            if (titulo.isEmpty()) {
                System.out.println("El titulo no puede estar vacio.");
            } else if (titulo.contains(";")) {
                System.out.println("El titulo no puede contener el caracter ';'");
            } else {
                tituloValido = true;
            }
        }

        // Comprobar que no existe ya ese titulo
        ArrayList<String[]> libros = leerLibros(fichero);
        if (libros != null) {
            for (String[] libro : libros) {
                if (libro[1].equalsIgnoreCase(titulo)) {
                    System.out.println("Ya existe un libro con ese titulo. No se puede anadir.");
                    return;
                }
            }
        }

        // --- Autor ---
        System.out.print("Autor: ");
        String autor = teclado.nextLine();

        // --- Categoria ---
        String[] categoriasValidas = {"Informatica", "Novela", "Cocina", "Administracion"};
        String categoria = "";
        boolean categoriaValida = false;
        while (categoriaValida == false) {
            System.out.print("Categoria (Informatica/Novela/Cocina/Administracion): ");
            categoria = teclado.nextLine();
            for (String cat : categoriasValidas) {
                if (cat.equalsIgnoreCase(categoria)) {
                    categoria = cat;
                    categoriaValida = true;
                }
            }
            if (categoriaValida == false) {
                System.out.println("Categoria no valida. Debe ser Informatica, Novela, Cocina o Administracion.");
            }
        }

        // --- Precio ---
        double precio = 0;
        boolean precioValido = false;
        while (precioValido == false) {
            System.out.print("Precio: ");
            String precioTexto = teclado.nextLine();
            try {
                precio = Double.parseDouble(precioTexto);
                if (precio <= 0) {
                    System.out.println("El precio debe ser mayor que 0.");
                } else {
                    precioValido = true;
                }
            } catch (Exception e) {
                System.out.println("El precio debe ser un numero valido.");
            }
        }

        // Grabar en fichero
        try (PrintWriter pluma = new PrintWriter(new FileWriter(fichero, true))) {
            pluma.printf("%s;%s;%s;%.2f%n", autor, titulo, categoria, precio);
            System.out.println("Libro anadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar el libro: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // APARTADO 3 – Generar informe por categorias
    // ─────────────────────────────────────────────────────────────────────────
    public static void generarInforme(String origen, String destino) {

        ArrayList<String[]> libros = leerLibros(origen);

        if (libros == null || libros.size() == 0) {
            System.out.println("El fichero de origen esta vacio o no existe.");
            return;
        }

        // Agrupar por categoria: clave = categoria, valor = lista de libros
        HashMap<String, ArrayList<String[]>> porCategoria = new HashMap<>();

        for (String[] libro : libros) {
            String cat = libro[2];
            if (porCategoria.containsKey(cat) == false) {
                porCategoria.put(cat, new ArrayList<>());
            }
            porCategoria.get(cat).add(libro);
        }

        // Ordenar las categorias alfabeticamente
        ArrayList<String> categorias = new ArrayList<>(porCategoria.keySet());
        Collections.sort(categorias);

        // Calcular total general
        double totalGeneral = 0;
        for (String[] libro : libros) {
            totalGeneral += Double.parseDouble(libro[3]);
        }

        try (PrintWriter pluma = new PrintWriter(destino)) {

            pluma.println("====================================");
            pluma.println("   INFORME DE INVENTARIO - PAGINAS PERDIDAS");
            pluma.println("====================================");

            for (String cat : categorias) {
                pluma.println();
                pluma.println("CATEGORIA: " + cat);

                ArrayList<String[]> librosCategoria = porCategoria.get(cat);
                double totalCategoria = 0;

                for (String[] libro : librosCategoria) {
                    double precio = Double.parseDouble(libro[3]);
                    totalCategoria += precio;
                    pluma.printf("  %s - %s (%.2f EUR)%n", libro[1], libro[0], precio);
                }

                pluma.printf("  Libros: %d | Total categoria: %.2f EUR%n",
                        librosCategoria.size(), totalCategoria);
            }

            pluma.println();
            pluma.println("====================================");
            pluma.println("TOTAL LIBROS EN INVENTARIO: " + libros.size());
            pluma.printf("VALOR TOTAL DEL INVENTARIO: %.2f EUR%n", totalGeneral);
            pluma.println("====================================");

            System.out.println("Informe generado correctamente en: " + destino);

        } catch (Exception e) {
            System.out.println("Error al generar el informe: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // METODO AUXILIAR – Leer el fichero y devolver lista de libros
    // Cada libro es un String[] con: [0]=autor, [1]=titulo, [2]=categoria, [3]=precio
    // ─────────────────────────────────────────────────────────────────────────
    public static ArrayList<String[]> leerLibros(String fichero) {

        ArrayList<String[]> libros = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length == 4) {
                    libros.add(campos);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return null;
        }

        return libros;
    }
}
