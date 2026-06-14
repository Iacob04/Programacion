package RA4yRA7Practica;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Animal iguana = new Animal("Iguana Verde", 2);
		Jaula reptiles = new Jaula("reptiles");
		reptiles.addAnimales(iguana);
		System.out.println(reptiles);
		Animal gecko = new Animal("Gecko", 1);
		Animal tortuga = new Animal("Tortuga de tierra");
		
		ArrayList<Animal> animales = new ArrayList<>();
		animales.add(gecko);
		animales.add(tortuga);
		reptiles.addAnimales(animales);
		System.out.println(reptiles);

	}

}
