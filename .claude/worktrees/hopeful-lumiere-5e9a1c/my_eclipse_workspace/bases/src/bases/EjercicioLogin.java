package bases;

import java.util.Scanner;



public class EjercicioLogin {

	public static void main(String[] args) {
		String usuario = "admin";
		String password = "1234";
        String server = "jdbc:mysql://localhost:3306/clasicmodels"; 
        
        Scanner teclado = new Scanner(System.in);
        
        boolean menu = false;
        
        do {
        System.out.println("=========================");
        System.out.println("=== 1. Iniciar Sesión ===");
        System.out.println("====== 2. Registro ======");
        System.out.println("=========================");
        System.out.println("Introduzca la opción: ");
        int opcion = teclado.nextInt();
        
        switch(opcion) {
        case 1:
        	Registrarse();
        	menu = true;
        	break;
        case 2:
        	IniciarSesion();
        	menu = true;
        	break;
        }
        
        	
        }while(menu ==  false);
     teclado.close();
	}
	public static void IniciarSesion() {
		Scanner teclado = new Scanner(System.in);   
		 
	    System.out.println("Introduzca el usuario: ");
	    String usuario = teclado.next();
	    
	    System.out.println("Introduzca la contraseña: ");
	    String contraseña = teclado.next();
	    teclado.close();
	}
	public static void Registrarse() {
		Scanner teclado = new Scanner(System.in);   
		 
	    System.out.println("Introduzca el  nombre del usuario que desea crear: ");
	    String usuario = teclado.next();
	    
	    System.out.println("Introduzca la contraseña: ");
	    String contraseña = teclado.next();
	    
	    System.out.println("Vuelva a introducir la contraseña: ");
	    String contraseña2 = teclado.next();
	    
	    if(contraseña2.matches(contraseña))
	    
	    teclado.close();
	}
	
	


}
