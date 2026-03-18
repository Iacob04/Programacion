
package boletin_15;

import java.time.LocalDate;

public class poo {

	public static void main(String[] args) {
		
		Centro_medico centro1 = new Centro_medico("La Elipa", "CM-0001");
		Centro_medico centro2 = new Centro_medico("Ciudad de los Angeles", "CM-0033");
		
		Medico medico1 = new Medico("Alexandru", "Iacob","Neuro Cirugia","010101",centro1);
		Medico medico2 = new Medico("Daniel", "Segura","Urologo","010102",centro1);
		Medico medico3 = new Medico("Sergio", "Serrano","Ginecologo","010103",centro2);
		Medico medico4 = new Medico("Sara", "Garcia","Podología","010104",centro2);
		
		Paciente paciente1 = new Paciente("Mario","Carcalete","X9011345R",665556665, centro1);
		Paciente paciente2 = new Paciente("Marcos","Pineros","X9011555R",665557665, centro2);
		
		
		Consulta c1 = new Consulta(paciente1,medico2,LocalDate.now(),"Le duele la capeza. ", " Que tome paracetamol :) ");
		Consulta c2 = new Consulta(paciente2,medico1,LocalDate.now(),"Le duele el pie. ", " Hay que cortarlo :) ");
		//c1.muestraConsulta();
		//c2.muestraConsulta();
		
		medico1.listaConsultas();

		//paciente1.mostrarPaciente();
		//System.out.println();
		
		
		//centro1.listaPacientes();
		//System.out.println("--------------");
		//paciente2.cambioCentro(centro1);
		//centro1.listaPacientes();
		
		
	}

}
