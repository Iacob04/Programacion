package practicaExamenOrdinaria;
import java.sql.*;

public class gestionEmpleados {

    static final String URL  = "jdbc:mysql://localhost:3306/empresa";
    static final String USER = "admin";
    static final String PASS = "1234";

    // --- Listar empleados de un departamento ---
    public static void listarPorDepartamento(Connection cnx, String depto)
            throws SQLException {
        String sql = "SELECT id, nombre, salario FROM empleados WHERE departamento = ?";
        PreparedStatement ps = cnx.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, depto);
        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {                 // no hay resultados
            System.out.println("No hay empleados en " + depto);
            return;
        }
        System.out.println("=== Empleados de " + depto + " ===");
        while (rs.next()) {
            System.out.printf("  [%d] %s - %.2f €%n",
                rs.getInt("id"), rs.getString("nombre"), rs.getDouble("salario"));
        }
    }

    // --- Insertar empleado ---
    public static void insertar(Connection cnx, String nombre,
                                String depto, double salario) throws SQLException {
        String sql = "INSERT INTO empleados (nombre, departamento, salario) VALUES (?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, depto);
        ps.setDouble(3, salario);
        int filas = ps.executeUpdate();            // INSERT -> executeUpdate
        System.out.println(filas + " empleado(s) insertado(s).");
    }

    // --- Subir salario un porcentaje ---
    public static void subirSalario(Connection cnx, int id, double porcentaje)
            throws SQLException {
        String sql = "UPDATE empleados SET salario = salario * (1 + ?/100) WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setDouble(1, porcentaje);
        ps.setInt(2, id);
        int filas = ps.executeUpdate();
        System.out.println(filas + " salario(s) actualizado(s).");
    }

    // --- Contar empleados (COUNT) ---
    public static void contarEmpleados(Connection cnx) throws SQLException {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM empleados");
        rs.next();                                 // una sola fila
        System.out.println("Total de empleados: " + rs.getInt(1));
    }

    // --- Salario medio por departamento (GROUP BY) ---
    public static void salarioMedioPorDepartamento(Connection cnx) throws SQLException {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT departamento, AVG(salario) AS media " +
            "FROM empleados GROUP BY departamento");
        System.out.println("=== Salario medio por departamento ===");
        while (rs.next()) {
            System.out.printf("  %s: %.2f €%n",
                rs.getString("departamento"), rs.getDouble("media"));
        }
    }

    // --- MAIN: una sola conexión para todo ---
    public static void main(String[] args) {
        try (Connection cnx = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Conexión correcta.\n");

            insertar(cnx, "Ana López", "Ventas", 1800);
            insertar(cnx, "Luis Gil", "IT", 2200);

            listarPorDepartamento(cnx, "Ventas");
            subirSalario(cnx, 1, 10);              // +10% al empleado id=1
            contarEmpleados(cnx);
            salarioMedioPorDepartamento(cnx);

        } catch (SQLException e) {
            System.out.println("Error de BD: " + e.getMessage());
        }
    }
}
