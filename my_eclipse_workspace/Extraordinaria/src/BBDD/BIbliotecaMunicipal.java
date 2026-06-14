 package BBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BIbliotecaMunicipal {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/biblioteca";

        try (Connection cnx = DriverManager.getConnection(url, "alex", "1234")) {
            System.out.println("Conexión realizada con éxito");

            librosPorCategoria(cnx, "Novela");
            prestamoDeSocio(cnx, "Ana García");
            registrarPrestamo(cnx, "Carlos López", "Clean Code");
            prestamoDeSocio(cnx, "Carlos López");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

	}
	
	public static void librosPorCategoria(Connection cnx , String categoria) throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT  titulo, disponible, precio ,anyo_publicacion, autores.nombre FROM categorias "
				+ "JOIN libros USING (categoria_id) "
				+ "JOIN autores USING (autor_id) "
				+ "WHERE categorias.nombre = ? "
				+ "ORDER BY anyo_publicacion DESC " ,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

			sql.setString(1, categoria);
			
			ResultSet rs = sql.executeQuery();
			
			rs.last();    
			
			int cuantos = rs.getRow();
			
			if (cuantos == 0) {
				System.out.println("La categoría " + categoria + " no existe en la base de datos" );
				
			}
			else {
				System.out.println("Libros de la categoría " + categoria + " ( " + cuantos + " encontrados):");
				rs.beforeFirst();
				while (rs.next()) {
		            String titulo = rs.getString("titulo");
		            int anyo = rs.getInt("anyo_publicacion");
		            double precio   = rs.getDouble("precio");
		            int disponible = rs.getInt("disponible");
		            String autor = rs.getString("nombre");
		            
		            if(disponible == 1) {
		            	System.out.printf("- %s (%d) | Autor : %s | %.2f € | Disponible : SÍ %n", titulo, anyo, autor, precio);
		            }
		            else {
		            	System.out.printf("- %s (%d) | Autor : %s | %.2f € | Disponible : NO %n", titulo, anyo, autor, precio);
		            }
		        }
			}
	}
	
	public static void prestamoDeSocio (Connection cnx, String nombreSocio) throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
	            "SELECT titulo , fecha_prestamo , fecha_devolucion FROM socios " +
	            "JOIN prestamos USING (socio_id) " +
	            "JOIN libros USING (libro_id) " +
	            "WHERE socios.nombre = ?",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY
	        );

	        sql.setString(1, nombreSocio);
	        ResultSet rs = sql.executeQuery();
	        
	        ArrayList<String> devueltos = new ArrayList<>();
	        ArrayList<String> encurso   = new ArrayList<>();
	        int cuantos;
	        rs.last();    
			cuantos = rs.getRow();

			if (cuantos == 0) {
				System.out.println();
				System.out.println("------------------------------------------------------------");
				System.out.println("El socio " + nombreSocio + " no existe en la base de datos");
				System.out.println("------------------------------------------------------------");
			}
			else {
				System.out.println();
				System.out.println("------------------------------------------------------------");
				System.out.println("Préstamos de " + nombreSocio + " (" + cuantos + " en total):");
				
				rs.beforeFirst();

				while (rs.next()) {
					String titulo           = rs.getString("titulo");
					Date fecha_prestamo     = rs.getDate("fecha_prestamo");
					Date fecha_devolucion   = rs.getDate("fecha_devolucion");
					
					if(fecha_devolucion == null) {
						encurso.add("[EN CURSO]  " + titulo + " | Prestado: " + fecha_prestamo + " | Devuelto: -");
					}
					else {
						devueltos.add("[DEVUELTO]  " + titulo + " | Prestado: " + fecha_prestamo + " | Devuelto: " + fecha_devolucion);
					}
				}
				for (String e : encurso)  System.out.println(e);
				for (String d : devueltos) System.out.println(d);
				System.out.println("------------------------------------------------------------");
			}
	}
	
	public static void registrarPrestamo(Connection cnx, String nombreSocio, String tituloLibro) throws SQLException{
		
		// 1. Comprobar que el socio existe
		PreparedStatement sqlSocio = cnx.prepareStatement(
	            "SELECT socio_id FROM socios WHERE nombre = ?"
	        );
		sqlSocio.setString(1, nombreSocio);
		ResultSet rsSocio = sqlSocio.executeQuery();
		
		if(!rsSocio.next()) {
			System.out.println("------------------------------------------------------------");
			System.out.println("El socio " + nombreSocio + " no existe o no está en la base de datos");
			System.out.println("------------------------------------------------------------");
			return;
		}
		
		int id_socio = rsSocio.getInt("socio_id");
		
		// 2. Comprobar que el libro existe y está disponible
		PreparedStatement sqlLibro = cnx.prepareStatement(
                "SELECT libro_id FROM libros WHERE titulo = ? AND disponible = 1"
        );
		sqlLibro.setString(1, tituloLibro);
		ResultSet rsLibro = sqlLibro.executeQuery();
		
		if(!rsLibro.next()) {
			System.out.println("------------------------------------------------------------");
			System.out.println("El libro " + tituloLibro + " no existe o no está disponible");
			System.out.println("------------------------------------------------------------");
			return;
		}
		
		int id_libro = rsLibro.getInt("libro_id");
		
		// 3. Comprobar que el socio no tiene ya ese libro en préstamo activo
		PreparedStatement sqlDup = cnx.prepareStatement(
		        "SELECT prestamo_id FROM prestamos WHERE socio_id = ? AND libro_id = ? AND devuelto = 0"
		);
		sqlDup.setInt(1, id_socio);
		sqlDup.setInt(2, id_libro);
		ResultSet rsDup = sqlDup.executeQuery();
		
		if(rsDup.next()) {
			System.out.println("------------------------------------------------------------");
			System.out.println("El socio " + nombreSocio + " ya tiene ese libro en préstamo activo");
			System.out.println("------------------------------------------------------------");
			return;
		}
		
		// 4. INSERT en prestamos
		PreparedStatement insPrestamo = cnx.prepareStatement(
		        "INSERT INTO prestamos (libro_id, socio_id, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, CURDATE(), NULL, 0)"
		);
		insPrestamo.setInt(1, id_libro);
		insPrestamo.setInt(2, id_socio);
		insPrestamo.executeUpdate();
		
		// 5. UPDATE disponible en libros
		PreparedStatement sqlUpdate = cnx.prepareStatement(
		        "UPDATE libros SET disponible = 0 WHERE libro_id = ?"
		);
		sqlUpdate.setInt(1, id_libro);
		sqlUpdate.executeUpdate();
		
		System.out.println("------------------------------------------------------------");
		System.out.printf("Préstamo registrado: \"%s\" prestado a %s%n", tituloLibro, nombreSocio);
		System.out.println("------------------------------------------------------------");
	}
}
