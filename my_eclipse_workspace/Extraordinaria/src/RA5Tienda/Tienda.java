package RA5Tienda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Tienda {

	public static void main(String[] args) {
		
		String ficheroArticulos = "C:\\Users\\alexi\\Programacion\\my_eclipse_workspace\\Extraordinaria\\src\\RA5Tienda\\articulos.txt";
		String ficheroVentas = "C:\\Users\\alexi\\Programacion\\my_eclipse_workspace\\Extraordinaria\\src\\RA5Tienda\\ventas.txt";
		
		leerFicheros(ficheroArticulos, ficheroVentas);
		
		   String fichero = "productos.dat";

	        ArrayList<Producto> productos = new ArrayList<>();
	        productos.add(new Producto("P01", "Raton",     9.95, 30));
	        productos.add(new Producto("P02", "Teclado",  19.50, 12));
	        productos.add(new Producto("P03", "Monitor", 129.00,  5));

	        Producto.escribirBinario(fichero, productos);
	        ArrayList<Producto> leidos = Producto.leerBinario(fichero);

	        System.out.println("--- Productos leidos del fichero binario ---");
	        for (Producto p : leidos) {
	            System.out.printf("%s | %-10s | %s € | stock: %d%n",
	                p.getCodigo(), p.getNombre(),
	                String.format("%.2f", p.getPrecio()).replace(".", ","),
	                p.getStock());
	        }
		

	}
	
	public static void leerFicheros(String ficheroArticulos, String ficheroVentas) {
		
		HashMap<String,String> articulos = new HashMap<>();
		HashMap<String, Double> precioArticulos = new HashMap<>();
		HashMap<String, Integer> cantidadVendida = new HashMap<>();
		
		
		try (BufferedReader lec = new BufferedReader(new FileReader(ficheroArticulos))) {
		    String linea;
		   
		    while ((linea = lec.readLine()) != null) {   
		        
		    	int pos = linea.indexOf(" ");
		    	int posArticulo = pos +1;
		   
		        String codigo = linea.substring(0,pos);
		       // System.out.println(codigo);
		        int posPrecio = linea.indexOf(" ",posArticulo);
		        String articulo = linea.substring(posArticulo,posPrecio);
		       // System.out.println(articulo);
		        double precioArticulo = Double.parseDouble(linea.substring(posPrecio + 1 ));
		       // System.out.println(precioArticulo);
		        articulos.put(codigo, articulo);
		        precioArticulos.put(codigo, precioArticulo);
		        
		        
		        
		    }
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		try (BufferedReader lec = new BufferedReader(new FileReader(ficheroVentas))) {
		    String linea;
		   
		    while ((linea = lec.readLine()) != null) {   
		        
		    	int pos = linea.indexOf(" ");
		   
		        String codigo = linea.substring(0,pos);
		       // System.out.println(codigo);
		        int cantidad = Integer.parseInt(linea.substring(pos +1));
		       // System.out.println(cantidad);
		        cantidadVendida.put(codigo, cantidad);
		        
		        
		        
		    }
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		
		// Casos 1 y 2: recorrer ventas
		for (String codigo : cantidadVendida.keySet()) {
		    if (articulos.containsKey(codigo)) {
		        // CASO 1: existe en ambos
		        String nombre = articulos.get(codigo);
		        double precio = precioArticulos.get(codigo);
		        int cantidad  = cantidadVendida.get(codigo);
		        double importe = precio * cantidad;
		        System.out.printf("%s %s: %d uds x %s € = %s €%n",
		            codigo, nombre, cantidad,
		            String.format("%.2f", precio).replace(".", ","),
		            String.format("%.2f", importe).replace(".", ","));
		    } else {
		        // CASO 2: en ventas pero no en el catálogo
		        System.out.printf("%s: codigo no encontrado en el catalogo%n", codigo);
		    }
		}

		// Caso 3: artículos sin ventas
		for (String codigo : articulos.keySet()) {
		    if (!cantidadVendida.containsKey(codigo)) {
		        System.out.printf("%s %s: sin ventas registradas%n",
		            codigo, articulos.get(codigo));
		    }
		}
		
	}

}
