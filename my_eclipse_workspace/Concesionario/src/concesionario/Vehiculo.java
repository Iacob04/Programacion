package concesionario;

abstract class Vehiculo {
	
	protected String matricula;
	protected int añoFab;
	protected Conductor conductor;
	
	public Vehiculo (String matricula, int anyoFab, Conductor conductor) {
		
		this.matricula = matricula;
		this.añoFab = anyoFab;
		this.conductor = conductor;
		
		
		
	}
	
	

}
