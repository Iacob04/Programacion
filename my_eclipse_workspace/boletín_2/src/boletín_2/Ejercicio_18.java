
package boletín_2;

import java.util.Scanner;

public class Ejercicio_18 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		System.out.println("Introduzca  el su saliario anual para calcular la retencion que tendrá");
		double salario = teclado.nextDouble();
		
		while (salario<0) {
			System.out.println("No puedes introducir un valor negativo");
			System.out.println("Introduzca  el su saliario anual para calcular la retencion que tendrá");
			salario = teclado.nextDouble();
		}
		
		
		if(salario >= 0 && salario <= 12450) {
			
			double irpf = salario * 0.19;
			double neto = salario - irpf ;
			System.out.println("El IRPF que debe pagar es de: " + irpf + " Euros");
			System.out.println("Su sueldo neto es de: " + neto + " Euros");
			
		}
		else if(salario > 12450 && salario <= 20200) {
			
			double irpf = salario * 0.24;
			double neto = salario - irpf ;
			System.out.println("El IRPF que debe pagar es de: " + irpf + " Euros");
			System.out.println("Su sueldo neto es de: " + neto + " Euros");
			
		}
		else if(salario > 20200 && salario <= 35200) {
			
			double irpf = salario * 0.30;
			double neto = salario - irpf ;
			System.out.println("El IRPF que debe pagar es de: " + irpf + " Euros");
			System.out.println("Su sueldo neto es de: " + neto + " Euros");
			
		}
		else if(salario > 35200 && salario <= 60000) {
			
			double irpf = salario * 0.37;
			double neto = salario - irpf ;
			System.out.println("El IRPF que debe pagar es de: " + irpf + " Euros");
			System.out.println("Su sueldo neto es de: " + neto + " Euros");
			
		}
		else if(salario > 60000 && salario <= 300000) {
			
			double irpf = salario * 0.45;
			double neto = salario - irpf ;
			System.out.println("El IRPF que debe pagar es de: " + irpf + " Euros");
			System.out.println("Su sueldo neto es de: " + neto + " Euros");
			
		}
		else {
			
			double irpf = salario * 0.47;
			double neto = salario - irpf ;
			System.out.println("El IRPF que debe pagar es de: " + irpf + " Euros");
			System.out.println("Su sueldo neto es de: " + neto + " Euros");
			
		}
		
		
		teclado.close();
	}

}
