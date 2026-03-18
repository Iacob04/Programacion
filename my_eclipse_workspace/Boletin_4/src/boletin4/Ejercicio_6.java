
package boletin4;
import java.util.Scanner;
public class Ejercicio_6 {

	public static void main(String[] args) {

		System.out.println("estos son los primeros 50 números primos");
		
	    int encontrados = 0;
        for (int num = 2; encontrados < 50; num++) {
            if (esPrimo(num)) {
                System.out.printf("%4d", num);
                encontrados++;
                if (encontrados % 10 == 0) System.out.println();
            }
        }
    }
    
    public static boolean esPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
		

	}

}
