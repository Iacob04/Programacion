package orientacionObjetosFInalCurso;

import java.util.ArrayList;
import java.util.Collections;

public class orientadaObjetos {

	public static void main(String[] args) {
		
		Pokemon p1 = new Pokemon(1, "Bulbasaur", "Planta", "Veneno");
		Pokemon p2 = new Pokemon(4, "Charmander", "Fuego");
		Pokemon p3 = new Pokemon(7, "Squirtle", "Agua");
		Pokemon p4 = new Pokemon(25, "Pikachu", "Eléctrico");
		Pokemon p5 = new Pokemon(39, "Jigglypuff", "Normal", "Hada");
		Pokemon p6 = new Pokemon(52, "Meowth", "Normal");
		Pokemon p7 = new Pokemon(63, "Abra", "Psíquico");
		Pokemon p8 = new Pokemon(94, "Gengar", "Fantasma", "Veneno");
		Pokemon p9 = new Pokemon(130, "Gyarados", "Agua", "Volador");
		Pokemon p10 = new Pokemon(150, "Mewtwo", "Psíquico");
		Pokemon p11 = p1;
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
		
		if (p11 == p4) {
			System.out.println("Son el mismo pokemon");
			
		}
		else
			System.out.println("No son el mismo pokemon");
		
		if(p11.equals(p4) == true) {
			System.out.println("Son el mismno pokemon");
		}
		else
			System.out.println("No son el mismo pokemon");
		
		ArrayList<Pokemon> listaPokemons = new ArrayList<>();
		listaPokemons.add(p1);
		listaPokemons.add(p2);
		listaPokemons.add(p3);
		listaPokemons.add(p4);
		listaPokemons.add(p5);
		listaPokemons.add(p6);
		listaPokemons.add(p7);
		listaPokemons.add(p8);
		listaPokemons.add(p9);
		listaPokemons.add(p10);
		
		Collections.sort(listaPokemons);
		for(Pokemon p : listaPokemons)
			System.out.println(p);
		
		p1.fueraDeCombate();
		Combate.finDelCombate();
	}

}
