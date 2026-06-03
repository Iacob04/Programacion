package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class solucionOrdinaria {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/naciones";

        try (Connection cnx = DriverManager.getConnection(url, "alex", "1234")) {
            System.out.println("Conexión realizada con éxito");

            
            idiomasHablan(cnx, "Zimbabwe");
            hacerOficial(cnx, "Spai", "Spanish");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }  

    
    public static void idiomasHablan(Connection cnx, String pais) throws SQLException {

        PreparedStatement sql = cnx.prepareStatement(
            "SELECT name, language, official FROM countries " +
            "JOIN country_languages USING (country_id) " +
            "JOIN languages USING (language_id) " +
            "WHERE name = ?"
        );

        sql.setString(1, pais);
        ResultSet rs = sql.executeQuery();

        ArrayList<String> oficiales = new ArrayList<>();
        ArrayList<String> otros     = new ArrayList<>();

        while (rs.next()) {
            String idioma = rs.getString("language");
            int oficial   = rs.getInt("official");

            if (oficial == 1) {
                oficiales.add(idioma);
            } else {
                otros.add(idioma);
            }
        }

        // País no existe en la BD
        if (oficiales.isEmpty() && otros.isEmpty()) {
            System.out.println(pais + " no es un país real o no está dado de alta en la base de datos");
            return;
        }

        // Idiomas oficiales
        if (oficiales.isEmpty()) {
            System.out.println("No hay idioma oficial en " + pais);
        } else {
            System.out.println("Idioma oficial en " + pais + ": " + String.join(", ", oficiales));
        }

        // Otros idiomas
        if (otros.isEmpty()) {
            System.out.println("No hay otros idiomas");
        } else {
            System.out.println("Otros idiomas: " + String.join(", ", otros));
        }
    }
    
    
    public static void hacerOficial(Connection cnx, String pais, String idioma) throws SQLException {

        // 1. Verificar que el país existe
        PreparedStatement sqlPais = cnx.prepareStatement(
            "SELECT country_id FROM countries WHERE name = ?"
        );
        sqlPais.setString(1, pais);
        ResultSet rsPais = sqlPais.executeQuery();

        if (!rsPais.next()) {
            System.out.println(pais + " no es un país real o no está dado de alta en la base de datos");
            return;
        }
        int countryId = rsPais.getInt("country_id");

        // 2. Verificar que el idioma existe
        PreparedStatement sqlIdioma = cnx.prepareStatement(
            "SELECT language_id FROM languages WHERE language = ?"
        );
        sqlIdioma.setString(1, idioma);
        ResultSet rsIdioma = sqlIdioma.executeQuery();

        if (!rsIdioma.next()) {
            System.out.println("El " + idioma + " no es un idioma real o no está dado de alta en la base de datos");
            return;
        }
        int languageId = rsIdioma.getInt("language_id");

        // 3. Verificar que el idioma se habla en ese país
        PreparedStatement sqlRelacion = cnx.prepareStatement(
            "SELECT official FROM country_languages WHERE country_id = ? AND language_id = ?"
        );
        sqlRelacion.setInt(1, countryId);
        sqlRelacion.setInt(2, languageId);
        ResultSet rsRelacion = sqlRelacion.executeQuery();

        if (!rsRelacion.next()) {
            System.out.println("El idioma " + idioma + " no se habla en " + pais + " y no puedo hacerlo oficial");
            return;
        }

        // 4. Verificar si ya es oficial
        int oficial = rsRelacion.getInt("official");
        if (oficial == 1) {
            System.out.println("El " + idioma + " ya es idioma oficial en " + pais);
            return;
        }

        // 5. Actualizar a oficial
        PreparedStatement sqlUpdate = cnx.prepareStatement(
            "UPDATE country_languages SET official = 1 WHERE country_id = ? AND language_id = ?"
        );
        sqlUpdate.setInt(1, countryId);
        sqlUpdate.setInt(2, languageId);
        sqlUpdate.executeUpdate();

        System.out.println("El " + idioma + " es ahora idioma oficial en " + pais);
    }
}


