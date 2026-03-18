package empresa;

import java.util.ArrayList;
import java.util.HashSet;


public class Empresa {
	
	private String nombre;
	
	private HashSet<Conductor> conductores = new HashSet<>();
	private HashSet<Vehiculo> vehiculos = new HashSet<>();
	private HashSet<Cliente> clientes = new HashSet<>();
	private HashSet<Ruta> rutas = new HashSet<>();
	private HashSet<Envio> envios = new HashSet<>();
	
	public Empresa(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public void anyadeConductor(Conductor c) {
		
		this.conductores.add(c);
	}
	
	public void anyadeVehiculo(Vehiculo v) {
		
		this.vehiculos.add(v);
	}
	
	public void anyadeCliente(Cliente cl) {
	
	this.clientes.add(cl);
	}
	
	public void anyadeRuta(Ruta r) {
	
	this.rutas.add(r);
	}
	
	public void anyadeEnvio(Envio e) {
		
		this.envios.add(e);
	}
	
	public void mostrarEmpresa() {
		
		System.out.println("Los Clientes de la empresa son estos;");
		for(Cliente cl: clientes) {
		
			System.out.println(cl);
			System.out.println();
			
		}
		System.out.println("--------------------------------------");
		System.out.println("Los Conductores de la empresa son estos;");
		for(Conductor c: conductores) {
		
			System.out.println(c);
			System.out.println();
			
		}
		System.out.println("--------------------------------------");
		
		System.out.println("Los Vehiculos de la empresa son estos;");
		
		for(Vehiculo v: vehiculos) {
			
			System.out.println(v);
			System.out.println();
			
		}
		System.out.println("--------------------------------------");
		System.out.println("Los Envios realizados por la empresa son;");
		for(Envio e: envios) {
		
		
			System.out.println(e);
			System.out.println();
			
		}
	}
	
	

}
