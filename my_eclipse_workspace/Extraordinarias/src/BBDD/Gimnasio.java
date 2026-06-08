package BBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gimnasio {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/gimnasio";

        try (Connection cnx = DriverManager.getConnection(url, "admin", "1234")) {
            System.out.println("Conexión realizada con éxito");
            
            Gimnasio.clasesPorSala(cnx, "Sala A");
            Gimnasio.resumenMonitor(cnx, "Yoga");
            Gimnasio.inscripcionesPorClase(cnx);
            Gimnasio.sociosEnInverso(cnx, 1);
            Gimnasio.nuevoMonitor(cnx, "Javi Morales", "Boxeo", 30.00);
            //Gimnasio.actualizarTarifa(cnx, "MON-001", 26.50);
            Gimnasio.borrarClase(cnx, 6);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }


	}
	
	public static void clasesPorSala(Connection cnx, String sala)throws SQLException {
		
		PreparedStatement sqlSala = cnx.prepareStatement(
				"SELECT clases.nombre,duracion, monitores.nombre FROM clases "
				+ "JOIN monitores USING (monitor_id) "
				+ "WHERE sala = ? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY );
		
		sqlSala.setString(1, sala);
		
		ResultSet rs = sqlSala.executeQuery();
		
		rs.last();    
		
		int cuantos = rs.getRow();
		
		if(cuantos == 0) {
			System.out.println("No hay clases en la sala: "+ sala);
		}
		
		else {
			rs.beforeFirst();
			while(rs.next()) {
				System.out.printf(" %s | %d | %s %n", rs.getString("clases.nombre"), rs.getInt("duracion"), rs.getString("monitores.nombre"));
			}
			
		}
		
	}
	
	
	public static void resumenMonitor(Connection cnx, String especialidad)throws SQLException{
		
		PreparedStatement sqlMonitores = cnx.prepareStatement(
				"SELECT nombre, tarifa_hora FROM monitores WHERE especialidad = ? ",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY
				);
		
		sqlMonitores.setString(1, especialidad);
		
		ResultSet rs = sqlMonitores.executeQuery();
		
		int cuantos = rs.getRow();
				
				if(cuantos == 0) {
					System.out.println("No hay monitores en la especalidad: "+ especialidad);
				}
				
				else {
					rs.beforeFirst();
					while(rs.next()) {
						System.out.printf(" %s - %.2f %n", rs.getString("clases.nombre"), rs.getDouble("tarifa_hora"));
					}
				}
		
	}
	
	public static void inscripcionesPorClase(Connection cnx)throws SQLException {
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT clase_id, nombre, sala FROM clases",
	            ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY
				);
		
		ResultSet rs = sql.executeQuery();
		
		
		PreparedStatement sqlApuntados = cnx.prepareStatement(
				"SELECT id_inscripcion FROM inscripciones WHERE clase_id = ? ",
	            ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY
				);
		
		rs.beforeFirst();
		while(rs.next()) {
			int clase_id = rs.getInt("clase_id");
			String nombre = rs.getString("nombre");
			String sala = rs.getString("sala");
			
			ResultSet rs2 = sqlApuntados.executeQuery();
			sqlApuntados.setInt(1, clase_id);
			int cuantos = rs2.getRow();
			
			System.out.printf("%s (%s): %d inscripciones %n",nombre,sala,cuantos);
		}
		
	}
	
	public static void borrarClase(Connection cnx, int clase_id)throws SQLException{
		
		PreparedStatement del = cnx.prepareStatement(
				"DELETE FROM inscripciones WHERE clase_id = ?",
	            ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY
				);
		del.setInt(1, clase_id);
		
				
		ResultSet rs = del.executeUpdate();
		int cuantos = rs.getRow();
		
		if(cuantos == 0) {
			System.out.println("La clase con "+ clase_id+ " no existe");
		}
		
		else {
			
		System.out.println("Se han eliminado "+ cuantos+" inscripciones");
		
		PreparedStatement delclase = cnx.prepareStatement(
				"DELETE FROM clases WHERE clase_id = ?"
				);
		delclase.setInt(1, clase_id);
		
		ResultSet rsclase = delclase.executeUpdate();
		System.out.println("Clase eliminada con éxito");
			
		}
	}
	
	public static void sociosEnInverso(Connection cnx, int clase_id)throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT  nombre, fecha FROM inscripciones "
				+ "WHERE clase_id = ? " ,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

			sql.setInt(1, clase_id);
			
			ResultSet rs = sql.executeQuery();
			
			rs.last();   
			
			int cuantos = rs.getRow();
			
			if (cuantos == 0) {
				System.out.println("No hay inscritos en la clase con el id : " + clase_id );
				
			}
			else {
				
				rs.afterLast();
				while (rs.previous()) {
					
					String nombre = rs.getString("nombre");
					Date fecha = rs.getDate("fecha");
		            
		            
		            	System.out.printf("[%d] %s - %tF",cuantos,nombre,fecha);
		            	cuantos--;
		            
		            
		        }
			}
		
	}
	
	public static void nuevoMonitor(Connection cnx, String nombre, String especialidad, double tarifaHora) throws SQLException{
		
		PreparedStatement ultimoCodigo = cnx.prepareStatement(
			    "SELECT codigo FROM monitores ORDER BY codigo DESC LIMIT 1"
			);

			ResultSet rs = ultimoCodigo.executeQuery();
			
			String nuevoCodigo = "";

			if (rs.next()) {
			    String ultimo = rs.getString("codigo").substring(4);

			    int numeroCodigo = Integer.parseInt(ultimo);
			    numeroCodigo++;

			    nuevoCodigo = "MON-" + String.format("%03d", numeroCodigo);

			    System.out.println(nuevoCodigo);
			}
		
		PreparedStatement insertarMonitor = cnx.prepareStatement(
				"INSERT INTO monitores(codigo,nombre, especialidad,tarifaHora) VALUES (?,?,?,?) "
				);
		insertarMonitor.setString(1, nuevoCodigo);
		insertarMonitor.setString(2, nombre);
		insertarMonitor.setString(3, especialidad);
		insertarMonitor.setDouble(4, tarifaHora);
		
		
		int insertadas = insertarMonitor.executeUpdate();
		System.out.println("Insertadas: " + insertadas);
		
	
		
	}
	
	
	

}


