package BaseDeDatos;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BBDD4 {
	public static void main(String[] args) {
		String usuario="admin";
        String password = "1234";
        String server = "jdbc:mysql://localhost:3306/sakila"; // URL de conexión: protocolo + host + puerto + nombre de la BD
        JFrame f = new JFrame("Tablas de Sakila");
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Área de texto donde se mostrará el resultado de la consulta
        // - false: no editable por el usuario
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        // ScrollPane para que aparezca barra de desplazamiento
        // si hay más tablas de las que caben en la ventana
        JScrollPane scroll = new JScrollPane(areaTexto);

        // Añadimos el scroll (con el área de texto dentro) a la ventana
        f.add(scroll, BorderLayout.CENTER);
        f.setVisible(true);

        // --- CONEXIÓN Y CONSULTA ---
        try {
            
            Connection conexion = DriverManager.getConnection(server, usuario, password);
            System.out.println("Conexión realizada con éxito");
            Statement consulta = conexion.createStatement();
            ResultSet resultado = consulta.executeQuery("Show tables");
            while (resultado.next()) {
                String tabla = resultado.getString(1);
                System.out.println(tabla);
                areaTexto.append(tabla + "\n");
            }
            
        }catch(Exception e) {
        	areaTexto.append("Error : " +  e.getMessage());
        	System.out.println(" Error  : " + e.getMessage() + " "  
        	+ "posicion :"  + e.getLocalizedMessage());
        }
        
}
}
