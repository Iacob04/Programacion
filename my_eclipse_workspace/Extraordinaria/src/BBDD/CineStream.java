package BBDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CineStream {

	public static void main(String[] args) {
		
		
		   String url = "jdbc:mysql://localhost:3306/cine";

	        try (Connection cnx = DriverManager.getConnection(url, "alex", "1234")) {
	            System.out.println("Conexión realizada con éxito");

	            peliculasPorGenero(cnx, "Drama");
	            actoresDePelicula(cnx, "Titanic");
	            actoresDePelicula(cnx, "Parásitos");
	            registrarActor(cnx, "Inception", "Cate Blanchett", "Investigadora");

	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	        }
	    }	
	
	
	public static void peliculasPorGenero(Connection cnx, String genero)throws SQLException {
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT titulo ,puntuacion,precio_alquiler,anyo_estreno, directores.nombre FROM generos "
						+ "JOIN peliculas USING (genero_id) "
						+ "JOIN directores USING (director_id) "
						+ "WHERE generos.nombre = ? "
						+ "ORDER BY puntuacion desc ",
						
						ResultSet.TYPE_SCROLL_INSENSITIVE,
					    ResultSet.CONCUR_READ_ONLY);
		
		sql.setString(1, genero);
		
		ResultSet rs = sql.executeQuery();
		
		rs.last();    
		
		int cuantos = rs.getRow();
		
		if (cuantos == 0) {
			System.out.println("El género " + genero + " no existe en la base de datos" );
			
		}
		else {
			System.out.println("Películas del género " + genero + " ( " + cuantos + " encontradas):");
			rs.beforeFirst();
			while (rs.next()) {
	            String titulo = rs.getString("titulo");
	            int anyo = rs.getInt("anyo_estreno");
	            double puntuacion   = rs.getDouble("puntuacion");
	            double alquiler = rs.getDouble("precio_alquiler");
	            String director = rs.getString("nombre");
	            
	            System.out.printf("- %s (%d) | Dir: %s | Punt: %.1f | %.2f € %n", titulo, anyo, director, puntuacion, alquiler);

	            
	        }
			
			
		}
		
	}
	
	
	
	public static void actoresDePelicula(Connection cnx, String titulo) throws SQLException{
		
		 PreparedStatement sql = cnx.prepareStatement(
		            "SELECT papel , protagonista, nombre FROM peliculas " +
		            "JOIN pelicula_actores USING (pelicula_id) " +
		            "JOIN actores USING (actor_id) " +
		            "WHERE titulo = ?",
					
					ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
		        );

		        sql.setString(1, titulo);
		        ResultSet rs = sql.executeQuery();
		        
		        ArrayList<String> protagonistas = new ArrayList<>();
		        ArrayList<String> secundarios     = new ArrayList<>();
		        int cuantos ;
		        rs.last();    
				
				cuantos = rs.getRow();

				if (cuantos == 0) {
					System.out.println();
					System.out.println("------------------------------------------------------------");
					System.out.println("La película " + titulo + " no existe en la base de datos" );
					System.out.println("------------------------------------------------------------");
					
				}
				else {
					System.out.println();
					System.out.println("------------------------------------------------------------");
					System.out.println("Reparto de "+ titulo + ":");
					
					rs.beforeFirst();

					while (rs.next()) {
						String nombre = rs.getString("nombre");
						int protagonista   = rs.getInt("protagonista");
						String papel = rs.getString("papel");
						
						if(protagonista == 1) {
							protagonistas.add("[PROTAGONISTA] "+ nombre + " -> "+ papel);
						}
						else {
							secundarios.add("[Secundario] "+ nombre + " -> "+ papel);
						}
						

					}
					for (String p : protagonistas) System.out.println(p);
					for (String s : secundarios) System.out.println(s);
					System.out.println("Total actores : "+ cuantos);
					System.out.println("------------------------------------------------------------");
				}	
		
	}
	
	
	
	
	
	public static void registrarActor(Connection cnx , String nombrePelicula, String nombreActor, String papel) throws SQLException{
		
		PreparedStatement sqlTitulo = cnx.prepareStatement(
	            "SELECT pelicula_id FROM peliculas WHERE titulo = ?  "
	        );
		
		sqlTitulo.setString(1, nombrePelicula);
		
		ResultSet rsTitulo = sqlTitulo.executeQuery();
		
		if(!rsTitulo.next()) {
			System.out.println("------------------------------------------------------------");
			System.out.println("La película "+ nombrePelicula + " no existe o no está en la base de datos");
			System.out.println("------------------------------------------------------------");
		}
		else {
		
		int id_pelicula = rsTitulo.getInt("pelicula_id");
		
		// Despues de comprobar si existe la película, compruebo si existe el actor en la tabla actores.
		
		
			PreparedStatement sqlActor = cnx.prepareStatement(
	            "SELECT actor_id FROM actores WHERE nombre = ?  "
					);
			
			sqlActor.setString(1, nombreActor);
			
			ResultSet rsActor = sqlActor.executeQuery();
			
			if(!rsActor.next()) {
				System.out.println("------------------------------------------------------------");
				System.out.println("El actor o actriz "+ nombreActor + " no existe o no está en la base de datos");
				System.out.println("------------------------------------------------------------");
			}
			else {
				
				int id_actor = rsActor.getInt("actor_id");
				
				// Despues de comprobnar que existe el actor , compuebro de que el actor no esté ya en la película.
				
				PreparedStatement sqlPeliculaActor = cnx.prepareStatement(
						"SELECT actor_id FROM pelicula_actores WHERE pelicula_id = ? AND actor_id = ?"
						);
				
				sqlPeliculaActor.setInt(1, id_pelicula);
				sqlPeliculaActor.setInt(2, id_actor);
				
				ResultSet rsPeliculaActor = sqlPeliculaActor.executeQuery();
				
				if(!rsPeliculaActor.next()) {
					
					//Tras comprobar todo lo anterior añado con isert al actor 
					
					PreparedStatement insActor = cnx.prepareStatement(
							"INSERT INTO pelicula_actores (pelicula_id , actor_id , papel ) VALUES (?, ?, ?) "
							);
					
					insActor.setInt(1, id_pelicula);
					insActor.setInt(2, id_actor);
					insActor.setString(3, papel);
					
					insActor.executeUpdate();
					System.out.println();
					System.out.println("------------------------------------------------------------");
					System.out.println("Actor "+ nombreActor + " añadido a "+ nombrePelicula + " como "+ papel);
					System.out.println("------------------------------------------------------------");
				}
				
				else {
					
					System.out.println("------------------------------------------------------------");
					System.out.println("El actor o actriz "+ nombreActor + " ya está en "+ nombrePelicula);
					System.out.println("------------------------------------------------------------");
					
				}
				
			}
		
		}
	}

	}


