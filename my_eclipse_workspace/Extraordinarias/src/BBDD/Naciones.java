package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Naciones {

    public static void main(String[] args) {

        String url  = "jdbc:mysql://localhost:3306/naciones";
        String usr  = "admin";
        String pswd = "1234";

        String sql = "SELECT countries.name, countries.area " +
                     "FROM countries " +
                     "JOIN regions USING (region_id) " +
                     "JOIN continents USING (continent_id) " +
                     "WHERE continents.name = ? " +
                     "ORDER BY area DESC";
        
        String continente = "Europe";

 

        try (Connection cnx = DriverManager.getConnection(url, usr, pswd);
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, continente);

            ResultSet rs = ps.executeQuery();

            boolean encontrado = false;

            while (rs.next()) {
                encontrado = true;
                String nombre = rs.getString("name");
                double area   = rs.getDouble("area");
                System.out.println(nombre + " - " + area);
            }

            // ← esto va DENTRO del try, después del while
            if (!encontrado) {
                System.out.println("\"" + continente + "\" no es un continente real o no está dado de alta en la base de datos.");
            }

        } catch (SQLException e) {
            System.out.println("Error de BD: " + e.getMessage());
        }
        
        
    }
}
