package bases;

import java.sql.*; // Importa todas las clases del paquete java.sql (Connection, Statement, ResultSet, etc.)

public class BBBD1 {
    public static void main(String[] args) {

        // Datos de conexión a la base de datos
        String usuario = "admin";
        String password = "1234";
        String server = "jdbc:mysql://localhost:3306/sakila"; // URL de conexión: protocolo + host + puerto + nombre de la BD

        try {
            // Abre la conexión con la base de datos usando las credenciales
            // DriverManager busca el driver adecuado (en este caso MySQL) automáticamente
            Connection conexion = DriverManager.getConnection(server, usuario, password);
            System.out.println("Conexión realizada con éxito");

            // Creamos el Statement con soporte de scroll para poder movernos libremente por los resultados
            // TYPE_SCROLL_INSENSITIVE → permite moverse hacia adelante y atrás (no refleja cambios en la BD en tiempo real)
            // CONCUR_READ_ONLY        → solo lectura, no podemos modificar los datos desde el ResultSet
            // Statement query = conexion.createStatement(
             //    ResultSet.TYPE_SCROLL_INSENSITIVE,
               // ResultSet.CONCUR_READ_ONLY // concur uptube
               // ResultSet.CONCUR_UPDATABLE
           // );
            
            
            PreparedStatement query2 = conexion.prepareStatement("Select * from actor where first_NAME = ?" ,ReasultSetty).TYPE_SCROLL_INSENSITIVE
            query2.setString(1, "Mary");
            query2.setString(2, "Kevin");
            // Consulta SQL que trae todos los registros de la tabla empleados
            String consulta = "SELECT * FROM empleados";

            // Ejecuta la consulta y guarda los resultados en el ResultSet
            // El cursor se posiciona ANTES del primer registro al crearse
            ResultSet resultado = query.executeQuery(consulta);

            // Mueve el cursor AL FINAL de todos los registros
            // Necesario para recorrer los resultados en orden inverso con previous()
            System.out.println("El query tiene " + resultado.getRow() + " filas") ; //numero filas
           //  Quitamos modificacines que si no da error
            //  resultado.absolute(67);
          //  resultado.updateString("first name", "Ines");
          //  resultado.updateString("Lasr name", "Perado");
          //  resultado.updateRow();
          //  resultado.afterLast();

            // Recorre los resultados de ATRÁS hacia ADELANTE
            // previous() devuelve false cuando no hay más registros anteriores
            while (resultado.previous()) {
                // getString("Nombre_completo") → obtiene el valor de la columna "Nombre_completo" como texto
                // getInt("id")                 → obtiene el valor de la columna "id" como número entero
                System.out.printf("%d - Empleado : %s (%d)|n", resultado.getRow(),resultado.getString(2),resultado.getInt(3));
                  
            }

        } catch (SQLException e) {
            // Captura cualquier error relacionado con la base de datos
            // getMessage() devuelve la descripción del error
            System.out.println("Error: " + e.getMessage());
        }
    }
}



// Absolute y relative, ambos llevan numero entero positivo o negativo y situarnos en un fila, positivo en filas del principio y negativo en filas del final
//absolute el total de la tabla , y relative desde la posicion en la que estamos 

//prepare statement cuando quieres hacer una consulta complejo  TYPE_SCROLL_INSENSITIVE