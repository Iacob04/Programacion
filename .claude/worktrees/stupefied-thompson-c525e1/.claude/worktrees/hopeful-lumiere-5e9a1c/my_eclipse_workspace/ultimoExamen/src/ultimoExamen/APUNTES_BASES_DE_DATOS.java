package ultimoExamen;

// ╔══════════════════════════════════════════════════════════════════════════╗
// ║          APUNTES EXAMEN — BASES DE DATOS (JDBC + MySQL)                 ║
// ║     Gabriel Alexandru Iacob | DAM | 3ª Evaluación                       ║
// ║  COPIA-PEGA DIRECTO. Cambia: servidor, BD, tabla, columnas, parámetros  ║
// ╚══════════════════════════════════════════════════════════════════════════╝

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APUNTES_BASES_DE_DATOS {

    // ═══════════════════════════════════════════════════════════════════
    // DATOS DE CONEXIÓN — CAMBIA ESTOS EN CADA EJERCICIO
    // ═══════════════════════════════════════════════════════════════════
    static String USUARIO  = "admin";
    static String PASSWORD = "1234";
    static String SERVIDOR = "jdbc:mysql://localhost:3306/classicmodels"; // ← cambia la BD

    // ═══════════════════════════════════════════════════════════════════
    // 0. PLANTILLA BASE — la que siempre usas
    // ═══════════════════════════════════════════════════════════════════
    // try-with-resources: cierra la conexión solo aunque haya error
    public static void plantillaBase() {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
            System.out.println("Conexión realizada con éxito");

            // ← tu código aquí

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 1. STATEMENT — consulta simple sin parámetros
    // ═══════════════════════════════════════════════════════════════════
    public static void ejemploStatement() {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {

            Statement stmt = cnx.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                // getString(nombreColumna) o getString(numeroColumna 1..N)
                System.out.println(rs.getInt("employeeNumber") + " - " + rs.getString("firstName"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 2. PREPAREDSTATEMENT — con parámetros (el más usado en examen)
    // ═══════════════════════════════════════════════════════════════════
    // Ventaja: evita SQL injection + admite TYPE_SCROLL_INSENSITIVE
    public static void ejemploPreparedStatement(String apellido) {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {

            // ? = parámetro, se sustituye luego con setXxx()
            PreparedStatement ps = cnx.prepareStatement(
                "SELECT employeeNumber, firstName, lastName FROM employees WHERE lastName = ?"
            );
            ps.setString(1, apellido);  // 1 = posición del primer ?
            // ps.setInt(1, numero);    // para int
            // ps.setDouble(1, valor);  // para double

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.printf("%d | %s %s%n",
                    rs.getInt("employeeNumber"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 3. TYPE_SCROLL_INSENSITIVE — para navegar el ResultSet libremente
    // ═══════════════════════════════════════════════════════════════════
    // Necesario cuando usas: last(), first(), previous(), absolute(), relative()
    public static void ejemploScrollable(String nombre) {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {

            PreparedStatement ps = cnx.prepareStatement(
                "SELECT * FROM actor WHERE first_name = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,   // ← permite navegar
                ResultSet.CONCUR_READ_ONLY            // ← solo lectura
            );
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            // --- CONTAR RESULTADOS SIN WHILE ---
            rs.last();                          // mueve al último
            int total = rs.getRow();            // getRow() = número de fila actual = total
            System.out.println("Total encontrados: " + total);

            rs.beforeFirst();                   // vuelve al principio para recorrer

            while (rs.next()) {
                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 4. MÉTODOS DE NAVEGACIÓN DEL RESULTSET (resumen)
    // ═══════════════════════════════════════════════════════════════════
    /*
        rs.next()           → avanza al siguiente (el más usado)
        rs.previous()       → retrocede (necesita SCROLL)
        rs.first()          → va a la primera fila
        rs.last()           → va a la última fila
        rs.beforeFirst()    → antes de la primera (posición inicial)
        rs.afterLast()      → después de la última (para recorrer con previous)
        rs.absolute(n)      → va a la fila n exacta (n positivo = desde inicio, negativo = desde final)
        rs.relative(n)      → mueve n posiciones desde donde estás
        rs.getRow()         → número de la fila actual (0 si no hay fila)
        rs.isBeforeFirst()  → true si está antes de la primera fila (ResultSet vacío o recién creado)
    */

    // ═══════════════════════════════════════════════════════════════════
    // 5. RECORRER EN ORDEN INVERSO
    // ═══════════════════════════════════════════════════════════════════
    public static void recorrerInverso() {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {

            Statement stmt = cnx.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = stmt.executeQuery("SELECT * FROM almacenes");

            rs.afterLast();                     // posicionarse AL FINAL
            while (rs.previous()) {             // previous() = false cuando no hay más
                System.out.printf("Fila %d: %s%n", rs.getRow(), rs.getString("nombre_sucursal"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 6. COMPROBAR SI EL RESULTSET ESTÁ VACÍO
    // ═══════════════════════════════════════════════════════════════════
    /*
        FORMA 1: isBeforeFirst() — true = ResultSet vacío (cursor antes de la 1ª fila)
            if (!rs.isBeforeFirst()) { ... vacío ... }

        FORMA 2: next() devuelve false en el primer intento
            if (!rs.next()) { System.out.println("Sin resultados"); return; }

        FORMA 3 (con SCROLL): last() devuelve false si está vacío
            if (!rs.last()) { System.out.println("Sin resultados"); return; }
            int total = rs.getRow();
            rs.beforeFirst();   // volver al inicio para recorrer
    */

    // ═══════════════════════════════════════════════════════════════════
    // 7. DOS CONSULTAS ANIDADAS (patrón habitual del examen)
    // ═══════════════════════════════════════════════════════════════════
    // Ej: para cada empleado X, muestra quién le reporta
    public static void consultasAnidadas(Connection cnx, String apellido) throws SQLException {

        // --- CONSULTA 1: encontrar el jefe ---
        PreparedStatement ps1 = cnx.prepareStatement(
            "SELECT employeeNumber, firstName, lastName FROM employees WHERE lastName = ?"
        );
        ps1.setString(1, apellido);
        ResultSet rs1 = ps1.executeQuery();

        if (!rs1.isBeforeFirst()) {             // comprueba si está vacío
            System.out.println("No existe el empleado: " + apellido);
            return;
        }

        // --- CONSULTA 2: dentro del while de la 1ª consulta ---
        PreparedStatement ps2 = cnx.prepareStatement(
            "SELECT firstName, lastName FROM employees WHERE reportsTo = ?"
        );

        while (rs1.next()) {
            int num = rs1.getInt("employeeNumber");
            System.out.println("Jefe: " + rs1.getString("firstName") + " " + rs1.getString("lastName"));

            ps2.setInt(1, num);                 // reutilizamos el mismo PreparedStatement
            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.isBeforeFirst()) {
                System.out.println("  → No le reporta nadie");
            } else {
                while (rs2.next()) {
                    System.out.println("  → " + rs2.getString("firstName") + " " + rs2.getString("lastName"));
                }
            }
            rs2.close();                        // cierra el ResultSet interno en cada iteración
        }

        ps2.close();
        rs1.close();
        ps1.close();
    }

    // ═══════════════════════════════════════════════════════════════════
    // 8. PRODUCTOS CON STOCK < X + CUÁNTAS VECES SE HA PEDIDO
    // ═══════════════════════════════════════════════════════════════════
    // (Ejercicio típico classicmodels)
    public static void productosStockBajo(Connection cnx, int stockMinimo) throws SQLException {

        PreparedStatement ps1 = cnx.prepareStatement(
            "SELECT productCode, productName, quantityInStock FROM products WHERE quantityInStock <= ?"
        );
        ps1.setInt(1, stockMinimo);
        ResultSet rs1 = ps1.executeQuery();

        PreparedStatement ps2 = cnx.prepareStatement(
            "SELECT COUNT(*) FROM orderdetails WHERE productCode = ?"
        );

        while (rs1.next()) {
            String codigo = rs1.getString("productCode");
            System.out.println("Producto: " + rs1.getString("productName") +
                               " | Stock: " + rs1.getInt("quantityInStock"));

            ps2.setString(1, codigo);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();                         // COUNT(*) siempre devuelve 1 fila
            System.out.println("  → Veces pedido: " + rs2.getInt(1)); // columna 1 = el COUNT
            rs2.close();
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 9. INSERT / UPDATE / DELETE  (executeUpdate, no executeQuery)
    // ═══════════════════════════════════════════════════════════════════
    public static void ejemploCRUD() {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {

            // INSERT
            PreparedStatement insert = cnx.prepareStatement(
                "INSERT INTO employees (employeeNumber, firstName, lastName, email, officeCode, jobTitle) VALUES (?,?,?,?,?,?)"
            );
            insert.setInt(1, 9999);
            insert.setString(2, "Alexandru");
            insert.setString(3, "Iacob");
            insert.setString(4, "alex@test.com");
            insert.setString(5, "1");
            insert.setString(6, "Sales Rep");
            int filasInsertadas = insert.executeUpdate(); // devuelve nº de filas afectadas
            System.out.println("Insertadas: " + filasInsertadas);

            // UPDATE
            PreparedStatement update = cnx.prepareStatement(
                "UPDATE employees SET jobTitle = ? WHERE employeeNumber = ?"
            );
            update.setString(1, "Manager");
            update.setInt(2, 9999);
            System.out.println("Actualizadas: " + update.executeUpdate());

            // DELETE
            PreparedStatement delete = cnx.prepareStatement(
                "DELETE FROM employees WHERE employeeNumber = ?"
            );
            delete.setInt(1, 9999);
            System.out.println("Borradas: " + delete.executeUpdate());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // 10. PASAR CONEXIÓN COMO PARÁMETRO (patrón limpio para el examen)
    // ═══════════════════════════════════════════════════════════════════
    /*
        public static void main(String[] args) {
            try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
                miMetodo(cnx, "Patterson");
                otroMetodo(cnx, 500);
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        public static void miMetodo(Connection cnx, String param) throws SQLException {
            // ... usa cnx directamente, sin abrir nueva conexión
        }
    */

    // ═══════════════════════════════════════════════════════════════════
    // 11. TIPOS DE DATOS — getXxx() según el tipo de columna SQL
    // ═══════════════════════════════════════════════════════════════════
    /*
        rs.getString("col")    → VARCHAR, CHAR, TEXT
        rs.getInt("col")       → INT, TINYINT, SMALLINT
        rs.getLong("col")      → BIGINT
        rs.getDouble("col")    → DOUBLE, FLOAT, DECIMAL
        rs.getBoolean("col")   → BOOLEAN, TINYINT(1)
        rs.getDate("col")      → DATE  → java.sql.Date
        rs.getTimestamp("col") → DATETIME, TIMESTAMP → java.sql.Timestamp
        rs.getInt(1)           → por número de columna (empieza en 1)
    */

    // ═══════════════════════════════════════════════════════════════════
    // 12. QUERIES SQL ÚTILES (copiar y pegar)
    // ═══════════════════════════════════════════════════════════════════
    /*
        SELECT * FROM tabla
        SELECT col1, col2 FROM tabla WHERE col = ?
        SELECT * FROM tabla WHERE col LIKE ?          → setString(1, "%texto%")
        SELECT * FROM tabla ORDER BY col ASC/DESC
        SELECT * FROM tabla WHERE col1 = ? AND col2 > ?
        SELECT COUNT(*) FROM tabla WHERE col = ?
        SELECT MAX(col), MIN(col), SUM(col), AVG(col) FROM tabla
        SELECT t1.col, t2.col FROM tabla1 t1 JOIN tabla2 t2 ON t1.id = t2.id WHERE ...
        SHOW TABLES                                    → lista todas las tablas de la BD
        SELECT * FROM tabla LIMIT 10
    */

    // ═══════════════════════════════════════════════════════════════════
    // MAIN DE DEMOSTRACIÓN
    // ═══════════════════════════════════════════════════════════════════
    public static void main(String[] args) {

        // Ejemplo 1: consulta simple
        ejemploPreparedStatement("Patterson");

        // Ejemplo 2: contar con scroll
        ejemploScrollable("Mary");

        // Ejemplo 3: dos consultas anidadas
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
            consultasAnidadas(cnx, "Patterson");
            productosStockBajo(cnx, 500);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
