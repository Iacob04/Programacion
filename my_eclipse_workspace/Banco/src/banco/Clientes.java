
package banco;

import java.util.ArrayList;

public class Clientes {

	private String nombre;
	private String apellido;
	private String NIF;
	private int telefono;
	private Sucursal sucursal;
	
	private ArrayList<Cuentas> cuentas = new ArrayList<>();
	
	public Clientes(String nombre, String apellido, String NIF, int telefono, Sucursal sucursal) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.NIF = NIF;
		this.telefono = telefono;
		this.sucursal = sucursal;
		this.sucursal.anyadeCliente(this);
		
	}
	
	public void mostrarClientes(){
		
		System.out.println("Nombre completo: "+nombre + " " + apellido);
		System.out.println("NIF: "+ NIF);
		System.out.println("Número de teléfono: "+ telefono);
		System.out.println("Sucursal a la que está abonado: "+ sucursal);
	}
	
	public String getNIF() {
		return this.NIF;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public void anyadeCuenta(Cuentas cc) {
		
		this.cuentas.add(cc);
		
	}
	
	
	

}
