package bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasesDeDatos {

	public static void main(String[] args) {
		
		String usuario = "admin";
		String password = "1234";
		String server = "jdbc:mysql://localhost:3306/logistica_global";
		
		try {
			Connection conexion;
			conexion = DriverManager.getConnection(server,usuario,password);
			System.out.println("Conexion realizada ");
			Statement query = conexion.createStatement();
			ResultSet resultado = query.executeQuery("SELECT * FROM almacenes");
			resultado.afterLast();
			while(resultado.previous()) {
				System.out.printf("%d - Almacen: (%d), %s, %s, %s \n",resultado.getRow(), resultado.getInt("id"),resultado.getString("ciudad_ubicacion"), resultado.getString("nombre_sucursal"),resultado.getString("cod_almacen") );
			}
			
			conexion.close();
		}catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}

	}

}
