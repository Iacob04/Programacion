package concesionario;

public class Coche extends Vehiculo {

	public Coche(String matricula, int anyoFab, Conductor conductor) {

		super(matricula, anyoFab, conductor);

	}

	public void datosVehiculo() {
		System.out.println(this.matricula +", " + this.añoFab +", "+ this.conductor.getNIF());
	}
}
