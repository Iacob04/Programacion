package examenesAutoescuela;

public class Main {

	public static void main(String[] args) {

		Examen examen = new Examen();

		Preguntas p1 = new Preguntas("Si veo una señal triangular apuntando hacia abajo con fondo blanco y borde rojo, ¿qué significa?");
		p1.añadirRespuesta(new Respuestas("Ceda el paso", true));
		p1.añadirRespuesta(new Respuestas("STOP", false));
		p1.añadirRespuesta(new Respuestas("Límite de velocidad", false));

		Preguntas p2 = new Preguntas("¿Está permitido adelantar en una línea continua?");
		p2.añadirRespuesta(new Respuestas("No, en ningún caso", true));
		p2.añadirRespuesta(new Respuestas("Sí, siempre", false));
		p2.añadirRespuesta(new Respuestas("Depende de la situación", false));
		
		Preguntas p3 = new Preguntas("¿Está permitido montar en unicornio?");
		p3.añadirRespuesta(new Respuestas("No, en ningún caso", true));
		p3.añadirRespuesta(new Respuestas("Sí, siempre", false));
		p3.añadirRespuesta(new Respuestas("Depende de la situación", false));

		examen.añadirPregunta(p1);
		examen.añadirPregunta(p2);
		examen.añadirPregunta(p3);

		examen.mostrarExamen();
	}
}

