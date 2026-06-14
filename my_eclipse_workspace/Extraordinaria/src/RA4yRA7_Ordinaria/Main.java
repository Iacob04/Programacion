package RA4yRA7_Ordinaria;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

        Receta fabada = new Receta("Fabada asturiana", 6);

        // ingredientes con cantidad
        fabada.addIngrediente(new Ingredientes("Fabes de Asturias", 500, "grs"));
        fabada.addIngrediente(new Ingredientes("Chorizo asturiano", 2, "unidades"));
        fabada.addIngrediente(new Ingredientes("Morcilla asturiana", 2, "unidades"));
        fabada.addIngrediente(new Ingredientes("Tocino curado entreverado asturiano", 200, "grs"));
        fabada.addIngrediente(new Ingredientes("Lacón asturiano", 200, "grs"));
        fabada.addIngrediente(new Ingredientes("Cebolla", 1, "unidad"));

        // ingredientes SIN cantidad ni medida
        fabada.addIngrediente(new Ingredientes("Aceite de oliva"));
        fabada.addIngrediente(new Ingredientes("Azafrán"));
        fabada.addIngrediente(new Ingredientes("Sal"));

        System.out.println(fabada);

        // ejemplo con lista (opcional, por si te lo piden)
        ArrayList<Ingredientes> extras = new ArrayList<>();
        extras.add(new Ingredientes("Pimentón", 1, "cucharada"));
        extras.add(new Ingredientes("Laurel"));

        Receta otraReceta = new Receta("Sopa casera", 4);
        otraReceta.addIngredientes(extras);

        System.out.println("\n" + otraReceta);
	}

}
