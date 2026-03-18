
package boletín_2;

import java.util.Scanner;

public class Ejercicio_17 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		System.out.println("Introduzca la temperatura que desea convertir junto a la unidad corespondiente 'C , K o F'(sin espacios)");
		String temperatura = teclado.nextLine();
		
		while(temperatura.charAt(temperatura.length() - 1) != 'C' &&
			       temperatura.charAt(temperatura.length() - 1) != 'c' &&
			       temperatura.charAt(temperatura.length() - 1) != 'F' &&
			       temperatura.charAt(temperatura.length() - 1) != 'f' &&
			       temperatura.charAt(temperatura.length() - 1) != 'K' &&
			       temperatura.charAt(temperatura.length() - 1) != 'k') {
			
			System.out.println("Error al introducir una unidad indisponible");
			System.out.println("Introduzca la temperatura que desea convertir junto a la unidad corespondiente 'C , K o F'(sin espacios)");
			 temperatura = teclado.nextLine();
			 
		}
		String numero = temperatura.substring(0, temperatura.length() - 1);
		double numero1 = Double.parseDouble(numero);

		char unidad = temperatura.charAt(temperatura.length() - 1);
		if (unidad == 'C' || unidad == 'c') {
			System.out.println("La unidad utilizada es Celsius (ºC)");
			System.out.println("La cantidad que desea convertir es : " + numero);
			double Far = (numero1*1.8+32); 
			double Kel = (numero1+273.15); 
			System.out.println(numero + " ºC son : " + Far + " ºF y , " + Kel + " ºK");
		}
		
		else if (unidad == 'F' || unidad == 'f') 
		{
			System.out.println("La unidad utilizada es Farenheit (ºF)");
			System.out.println("La cantidad que desea convertir es : " + numero);
			double Cel = ((numero1-32)/18); 
			double Kel = (5/9*(numero1-32)+273.15); 
			System.out.println(numero + " ºF son : " + Cel + " ºC y , " + Kel + " ºK");
		}
		
		else {
			System.out.println("La unidad utilizada es Kelvin (ºK)");
			System.out.println("La cantidad que desea convertir es : " + numero);
			double Cel = (numero1-273.15); 
			double Far = (1.8*(numero1-273.15)+32); 
			System.out.println(numero + " ºK son : " + Cel + " ºC y , " + Far + " ºF");
		}
		
		

		teclado.close();
	}

}
