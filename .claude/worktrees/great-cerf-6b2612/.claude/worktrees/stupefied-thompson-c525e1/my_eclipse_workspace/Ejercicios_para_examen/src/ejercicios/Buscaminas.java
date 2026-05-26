package ejercicios;

import java.util.Scanner;

public class Buscaminas {
	final static int MINA = 1; // variable constante global

	public static void main(String[] args) {
		/*
		 * Vamos a hacer una implementación del juego del buscaminas y lo primero es
		 * preparar el tablero. Genera un array de dos dimensiones de 5 filas por 5
		 * columnas. El tablero tendrá 5 minas que se colocaran de forma aleatoria en
		 * cinco posiciones del array. Las minas se representarán con un 1 y las
		 * posiciones sin mina con un 0. Al final dibuja el tablero de esta forma: 0 0 0
		 * 0 0 0 0 
		 * 1 0 0 0 
		 * 0 1 0 1 
		 * 0 0 0 0 
		 * 0 1 1 0 
		 * 0 0 Modifica tu programa para que le
		 * puedas decir por teclado el tamaño del tablero de juego (siempre será
		 * cuadrado, así que solo le dirás un número entero) y el número de minas.
		 * Comprueba que no se pueden poner mas minas que las posiciones del tablero (en
		 * un tablero de 3x3 no puede haber 10 minas, por ejemplo
		 */
		// MINAS 1
		// SIN MINA 0

		// primer[FILA][COLUMNA]

		int lado = 0;// lados del tablero
		int minas = 0; // minas que va tener el tablero

		boolean correcto = false;
		Scanner teclado = new Scanner(System.in);//fuera del while mas eficiente

		do {

			try { //CAPTURA DE ERRORES 
				System.out.println("De cuantos lados es el tablero?: ");
				lado = teclado.nextInt();//pedidosmos el lado
				System.out.println("Cauntas minas vas a poner?: ");
				minas = teclado.nextInt();//pedimos las minas
				if (minas < lado * lado) {
					correcto = true;
				} else {
					System.out.println(
							"No puedes poner " + minas + " minas en un tablero de " + lado * lado + " casillas");
				}
			} catch (Exception e) {
				System.out.println("NO VALIDO");
			}
		} while (correcto == false);
		teclado.close(); 

		int[][] tablero = new int[lado][lado];// array de dos dimensiones de 5*5
		//tablero[columna][fila]=MINA;

		colocarMinas(tablero, lado, minas);// tiene que ir en mismo orden
		mostrarTablero(tablero, lado);

	}

	// TODO FUNCION PARA MOSTRAR TABLERO
	public static void mostrarTablero(int[][] tablero, int lado) {// indicamos que va ser una matriz
		// recorriendo de un array de dos dimensiones
		for (int j = 0; j < lado; j++) {
			for (int i = 0; i < lado; i++) {
				System.out.print(tablero[j][i] + " ");// imprimimos el tablero
			}
			System.out.println();
		}
	}

	// TODO FUNCION PARA COLOCAR UN 1 ALTORIAMENTE POR FILA Y COLUMNA
	public static void colocarMinas(int[][] tablero, int lado, int minas) {
		int numMinas = 0;
		while (numMinas != minas) {
			// for (int i = 0; i < 5; i++) {
			int columna = (int) (Math.random() * lado);// columna aletorio
			int fila = (int) (Math.random() * lado);// fila aletoria
			if (tablero[fila][columna] == 0) { // SI HAY UN 0 PONE LA MINA
				tablero[fila][columna] = MINA;// MINA ES 1
				numMinas++;
			}
		}
	}
//	int[] tablero=new int[5];
//	mostrarTablero(tablero);
//	public static void mostrarTablero(int[] tablero) {
//		for(int i=0; i <5;i++) {
//			System.out.print(tablero[i]+" ");
//		}
//	}

}