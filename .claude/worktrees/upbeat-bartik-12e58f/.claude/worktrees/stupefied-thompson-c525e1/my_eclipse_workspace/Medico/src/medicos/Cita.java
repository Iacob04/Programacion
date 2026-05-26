package medicos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Cita {
	
	private Medico medico;
	private Paciente paciente;
	private LocalDate fechaCita;
	
	public Cita(Medico medico) {
		
		this.medico = medico;
		this.fechaCita = LocalDate.now().plusWeeks(1);
	
	}
	public String getFechaCita() {
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	return fechaCita.format(formato);
	}
	
	public Medico getMedico() {
		
		return this.medico;
	}

}
