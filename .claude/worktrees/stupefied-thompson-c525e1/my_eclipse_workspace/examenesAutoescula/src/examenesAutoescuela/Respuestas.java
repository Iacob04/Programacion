package examenesAutoescuela;

public class Respuestas {

	private String texto;
	private boolean correcta;

	public Respuestas(String texto, boolean correcta) {
		this.texto = texto;
		this.correcta = correcta;
	}

	public String getTexto() {
		return texto;
	}

	public boolean isCorrecta() {
		return correcta;
	}
}

