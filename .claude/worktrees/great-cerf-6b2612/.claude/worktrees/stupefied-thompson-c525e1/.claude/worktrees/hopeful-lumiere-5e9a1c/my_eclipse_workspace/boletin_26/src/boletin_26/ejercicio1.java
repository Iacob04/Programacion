package boletin_26;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ejercicio1 {
	
	public static void main(String[] args) {
		
		String usuario = "admin";
		String servidor = "jdbc:mysql://localhost:3306/classicmodels";
		String passwd = "1234";
		
		String apellido = "Patterson";
		
	    try (Connection conexion = DriverManager.getConnection(servidor, usuario, passwd)) {
	    	
	    	System.out.println("Conexion realizada con exito");
	    	empleadosQueReportan(conexion, apellido);

	        } catch (SQLException e) {
	            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
	        }
		
	}
	
	public static void empleadosQueReportan (Connection conexion, String apellido) throws SQLException{

		String query1 = "SELECT employeeNumber, firstName, lastName FROM employees WHERE lastName = ?";
		String query2 = "SELECT firstName, lastName FROM employees WHERE reportsTo = ?";

		PreparedStatement consulta1 = conexion.prepareStatement(query1);
		consulta1.setString(1, apellido);
		ResultSet resultado1 = consulta1.executeQuery();

		if (!resultado1.isBeforeFirst()) {
			System.out.println("Error: no existe ningún empleado con el apellido '" + apellido + "'.");
			return;
		}

		PreparedStatement consulta2 = conexion.prepareStatement(query2);

		while (resultado1.next()) {
			int employeeNumber = resultado1.getInt("employeeNumber");
			String firstName   = resultado1.getString("firstName");
			String lastName    = resultado1.getString("lastName");

			System.out.println("Empleado: " + firstName + " " + lastName);

			consulta2.setInt(1, employeeNumber);
			ResultSet resultado2 = consulta2.executeQuery();

			if (!resultado2.isBeforeFirst()) {
				System.out.println("  -> No le reporta ningún empleado.");
			} else {
				while (resultado2.next()) {
					System.out.println("  -> Le reporta: " + resultado2.getString("firstName") + " " + resultado2.getString("lastName"));
				}
			}
			resultado2.close();
		}

		consulta2.close();
		resultado1.close();
		consulta1.close();
	}

}
