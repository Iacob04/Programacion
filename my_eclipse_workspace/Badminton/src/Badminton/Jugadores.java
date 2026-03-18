package Badminton;
import java.time.LocalDate;
public class Jugadores extends Persona{
	
	protected int añoNacimiento;
	public String categoria;
	public int añoActual = LocalDate.now().getYear();
	public Clubes club;
	public String tipoLicencia;
	public String genero;
	
	
	public Jugadores(Clubes club,int generos,String nombre,String apellido,String DNI,int añoNacimiento,String nacionalidad,int licencia) {
		super(licencia,apellido,DNI,nacionalidad,nombre,generos);
		
		this.añoNacimiento = añoNacimiento;
		this.club = club;
		
		this.club.anyadeJugador(this);
		
	}
	
	public String Genero() {	
		
		if(generos == 1) {
			genero = "Hombre";
		}
		else {
			genero = "Mujer";
		}
		return genero;
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
	
	public String Categorias() {
		
		if (añoActual - añoNacimiento >= 21) {
			categoria = "Absoluto";
		}
		else if(añoActual - añoNacimiento >= 19) {
			categoria = "Sub 21";
		}
		else if(añoActual - añoNacimiento <= 18 && añoActual - añoNacimiento >= 17) {
			categoria = "Sub 19";
		}
		else if(añoActual - añoNacimiento <= 16 && añoActual - añoNacimiento >= 13) {
			categoria = "Sub 17";
		}
		else {
			categoria = "Minibadminton";
		}
		
		return categoria;
		
	}
	
	
	public Clubes getClubJugador() {
		
		return this.club;
		
	}

}
