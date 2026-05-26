package concesionario;

public class Main {
	public static void main(String[] args) {
		
		Conductor conductor1 = new Conductor("Alexandru Iacob", "123456R",2004,2024,8);
		
		conductor1.datosConductor();
		
		Coche coche1 = new Coche ("1212RTR", 2025,conductor1);
		
		coche1.datosVehiculo();
		
	}
}
