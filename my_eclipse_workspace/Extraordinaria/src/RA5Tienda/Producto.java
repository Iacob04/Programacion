package RA5Tienda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Producto {
	
	private String codigo;
	private String nombre;
	private double precio;
	private int stock;
	
	public Producto (String codigo, String nombre, double precio , int stock) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		
	}
	
	
	 	public String getCodigo() { return codigo; }
	    public String getNombre() { return nombre; }
	    public double getPrecio() { return precio; }
	    public int    getStock()  { return stock; }
	    
	    public static void escribirBinario(String fichero, ArrayList<Producto> productos) {
	        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(fichero))) {
	            salida.writeInt(productos.size());        // 1) cuantos hay
	            for (Producto p : productos) {            // 2) cada uno, EN ORDEN
	                salida.writeUTF(p.getCodigo());
	                salida.writeUTF(p.getNombre());
	                salida.writeDouble(p.getPrecio());
	                salida.writeInt(p.getStock());
	            }
	        } catch (Exception e) {
	            System.out.println("Error al escribir: " + e.getMessage());
	        }
	    }

	    public static ArrayList<Producto> leerBinario(String fichero) {
	        ArrayList<Producto> productos = new ArrayList<>();
	        try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))) {
	            int n = entrada.readInt();                // contador primero
	            for (int i = 0; i < n; i++) {             // MISMO orden que al escribir
	                String codigo = entrada.readUTF();
	                String nombre = entrada.readUTF();
	                double precio = entrada.readDouble();
	                int stock     = entrada.readInt();
	                productos.add(new Producto(codigo, nombre, precio, stock));
	            }
	        } catch (Exception e) {
	            System.out.println("Error al leer: " + e.getMessage());
	        }
	        return productos;
	    }

}
