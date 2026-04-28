package funcionesLambda;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		
		Operacion suma =(a,b)-> {
			int x = a + b;
			return x;
		};
		Operacion resta =(a,b)-> {
			int x = a - b;
			return x;
		};
		
		Operacion producto = (a,b) -> a*b;
		
		System.out.println(suma.ejecutar(2, 4));
		System.out.println(resta.ejecutar(2, 4));
		System.out.println(producto.ejecutar(2, 4));
		
		Producto articulo = (nombre,importe, iva) -> {
			double valor = (double)Math.round(100* (importe + (importe*iva/100)))/100;
			
			String texto = "PVP " + nombre + ": " + String.valueOf(valor) +"€";
			return texto;
		};
		
		System.out.println(articulo.producto("Cromos", 1.50, 21));
		
		
		Runnable hola = () -> System.out.println("Hola Mundo");
		hola.run();
		
		
		Consumer <String> holagrupo = (grupo) -> System.out.println("Hola Mundo " + grupo);
		holagrupo.accept("DAM1");
		
		
		Consumer<Integer> euros = (valor) -> System.out.println(valor + "€");
		euros.accept(67);
		
		Supplier <String> saludo = () -> "Buenaaaaasssssss";
		Supplier <Double> pi = () -> 3.14159;
		System.out.println(saludo.get());
		System.out.println(pi.get());
		
		
		
		numeroAleatorio num = (inicio,fin,cuantos)-> {
			
		boolean repetido;
			
		HashSet<Integer> premiados = new HashSet<>();
		while(premiados.size() < cuantos) {
			int azar = (int)(Math.random()*(fin-inicio +1)+inicio);
			premiados.add((Integer)azar);
			
		}
		int[] resultado = new int[cuantos];
		int i =0; 
		for(int n:premiados) {
			resultado[i]= n;
			i++;
		}
		return resultado;
		};
		
		int[] ganadores = num.numeros(1, 49, 6);
		for(int n:ganadores)
			System.out.println(n);
		
		
	}

}
