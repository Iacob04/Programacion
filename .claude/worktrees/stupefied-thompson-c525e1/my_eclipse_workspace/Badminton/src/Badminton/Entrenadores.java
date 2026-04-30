package Badminton;

public class Entrenadores extends Persona{
	
	public Clubes club;
	public String tipoLicencia;
	
	public Entrenadores(Clubes club,int generos,String nombre, String apellido, String DNI, String nacionalidad,int licencia) {
		super(licencia,apellido,DNI,nacionalidad,nombre,generos);
		
		this.club = club;
		this.club.anyadeEntrenador(this);
		
	}
	
	public Clubes getClubEntrenador() {
		
		return this.club;
		
	}
	public String Licencia() {
		if(licencia == 1) {
			tipoLicencia = "Promocional";
		}
		else if (licencia == 2) {
			tipoLicencia = "Territorial";
		}
		else if (licencia == 3){
			tipoLicencia = "Nacional";
		}
		else {
			tipoLicencia = "Sin Licencia";
		}
		return tipoLicencia;
	}
	

}
