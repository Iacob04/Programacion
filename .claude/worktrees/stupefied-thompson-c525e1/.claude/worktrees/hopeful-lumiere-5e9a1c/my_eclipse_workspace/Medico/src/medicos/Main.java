package medicos;

public class Main {

	public static void main(String[] args) {
		
		Especialidad urologia = new Especialidad("Urología");
		Especialidad cardiologia = new Especialidad("Cardiología");
		Especialidad familia = new Especialidad("Medico de Familia");
		
		Medico medico1 = new Medico("Mario","Carcalete",familia);
		Medico medico2 = new Medico("Alexandru","Iacob",cardiologia);
		Medico medico3 = new Medico("María","Carcaleta",familia);
		
		
		Paciente paciente1 = new Paciente("Sara","Garcia",2,"12345678H");
		Paciente paciente2 = new Paciente("Sergio","Serrano",21,"12345578H");
		
		
		paciente1.pideCita(familia);
		paciente2.pideCita(familia);
		paciente1.pideCita(familia);
		
		urologia.listarMedicos();
		cardiologia.listarMedicos();
		familia.listarMedicos();
		
		paciente1.anulaCita(familia);
		paciente1.anulaCita(familia);
	}

}
