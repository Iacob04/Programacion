package la_liga;

public class Partido {
	
	private Equipo visitante;
	private Equipo local;
	 
	public Partido(Equipo local,Equipo visitante) {
		this.visitante = visitante;
		this.local = local;
		
	}
	
	public void Resultado(int golesLocal, int golesVisitante) {
		if(golesLocal>golesVisitante) {
			local.ganaPartido();
			visitante.pierdePartido();
		}
		else if(golesVisitante > golesLocal){
			visitante.ganaPartido();
			local.pierdePartido();
		}
		else {
			local.empataPartido();
			visitante.empataPartido();
		}
		local.cambiaGoles(golesLocal,golesVisitante);
		visitante.cambiaGoles(golesVisitante,golesLocal);
	}

}
