package boletin_26;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ejercicio3 {

	public static void main(String[] args) {
		
		String usuario = "admin";
		String servidor = "jdbc:mysql://localhost:3306/classicmodels";
		String passwd = "1234";
		int stockMinimo = 500;
	    try (Connection conexion = DriverManager.getConnection(servidor, usuario, passwd)) {
	    	
	    	System.out.println("Conexion realizada con exito");
	    	productosConStockMenor(conexion, stockMinimo);

	        } catch (SQLException e) {
	            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
	        }

	}
	
	public static void productosConStockMenor(Connection cnx , int min)throws SQLException{
		PreparedStatement query = cnx.prepareStatement("SELECT productCode, productName, quantityInStock FROM products WHERE quantityInStock <= ?");
		query.setInt(1, min);
		ResultSet resultado = query.executeQuery();
		while (resultado.next()) {
			System.out.println(resultado.getString("productName"));
			PreparedStatement query2 = cnx.prepareStatement("select count(*) from orderdetails where productCode =?");
			query2.setString(1, resultado.getString("productCode"));
			ResultSet resultado2 = query2.executeQuery();
			resultado2.next();
			System.out.println(resultado2.getInt(1));
		}
	}

}

