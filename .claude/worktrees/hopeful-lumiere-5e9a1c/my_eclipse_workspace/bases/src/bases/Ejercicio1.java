package EjercicioBBDDCoincidentes;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio1 {
	public static void main(String[] args) {
		String usuario = "admin";
		String password = "1234";
        String server = "jdbc:mysql://localhost:3306/sakila"; // URL de conexión: protocolo + host + puerto + nombre de la BD
        String nombreBUscar= "Mick";
		try {
            // Abre la conexión con la base de datos usando las credenciales
            // DriverManager busca el driver adecuado (en este caso MySQL) automáticamente
            Connection conexion = DriverManager.getConnection(server, usuario, password);
            System.out.println("Conexión realizada con éxito");
            String consulta = "SELECT * FROM actor where first_name = ?";
            PreparedStatement query2 = conexion.prepareStatement(consulta,ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
            query2.setString(1, nombreBUscar);
            
            ResultSet resultado = query2.executeQuery();
           
            resultado.last();
            
            int total = resultado.getRow();
            
            System.out.printf("El nombre %s aparece %d ",nombreBUscar,total);
            
	}catch(SQLException e) {
		System.out.println("Error :" + e.getMessage());
	}
}
}



/*
 * 
 * Esto es con row

package EjercicioBBDDCoincidentes;

import java.sql.Connection;        // Representa la conexión con la base de datos
import java.sql.DriverManager;     // Gestiona y crea la conexión
import java.sql.PreparedStatement; // Permite consultas con parámetros (?)
import java.sql.ResultSet;         // Almacena los resultados de la consulta
import java.sql.SQLException;      // Captura errores relacionados con la BD

public class Ejercicio1 {
    public static void main(String[] args) {

        // Credenciales y URL de conexión a la base de datos sakila
        String usuario = "admin";
        String password = "1234";
        String server = "jdbc:mysql://localhost:3306/sakila"; // protocolo + host + puerto + BD

        // Nombre que queremos buscar en la tabla actor
        String nombreBuscar = "Mick";

        try {
            // Abre la conexión con MySQL usando las credenciales definidas
            Connection conexion = DriverManager.getConnection(server, usuario, password);
            System.out.println("Conexión realizada con éxito");

            // Consulta con ? como parámetro, más seguro que concatenar el nombre directamente
            String consulta = "SELECT * FROM actor WHERE first_name = ?";

            // PreparedStatement con scroll para poder movernos libremente por el ResultSet
            // TYPE_SCROLL_INSENSITIVE → permite usar last(), previous(), first()...
            // CONCUR_READ_ONLY        → solo lectura, no modificamos datos
            PreparedStatement query = conexion.prepareStatement(
                consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );

            // Sustituye el primer ? por el valor de nombreBuscar
            // setString porque first_name es de tipo texto (VARCHAR)
            query.setString(1, nombreBuscar);

            // Ejecuta la consulta y guarda los resultados en el ResultSet
            // El cursor se posiciona ANTES de la primera fila al crearse
            ResultSet resultado = query.executeQuery();

            // Mueve el cursor a la ÚLTIMA fila del ResultSet
            // Si no hay resultados, last() devuelve false y el cursor no se mueve
            resultado.last();

            // getRow() devuelve el número de la fila actual
            // Como estamos en la última fila, ese número = total de registros encontrados
            int total = resultado.getRow();

            // Muestra el resultado por pantalla
            System.out.printf("El nombre '%s' aparece %d vez/veces en la tabla actor.\n",
                nombreBuscar, total);

        } catch (SQLException e) {
            // Captura y muestra cualquier error de base de datos
            System.out.println("Error: " + e.getMessage());
        }
    }
}                 

*/
/*
 * 
 * ESto es con contador un poco mas tedioso
package EjercicioBBDDCoincidentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio1 {
    public static void main(String[] args) {
        String usuario = "admin";
        String password = "1234";
        String server = "jdbc:mysql://localhost:3306/sakila";
        String nombreBuscar = "Mick"; // Nombre que queremos buscar

        try {
            // Abre conexión con la base de datos
            Connection conexion = DriverManager.getConnection(server, usuario, password);
            System.out.println("Conexión realizada con éxito");

            // PreparedStatement permite usar ? como parámetro de forma segura
            // Es más seguro que Statement porque evita SQL Injection
            String consulta = "SELECT * FROM actor WHERE first_name = ?";
            PreparedStatement query = conexion.prepareStatement(consulta);

            // Asignamos el valor al parámetro ?
            // El 1 indica que es el primer ? de la consulta
            // setString porque el nombre es texto
            query.setString(1, nombreBuscar);

            // Ejecutamos la consulta y guardamos los resultados
            ResultSet resultado = query.executeQuery();

            // Contador de coincidencias
            int contador = 0;

            // Recorremos cada fila del resultado
            while (resultado.next()) {
                contador++; // Sumamos 1 por cada actor encontrado
                System.out.printf("Actor encontrado: %s %s\n",
                    resultado.getString("first_name"),
                    resultado.getString("last_name"));
            }

            // Mostramos el total de coincidencias
            System.out.printf("\nEl nombre '%s' aparece %d vez/veces en la tabla actor.\n",
                nombreBuscar, contador);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

*/
