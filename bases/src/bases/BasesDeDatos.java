package bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasesDeDatos {

	public static void main(String[] args) {
		
		String usuario = "admin";
		String password = "1234";
		String server = "jdbc:mysql://localhost:3306/sakila";
		
		try {
			Connection conexion;
			conexion = DriverManager.getConnection(server,usuario,password);
			conexion.close();
		}catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}

	}

}
