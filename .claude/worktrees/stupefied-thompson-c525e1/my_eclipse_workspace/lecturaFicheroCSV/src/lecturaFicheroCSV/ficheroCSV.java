package lecturaFicheroCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ficheroCSV {

	static final String servidorBase = "jdbc:mysql://localhost:3306/";
	static final String servidor     = "jdbc:mysql://localhost:3306/agenda";
	static final String usuar        = "admin";
	static final String passwd       = "1234";

	public static void main(String[] args) {
		Path nombreFichero = Path.of("agenda.csv");

		ArrayList<String> lineas = null;

		try {
			lineas = new ArrayList<>(Files.readAllLines(nombreFichero));
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
			return;
		}

		crearBasedeDatos(lineas);
	}

	public static void crearBasedeDatos(ArrayList<String> lineas) {

		try (Connection conexion = DriverManager.getConnection(servidorBase, usuar, passwd);
		     Statement stmt = conexion.createStatement()) {

			String sqlDB = "CREATE DATABASE IF NOT EXISTS agenda";
			stmt.executeUpdate(sqlDB);

			String sqlTable = "CREATE TABLE IF NOT EXISTS agenda.contactos (" +
			                  "nombre VARCHAR(50), " +
			                  "apellido VARCHAR(50), " +
			                  "email VARCHAR(100) NOT NULL UNIQUE, " +
			                  "telefono INT)";
			stmt.executeUpdate(sqlTable);

		} catch (SQLException e) {
			System.out.println("Error al inicializar la base de datos: " + e.getMessage());
			return;
		}

		try (Connection conexion = DriverManager.getConnection(servidor, usuar, passwd)) {
			String sqlInsert = "INSERT INTO contactos (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sqlInsert);

			for (int i = 1; i < lineas.size(); i++) {
				String[] campos = lineas.get(i).split(",");
				if (campos.length < 4) continue;
				String nombre   = campos[0].trim();
				String apellido = campos[1].trim();
				ps.setString(1, nombre.length()   > 50 ? nombre.substring(0, 50)   : nombre);
				ps.setString(2, apellido.length() > 50 ? apellido.substring(0, 50) : apellido);
				ps.setString(3, campos[2].trim());
				ps.setInt(4, Integer.parseInt(campos[3].trim()));
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error al insertar datos: " + e.getMessage());
		}
	}
}
