package practicaExamenOrdinaria;

import java.util.Scanner;

public class cajeroAutomatico {

	public static void main(String[] args) {
		
		double saldo = 1000.0;
		boolean salir = false;
		Scanner teclado = new Scanner(System.in);
		int opcion ;
		int operaciones = 0;
		do {
		
		
			System.out.println("-------------------------");
			System.out.println("--- Cajero automático ---");
			System.out.println("-------------------------");
			System.out.println("1) Ingresar");
			System.out.println("2) Retirar");
			System.out.println("3) Consultar saldo");
			System.out.println("4) Salir");
			System.out.println("Elige una opción");
			
			opcion = teclado.nextInt();
			
			switch (opcion) {
			case 1: 
				System.out.println("Introduzca la cantidad a ingresar:");
				double ingreso = teclado.nextDouble();
				if(ingreso <= 0)
					System.out.println("El ingreso no puede ser negativo o 0");
				else {
					saldo = saldo + ingreso;
					operaciones ++;
					System.out.printf("Se han ingresado con éxito %.2f €%n", ingreso);
				}
				break;
				
			case 2:
				System.out.println("Introduzca la cantidad a retirar");
				
				double retirar = teclado.nextDouble();
				
				if(retirar <= 0 )
					System.out.println("La cantidad debe ser positiva");
				
				
				else if(retirar > saldo){
					System.out.println("Saldo insuficiente");
				}
				else {
					saldo = saldo - retirar;
					operaciones ++;
					System.out.printf("Se han retirado con éxito %.2f €%n", retirar);
				}
				break;
				
			case 3:
				operaciones ++;
				System.out.printf("Su saldo es de %.2f €%n", saldo);
				
				break;
				
			case 4:
				
				System.out.println("Saliendo .......");
				System.out.println("Se han realizado "+ operaciones + " operaciones exitosas");
				salir = true;
				break;
			
			default:
				System.out.println("Opción no válida");
				break;
			
			}
		
			
		}while(salir == false);
		
		teclado.close();

	}

}
