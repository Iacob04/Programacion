package clase;

public class Alumno extends Persona {
    private boolean mayorEdad;
    private String ciclo;
    private String grupo;

    public Alumno(String nom, String ape, int edad, String ciclo, String grupo) {
        super(nom, ape, edad);
        this.ciclo = ciclo;
        this.grupo = grupo;
        this.mayorEdad = edad >= 18;
    }

    public String getNombreCompleto() {
        return super.getNombreCompleto() + 
               ", Edad: " + getEdad() + " años, Ciclo: " + this.ciclo + ", Grupo: " + this.grupo;
    }
}

