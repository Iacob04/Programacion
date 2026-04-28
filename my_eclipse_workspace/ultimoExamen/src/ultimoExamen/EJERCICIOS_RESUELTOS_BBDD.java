package ultimoExamen;

// ╔══════════════════════════════════════════════════════════════════════════╗
// ║   EJERCICIOS RESUELTOS — BASES DE DATOS (JDBC / classicmodels)          ║
// ║   Gabriel Alexandru Iacob | DAM | 3ª Evaluación                         ║
// ║   Todos los patrones que han salido en clase + extras de examen.         ║
// ╚══════════════════════════════════════════════════════════════════════════╝

import java.sql.*;

public class EJERCICIOS_RESUELTOS_BBDD {

    // ─── Credenciales (cambia la BD según el ejercicio) ──────────────────────
    static final String USUARIO  = "admin";
    static final String PASSWORD = "1234";
    // BD disponibles en clase: classicmodels, sakila, logistica_global
    static final String SERVIDOR = "jdbc:mysql://localhost:3306/classicmodels";

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 1 — Mostrar empleados que reportan a un jefe dado por apellido
    //   → Patrón: 2 consultas anidadas, isBeforeFirst para vacío
    // ═══════════════════════════════════════════════════════════════════════
    public static void empleadosQueReportanA(Connection cnx, String apellido) throws SQLException {

        PreparedStatement ps1 = cnx.prepareStatement(
            "SELECT employeeNumber, firstName, lastName FROM employees WHERE lastName = ?"
        );
        ps1.setString(1, apellido);
        ResultSet rs1 = ps1.executeQuery();

        if (!rs1.isBeforeFirst()) {
            System.out.println("No existe ningún empleado con apellido: " + apellido);
            return;
        }

        PreparedStatement ps2 = cnx.prepareStatement(
            "SELECT firstName, lastName FROM employees WHERE reportsTo = ?"
        );

        while (rs1.next()) {
            System.out.println("Jefe: " + rs1.getString("firstName") + " " + rs1.getString("lastName"));
            ps2.setInt(1, rs1.getInt("employeeNumber"));
            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.isBeforeFirst()) {
                System.out.println("  → No le reporta nadie.");
            } else {
                while (rs2.next()) {
                    System.out.println("  → " + rs2.getString("firstName") + " " + rs2.getString("lastName"));
                }
            }
            rs2.close();
        }
        ps2.close(); rs1.close(); ps1.close();
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 2 — Productos con stock menor a X y cuántas veces se han pedido
    //   → Patrón: consulta + subconsulta COUNT(*), rs2.next() una vez
    // ═══════════════════════════════════════════════════════════════════════
    public static void productosStockBajo(Connection cnx, int stockMin) throws SQLException {

        PreparedStatement ps1 = cnx.prepareStatement(
            "SELECT productCode, productName, quantityInStock FROM products WHERE quantityInStock <= ?"
        );
        ps1.setInt(1, stockMin);
        ResultSet rs1 = ps1.executeQuery();

        PreparedStatement ps2 = cnx.prepareStatement(
            "SELECT COUNT(*) FROM orderdetails WHERE productCode = ?"
        );

        System.out.println("Productos con stock ≤ " + stockMin + ":");
        while (rs1.next()) {
            String code  = rs1.getString("productCode");
            String name  = rs1.getString("productName");
            int    stock = rs1.getInt("quantityInStock");

            ps2.setString(1, code);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();                               // COUNT siempre devuelve 1 fila
            int veces = rs2.getInt(1);               // columna 1 = el COUNT

            System.out.printf("  %-40s | Stock: %4d | Pedido %d veces%n", name, stock, veces);
            rs2.close();
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 3 — Contar actores con un nombre dado (sakila)
    //   → Patrón: SCROLL + last() + getRow() para contar sin while
    // ═══════════════════════════════════════════════════════════════════════
    public static void contarActoresPorNombre(Connection cnx, String nombre) throws SQLException {

        // Necesita TYPE_SCROLL para poder usar last() y getRow()
        PreparedStatement ps = cnx.prepareStatement(
            "SELECT * FROM actor WHERE first_name = ?",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        rs.last();
        int total = rs.getRow();         // getRow() en last() = número total de filas

        System.out.printf("El nombre '%s' aparece %d vez/veces.%n", nombre, total);

        // Si quieres recorrer también:
        rs.beforeFirst();
        while (rs.next()) {
            System.out.println("  " + rs.getString("first_name") + " " + rs.getString("last_name"));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 4 — Mostrar tablas de la BD (SHOW TABLES) + nombre de BD actual
    //   → Patrón útil para saber qué tablas existen
    // ═══════════════════════════════════════════════════════════════════════
    public static void mostrarTablas(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery("SHOW TABLES");
        System.out.println("Tablas de la base de datos:");
        while (rs.next()) {
            System.out.println("  " + rs.getString(1));   // columna 1 = nombre de tabla
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 5 — Clientes y sus pedidos (classicmodels: customers + orders)
    //   → Patrón: JOIN en SQL (opción 1) y dos consultas (opción 2)
    // ═══════════════════════════════════════════════════════════════════════
    public static void clientesConPedidos(Connection cnx) throws SQLException {

        // OPCIÓN 1: JOIN (más eficiente, 1 sola consulta)
        PreparedStatement ps = cnx.prepareStatement(
            "SELECT c.customerName, o.orderNumber, o.status " +
            "FROM customers c " +
            "JOIN orders o ON c.customerNumber = o.customerNumber " +
            "ORDER BY c.customerName"
        );
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.printf("Cliente: %-30s | Pedido: %d | Estado: %s%n",
                rs.getString("customerName"),
                rs.getInt("orderNumber"),
                rs.getString("status")
            );
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 6 — Mostrar empleados ordenados por oficina (office)
    //   → Patrón: ORDER BY en la consulta SQL
    // ═══════════════════════════════════════════════════════════════════════
    public static void empleadosPorOficina(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery(
            "SELECT officeCode, firstName, lastName, jobTitle " +
            "FROM employees ORDER BY officeCode"
        );
        String oficinActual = "";
        while (rs.next()) {
            String oficina = rs.getString("officeCode");
            if (!oficina.equals(oficinActual)) {
                System.out.println("\n── Oficina " + oficina + " ──");
                oficinActual = oficina;
            }
            System.out.printf("  %s %s — %s%n",
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("jobTitle")
            );
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 7 — Recorrer ResultSet en INVERSO (afterLast + previous)
    //   → Patrón: sakila o logistica_global, mostrar en orden inverso
    // ═══════════════════════════════════════════════════════════════════════
    public static void mostrarInverso(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        rs.afterLast();                         // posición DESPUÉS de la última fila
        while (rs.previous()) {                 // avanza hacia atrás
            System.out.println("Fila " + rs.getRow() + ": " + rs.getString("firstName"));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 8 — INSERT + UPDATE + DELETE (executeUpdate)
    //   → Patrón: CRUD básico
    // ═══════════════════════════════════════════════════════════════════════
    public static void crud(Connection cnx) throws SQLException {

        // INSERT — devuelve número de filas insertadas
        PreparedStatement ins = cnx.prepareStatement(
            "INSERT INTO offices (officeCode, city, phone, addressLine1, country, postalCode, territory) " +
            "VALUES (?,?,?,?,?,?,?)"
        );
        ins.setString(1, "99");
        ins.setString(2, "Madrid");
        ins.setString(3, "+34 91 555 0000");
        ins.setString(4, "Calle Gran Vía 1");
        ins.setString(5, "Spain");
        ins.setString(6, "28013");
        ins.setString(7, "EMEA");
        System.out.println("Insertadas: " + ins.executeUpdate()); // → 1

        // UPDATE
        PreparedStatement upd = cnx.prepareStatement(
            "UPDATE offices SET city = ? WHERE officeCode = ?"
        );
        upd.setString(1, "Barcelona");
        upd.setString(2, "99");
        System.out.println("Actualizadas: " + upd.executeUpdate());

        // DELETE
        PreparedStatement del = cnx.prepareStatement(
            "DELETE FROM offices WHERE officeCode = ?"
        );
        del.setString(1, "99");
        System.out.println("Borradas: " + del.executeUpdate());
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 9 — Buscar con LIKE (búsqueda parcial)
    //   → Patrón: setString con % delante/detrás
    // ═══════════════════════════════════════════════════════════════════════
    public static void buscarConLike(Connection cnx, String fragmento) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement(
            "SELECT productName, productLine FROM products WHERE productName LIKE ?"
        );
        // % = cualquier cosa antes/después del fragmento
        ps.setString(1, "%" + fragmento + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("productName") + " | " + rs.getString("productLine"));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // EJERCICIO 10 — MAX, MIN, AVG, SUM con SQL
    //   → Una sola fila de resultado, rs.next() una sola vez
    // ═══════════════════════════════════════════════════════════════════════
    public static void estadisticasProductos(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery(
            "SELECT MAX(buyPrice) AS maximo, MIN(buyPrice) AS minimo, " +
            "AVG(buyPrice) AS media, SUM(quantityInStock) AS totalStock " +
            "FROM products"
        );
        if (rs.next()) {  // solo hay 1 fila
            System.out.printf("Precio máximo:  %.2f%n", rs.getDouble("maximo"));
            System.out.printf("Precio mínimo:  %.2f%n", rs.getDouble("minimo"));
            System.out.printf("Precio medio:   %.2f%n", rs.getDouble("media"));
            System.out.printf("Stock total:    %d%n",   rs.getInt("totalStock"));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // MAIN — ejecuta todos los ejercicios
    // ═══════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {

        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
            System.out.println("Conexión OK\n");

            System.out.println("══ Ejercicio 1: Empleados que reportan a Patterson ══");
            empleadosQueReportanA(cnx, "Patterson");

            System.out.println("\n══ Ejercicio 2: Productos con stock < 500 ══");
            productosStockBajo(cnx, 500);

            System.out.println("\n══ Ejercicio 4: Tablas de la BD ══");
            mostrarTablas(cnx);

            System.out.println("\n══ Ejercicio 6: Empleados por oficina ══");
            empleadosPorOficina(cnx);

            System.out.println("\n══ Ejercicio 9: Productos con 'Ford' en el nombre ══");
            buscarConLike(cnx, "Ford");

            System.out.println("\n══ Ejercicio 10: Estadísticas de productos ══");
            estadisticasProductos(cnx);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
