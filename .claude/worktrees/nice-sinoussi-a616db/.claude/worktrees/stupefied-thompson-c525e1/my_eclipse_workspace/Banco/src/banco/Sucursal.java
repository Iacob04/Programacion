
package banco;

import java.util.ArrayList;

public class Sucursal {
	
	private String calle;
	private int numero;
	private int codigoPostal;
	private String ciudad;
	private String codigo;
	private Banco banco;
	
	private ArrayList<Clientes> clientes = new ArrayList<>();
	private ArrayList<Cuentas> cuentas = new ArrayList<>();
	
	
	public Sucursal(Banco b, String calle, int numero, String ciudad, int codigoPostal, String codigo) {
	
		this.banco = b;
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.ciudad = ciudad;
		this.codigo = codigo;
		
		b.nuevasSucursales(this);
	}
	
	public String getCiudad() {
		
		return this.ciudad;
	}
	
	public String getCodigo() {
		
		return this.codigo;
	}
	
	public void anyadeCliente(Clientes cliente) {
		clientes.add(cliente);
		
	}
	
	public void anyadeCuenta(Cuentas cc) {
		
		this.cuentas.add(cc);
		
	}
	
	 public void listarClientes() {
	    	System.out.println("Sucursal: " + this.ciudad + " / Código: (" + this.codigo+")");
	    	System.out.println();
	        for (Clientes c : clientes) {
	        	System.out.println("-" + c.getApellido()+ " "+ c.getNombre()+ " "+ " - NIF:(" + c.getNIF() + ")");
	        	System.out.println();
	        }
	        System.out.println("-------------------------------------------");
	        }
	 
	 public void listarCuentas() {
		 
		 System.out.println("Cuentas: " + this.ciudad);
	 }
	 
	 public String getCodigoCompleto() {
		 
		 return banco.getCodigo() + " " + this.codigo;
	 }
	 
	 
	    }
	

		
	
	
	


