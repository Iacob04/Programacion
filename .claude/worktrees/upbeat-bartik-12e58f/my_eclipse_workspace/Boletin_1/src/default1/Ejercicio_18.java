
package default1;

public class Ejercicio_18 {

	public static void main(String[] args) {
System.out.println("Calaculador de cuantos días quedan para el apocalipsis");
		
		int numero = 0;
		int contador = 0;
		while( numero != 666 ) {
			contador = contador+1;
		 numero = (int)(Math.random() * 1000) + 1;
		System.out.println(numero);

		}
		
		System.out.println("¡Faltan " + contador + " días para que se acabe todo!" );

	}

}
