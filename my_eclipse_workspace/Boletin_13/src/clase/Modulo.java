package clase;

public class Modulo {

    private String nombre;
    private int horasSemana;
    private int curso;
    private boolean optativa;

    public Modulo(String nombre, int horasSemana, int curso, boolean optativa) {
        this.nombre = nombre;
        this.horasSemana = horasSemana;
        this.optativa = optativa;
        this.curso = curso;
    }

    public int getCurso() {
        return this.curso;
    }

    public String getNombre() {
        return this.nombre;
    }
}
