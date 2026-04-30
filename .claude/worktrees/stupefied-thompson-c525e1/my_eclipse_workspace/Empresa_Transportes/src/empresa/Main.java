package empresa;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		Empresa empresa1 = new Empresa ("Manolo S.A");
		
		Conductor conductor1 = new Conductor ("Alexandru","Iacob","Y122589H",21, empresa1);
		Conductor conductor2 = new Conductor ("Constantin","Iacob","Y12489H",33, empresa1);
		Conductor conductor3 = new Conductor ("Elena Irina","Iacob","Y44589H",30, empresa1);
		Conductor conductor4 = new Conductor ("Miguel","Cervantes","34456Y",10, empresa1);
		
		Cliente cliente1 = new Cliente ("Yanira","Ñieto Linares", "12345678H" ,empresa1);
		Cliente cliente2 = new Cliente ("Sara","Garcia", "12345678H" ,empresa1);
		Cliente cliente3 = new Cliente ("Daniel","Segura", "12345678H" ,empresa1);
		
		Vehiculo vehiculo1 = new Vehiculo ("1919MBN","Coche",conductor1,empresa1);
		Vehiculo vehiculo2 = new Vehiculo ("1819MNN","Moto",conductor2,empresa1);
		Vehiculo vehiculo3 = new Vehiculo ("1919MBN","Coche",conductor3,empresa1);
		
		Ruta r1 = new Ruta ("Madrid","Valencia",345);
		Ruta r2 = new Ruta ("Salamnca","Valencia",445);
		Ruta r3 = new Ruta ("Madrid","Murcia",387);
		empresa1.anyadeRuta(r1);
		empresa1.anyadeRuta(r2);
		empresa1.anyadeRuta(r3);
		
		Envio envio1 = new Envio (cliente1,conductor2,vehiculo2,r2,LocalDate.parse("2010-03-25"),28.55);
		empresa1.anyadeEnvio(envio1);
		Envio envio2 = new Envio (cliente1,conductor3,vehiculo3,r2,LocalDate.parse("2026-03-25"),458.55);
		empresa1.anyadeEnvio(envio2);
		
		//empresa1.mostrarEmpresa();
		//cliente1.calcularGastoTotal();
		//vehiculo2.listarEnvios();
		cliente1.listarEnvios();
		
		
	}

}
