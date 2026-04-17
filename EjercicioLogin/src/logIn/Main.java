package logIn;  

import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner teclado = new Scanner(System.in); // Un solo Scanner global

    public static void main(String[] args) {
        String servidor = "jdbc:mysql://localhost:3306/";

        boolean menu = false;

        do {
            System.out.println("=========================");
            System.out.println("=== 1. Iniciar Sesión ===");
            System.out.println("=== 2. Registro =========");
            System.out.println("=========================");
            System.out.print("Introduzca la opción: ");
            int opcion = teclado.nextInt();
            teclado.nextLine(); // limpiar buffer tras nextInt()

            switch (opcion) {
                case 1:
                    IniciarSesion();
                    menu = true;
                    break;
                case 2:
                    Registrarse();
                    menu = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (menu == false);

        teclado.close();
    }

    public static void IniciarSesion() {
        System.out.print("Introduzca el usuario: ");
        String usuario = teclado.nextLine();

        System.out.print("Introduzca la contraseña: ");
        String contraseña = teclado.nextLine();

        System.out.println("Sesión iniciada como: " + usuario);
    }

    public static void Registrarse() {
        System.out.print("Introduzca el nombre del usuario que desea crear: ");
        String usuario = teclado.nextLine();

        System.out.print("Introduzca la contraseña: ");
        String contraseña = teclado.nextLine();

        System.out.print("Vuelva a introducir la contraseña: ");
        String contraseña2 = teclado.nextLine();

        // equals() para comparar Strings, no matches()
        if (contraseña2.equals(contraseña)) {
            System.out.println("Usuario registrado correctamente ");
        } else {
            System.out.println("Las contraseñas no coinciden ");
        }
    }

    // server se pasa como parámetro para que esté disponible aquí
    public static void BBDD(String server, String usuario, String password) {
        try (Connection conexion = DriverManager.getConnection(server, usuario, password)) {
            System.out.println("Conexión exitosa a la base de datos ");
        } catch (Exception e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }
}
