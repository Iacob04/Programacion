package banco;

import java.util.ArrayList;
import java.util.Iterator;

public class Banco {
    
    private String nombre;
    private String codigo;
    
    private ArrayList<Sucursal> sucursales = new ArrayList<>();
    
    public Banco(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    public void nuevasSucursales(Sucursal s) {
        this.sucursales.add(s);
    }
    
 
    public void listarSucursales() {
    	System.out.println("Banco: " + this.nombre + " / Código: " + this.codigo);
    	System.out.println();
        for (Sucursal s : sucursales) {
        	System.out.println("-" + s.getCiudad()+ " ("+ s.getCodigo()+ ")");
        }
        System.out.println("--------------------------------");
        }
    
    public String getCodigo() {
    	
    	return this.codigo;
    }
    
    
    }


