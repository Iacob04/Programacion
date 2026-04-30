package examen2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		 String USUARIO  = "admin";
		 String PASSWORD = "1234";
		 String SERVIDOR = "jdbc:mysql://localhost:3306/classicmodels"; 
		 
		 String ciudadBuscar = "Paris";
		 
		 
		  try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
			  
			  PreparedStatement ps1 = cnx.prepareStatement(
		                "SELECT officeCode FROM offices WHERE city = ?");
		            ps1.setString(1, ciudadBuscar); 
		            
		        ResultSet rs1 = ps1.executeQuery();
		        
		        if (!rs1.isBeforeFirst()) {             
		            System.out.println("No existe oficina en " + ciudadBuscar);
		            return;
		        }
		        
		        PreparedStatement ps = cnx.prepareStatement(
		                "SELECT firstName, lastName, email FROM employees WHERE officeCode =?"
		            );
		        
		        while(rs1.next()) {
		        String office = rs1.getString("officeCode");
	            
	            ps.setString(1, office);
	            
	            ResultSet rs = ps.executeQuery();
	            
	            rs.last();                          
	            int total = rs.getRow();            
	            System.out.println("Hay " + total + " empleados en la oficina de "+ ciudadBuscar + " Sus datos son: ");

	            while (rs.next()) {
	                System.out.printf(" %s, %s ( s% ) %n ",rs.getString("firstName"),rs.getString("lastName"), rs.getString("email"));
	            }
		        }

	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        
		  
	}

	

}
