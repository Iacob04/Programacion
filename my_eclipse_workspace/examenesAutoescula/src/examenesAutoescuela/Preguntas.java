package examenesAutoescuela;

import java.util.ArrayList;

public class Preguntas {

	private String texto;
	private ArrayList<Respuestas> respuestas = new ArrayList<>();

	public Preguntas(String texto) {
		this.texto = texto;
	}

	public void añadirRespuesta(Respuestas r) {
		respuestas.add(r);
	}

	public String getTexto() {
		return texto;
	}

	public ArrayList<Respuestas> getRespuestas() {
		return respuestas;
	}
}

