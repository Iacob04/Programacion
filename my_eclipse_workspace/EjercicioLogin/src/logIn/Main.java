package logIn;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

public class Main {

    static Scanner teclado = new Scanner(System.in);
    static final String servidorBase = "jdbc:mysql://localhost:3306/";
    static final String servidor     = "jdbc:mysql://localhost:3306/login_db";
    static final String usuar  = "admin";
    static final String passwd = "1234";

    public static void main(String[] args) {
        inicializar();

        boolean menu = false;

        do {
            System.out.println("=========================");
            System.out.println("=== 1. Iniciar Sesión ===");
            System.out.println("=== 2. Registro =========");
            System.out.println("=========================");
            System.out.print("Introduzca la opción: ");
            int opcion;
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("Opción no válida.");
                continue;
            }

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

        } while (!menu);

        teclado.close();
    }

    public static void inicializar() {
        String sqlDB    = "CREATE DATABASE IF NOT EXISTS login_db";
        String sqlTable = "CREATE TABLE IF NOT EXISTS login_db.usuarios (" +
                          "id INT AUTO_INCREMENT PRIMARY KEY, " +
                          "usuario VARCHAR(50) NOT NULL UNIQUE, " +
                          "hash VARCHAR(255) NOT NULL, " +
                          "salt VARCHAR(100) NOT NULL, " +
                          "email VARCHAR(100) NOT NULL UNIQUE, " +
                          "prioridad TINYINT NOT NULL CHECK (prioridad IN (1, 2, 3)))";

        try (Connection conexion = DriverManager.getConnection(servidorBase, usuar, passwd);
             PreparedStatement stmtDB    = conexion.prepareStatement(sqlDB);
             PreparedStatement stmtTable = conexion.prepareStatement(sqlTable)) {

            stmtDB.executeUpdate();
            stmtTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    public static String generarSalt() {
        SecureRandom azar = new SecureRandom();
        byte[] salt = new byte[16];
        azar.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String generarHash(String saltMasContra) {
        String hashTXT = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(saltMasContra.getBytes(StandardCharsets.UTF_8));
            hashTXT = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            System.out.println("El algoritmo SHA-512 no está disponible.");
        }
        return hashTXT;
    }

    public static void IniciarSesion() {
        System.out.print("Introduzca el usuario: ");
        String usuario = teclado.nextLine();

        System.out.print("Introduzca la contraseña: ");
        String contraseña = teclado.nextLine();

        String sql = "SELECT hash, salt, prioridad FROM usuarios WHERE usuario = ?";

        try (Connection conexion = DriverManager.getConnection(servidor, usuar, passwd);
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashGuardado = rs.getString("hash");
                String salt         = rs.getString("salt");
                String hashIntento  = generarHash(salt + contraseña);

                if (hashGuardado.equals(hashIntento)) {
                    int prioridad = rs.getInt("prioridad");
                    System.out.println("Sesión iniciada como: " + usuario + " (prioridad " + prioridad + ")");
                } else {
                    System.out.println("Usuario o contraseña incorrectos.");
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void Registrarse() {
        System.out.print("Introduzca el nombre del usuario que desea crear: ");
        String usuario = teclado.nextLine();

        System.out.print("Introduzca la contraseña: ");
        String contraseña = teclado.nextLine();

        System.out.print("Vuelva a introducir la contraseña: ");
        String contraseña2 = teclado.nextLine();

        if (!contraseña2.equals(contraseña)) {
            System.out.println("Las contraseñas no coinciden.");
            return;
        }

        System.out.print("Introduzca el email: ");
        String email = teclado.nextLine();

        System.out.print("Introduzca la prioridad (1, 2 o 3): ");
        int prioridad;
        try {
            prioridad = Integer.parseInt(teclado.nextLine().trim());
            if (prioridad < 1 || prioridad > 3) {
                System.out.println("La prioridad debe ser 1, 2 o 3.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Prioridad no válida.");
            return;
        }

        String sqlCheck  = "SELECT * FROM usuarios WHERE usuario = ?";
        String sqlInsert = "INSERT INTO usuarios (usuario, hash, salt, email, prioridad) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(servidor, usuar, passwd);
             PreparedStatement stmtCheck  = conexion.prepareStatement(sqlCheck);
             PreparedStatement stmtInsert = conexion.prepareStatement(sqlInsert)) {

            stmtCheck.setString(1, usuario);
            ResultSet rs = stmtCheck.executeQuery();

            if (rs.next()) {
                System.out.println("El usuario ya existe.");
            } else {
                String salt = generarSalt();
                String hash = generarHash(salt + contraseña);

                stmtInsert.setString(1, usuario);
                stmtInsert.setString(2, hash);
                stmtInsert.setString(3, salt);
                stmtInsert.setString(4, email);
                stmtInsert.setInt(5, prioridad);
                stmtInsert.executeUpdate();
                System.out.println("Usuario registrado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
