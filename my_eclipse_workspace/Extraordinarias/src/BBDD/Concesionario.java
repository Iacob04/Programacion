package BBDD;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Concesionario {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/concesionario";

        try (Connection cnx = DriverManager.getConnection(url, "admin", "1234")) {
            System.out.println("Conexión realizada con éxito");
            
            listadoDisponibles(cnx, 0);
            busquedaPorMarcaRecuento(cnx, "BMW");
            busquedaPorMarcaInverso(cnx, "BMW");
            datosComerciales(cnx);
            nuevoVehiculo(cnx, "BMW", "M4", 2026, 5,67000.43, 0);
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

	}

	
	public static void listadoDisponibles(Connection cnx, int vendido)throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT codigo , marca , modelo , anyo, kilometros, precio FROM vehiculos "
				+ "WHERE vendido = ? "
				);
		sql.setInt(1, vendido);
		ResultSet rs = sql.executeQuery();
		System.out.println("Listado de coches disponibles:");
		while (rs.next()) {
			String codigo = rs.getString("codigo");
			String marca = rs.getString("marca");
			String modelo = rs.getString("modelo");
			int anyo = rs.getInt("anyo");
			int km = rs.getInt("kilometros");
			double precio = rs.getDouble("precio");
			
			System.out.printf("- Código: %s | Marca: %s | Modelo: %s | Año (%d) | Kilómetros: %d | Precio: %.2f %n", codigo,marca,modelo,anyo,km,precio);
			
		}
		
	}
	
	
	public static void busquedaPorMarcaRecuento(Connection cnx, String marca)throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT  codigo, modelo, anyo ,kilometros, precio, vendido FROM vehiculos "
				+ "WHERE marca = ? " ,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

			sql.setString(1, marca);
			
			ResultSet rs = sql.executeQuery();
			
			rs.last();    
			
			int cuantos = rs.getRow();
			
			if (cuantos == 0) {
				System.out.println("No hay vehículos de la marca: " + marca );
				
			}
			else {
				System.out.println("La marca "+ marca +" tiene "+ cuantos + " vehículos: ");
				rs.beforeFirst();
				while (rs.next()) {
					String codigo = rs.getString("codigo");
					int vendido = rs.getInt("vendido");
					String modelo = rs.getString("modelo");
					int anyo = rs.getInt("anyo");
					int km = rs.getInt("kilometros");
					double precio = rs.getDouble("precio");
		            
		            if(vendido == 1) {
		            	System.out.printf("- Código: %s | Modelo: %s | Año (%d) | Kilómetros: %d | Precio: %.2f | Vendido: SI  %n" , codigo,modelo,anyo,km,precio,vendido);
		            }
		            else {
		            	System.out.printf("- Código: %s | Modelo: %s | Año (%d) | Kilómetros: %d | Precio: %.2f | Vendido: NO  %n" , codigo,modelo,anyo,km,precio,vendido);
		            }
		        }
			}
		
	}
	
	
	public static void busquedaPorMarcaInverso(Connection cnx, String marca)throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT  codigo, modelo, anyo ,kilometros, precio, vendido FROM vehiculos "
				+ "WHERE marca = ? " ,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

			sql.setString(1, marca);
			
			ResultSet rs = sql.executeQuery();
			
			rs.last();   
			
			int cuantos = rs.getRow();
			
			if (cuantos == 0) {
				System.out.println("No hay vehículos de la marca: " + marca );
				
			}
			else {
				System.out.println("La marca "+ marca +" tiene "+ cuantos + " vehículos: ");
				rs.afterLast();
				while (rs.previous()) {
					
					String modelo = rs.getString("modelo");
					double precio = rs.getDouble("precio");
		            
		            
		            	System.out.printf("-  Modelo: %s | Precio: %.2f   %n" , modelo,precio);
		            
		            
		        }
			}
		
	}
	
	public static void marcarComoVendido(Connection cnx ,String codigoCoche ,String comercial, int vendido)throws SQLException{
		
		PreparedStatement sql = cnx.prepareStatement(
				"SELECT precio FROM vehiculos "
				+ "WHERE codigo = ? "
				);
		sql.setString(1, codigoCoche);
		ResultSet rs = sql.executeQuery();
		
		if (!rs.next()) {                      
	        System.out.println("No existe el vehículo " + codigoCoche);
	        return;
	    }
	    double precio = rs.getDouble("precio");
		
		PreparedStatement sqlUpdate = cnx.prepareStatement(
		        "UPDATE vehiculos SET vendido = ? WHERE codigo = ?"
		);
		sqlUpdate.setInt(1, vendido);
		sqlUpdate.setString(2, codigoCoche);
		int filasUpdate = sqlUpdate.executeUpdate();
	    System.out.println("Vehículos actualizados: " + filasUpdate);
		
		PreparedStatement insVenta = cnx.prepareStatement(
		        "INSERT INTO ventas (codigo_vehiculo, codigo_comercial, fecha_venta, precio_final) VALUES (?, ?, CURDATE(), ?)"
		);
		insVenta.setString(1, codigoCoche);
		insVenta.setString(2, comercial);
		insVenta.setDouble(3, precio);
		int filasVenta = insVenta.executeUpdate();
	    System.out.println("Ventas registradas: " + filasVenta);
		
		
	}
	
	public static void datosComerciales(Connection cnx) throws SQLException {

	    PreparedStatement sqlComercial = cnx.prepareStatement(
	            "SELECT nombre, codigo, apellidos, email, salario_base, fecha_alta FROM comerciales",
	            ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY
	    );
	    ResultSet rs = sqlComercial.executeQuery();

	    // La consulta de ventas se prepara UNA sola vez y se reutiliza en cada vuelta
	    PreparedStatement sqlVentas = cnx.prepareStatement(
	            "SELECT codigo_vehiculo, fecha_venta, precio_final FROM ventas WHERE codigo_comercial = ?",
	            ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY
	    );

	    while (rs.next()) {
	        String codigo_comercial = rs.getString("codigo");
	        String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellidos");
	        String email = rs.getString("email");
	        double salario_base = rs.getDouble("salario_base");
	        Date fecha_alta = rs.getDate("fecha_alta");

	        System.out.println("----------------------------------");
	        System.out.println("Datos del comercial: " + rs.getString("nombre"));
	        System.out.println("----------------------------------");
	        System.out.printf(" CODIGO DE COMERCIAL: %s%n NOMBRE COMPLETO: %s%n EMAIL: %s%n SALARIO BASE: %.2f€%n FECHA DE ALTA: %tD%n",
	                codigo_comercial, nombreCompleto, email, salario_base, fecha_alta);

	        // Reutilizamos el mismo PreparedStatement: solo cambia el parámetro
	        sqlVentas.setString(1, codigo_comercial);
	        ResultSet rsVentas = sqlVentas.executeQuery();

	        rsVentas.last();
	        int contadorVentas = rsVentas.getRow();

	        if (contadorVentas == 0) {
	            System.out.println("Sin ventas registradas");
	        } else {
	            System.out.println();
	            System.out.println("Total de ventas realizadas: " + contadorVentas);
	            rsVentas.beforeFirst();
	            while (rsVentas.next()) {
	                String codigo_vehiculo = rsVentas.getString("codigo_vehiculo");
	                Date fecha_venta = rsVentas.getDate("fecha_venta");
	                double precio_final = rsVentas.getDouble("precio_final");

	                System.out.printf("- Codigo Vehículo: %s | Fecha Venta: %tD | Precio final: %.2f€%n",
	                        codigo_vehiculo, fecha_venta, precio_final);
	            }
	        }
	        rsVentas.close();   // cerramos el ResultSet interno en cada vuelta
	    }
	    sqlVentas.close();
	    // rs y sqlComercial se cierran solos al cerrarse la conexión del try-with-resources del main
	}
	
	public static void nuevoVehiculo (Connection cnx , String marca, String modelo, int anyo, int kilometros ,double precio,int vendido)throws SQLException {
		
		PreparedStatement ultimoCodigo = cnx.prepareStatement(
			    "SELECT codigo FROM vehiculos ORDER BY codigo DESC LIMIT 1"
			);

			ResultSet rs = ultimoCodigo.executeQuery();
			
			String nuevoCodigo = "";

			if (rs.next()) {
			    String ultimo = rs.getString("codigo").substring(4);

			    int numeroCodigo = Integer.parseInt(ultimo);
			    numeroCodigo++;

			    nuevoCodigo = "VEH-" + String.format("%03d", numeroCodigo);

			    System.out.println(nuevoCodigo);
			}
		
		PreparedStatement insertarVehículo = cnx.prepareStatement(
				"INSERT INTO vehiculos(codigo,marca, modelo, anyo, kilometros,precio, vendido) VALUES (?,?,?,?,?,?,?) "
				);
		insertarVehículo.setString(1, nuevoCodigo);
		insertarVehículo.setString(2, marca);
		insertarVehículo.setString(3, modelo);
		insertarVehículo.setInt(4, anyo);
		insertarVehículo.setInt(5, kilometros);
		insertarVehículo.setDouble(6, precio);
		insertarVehículo.setInt(7, vendido);
		
		int insertadas = insertarVehículo.executeUpdate();
		System.out.println("Insertadas: " + insertadas);
		
	}
	
	
}


