package clase;

public class Profesor extends Persona {
    private String departamento;
    private Grupo tutoria;

    public Profesor(String nom, String ape, String departamento) {
        super(nom, ape);

        if (!departamento.equalsIgnoreCase("Informatica") &&
            !departamento.equalsIgnoreCase("Empresa") &&
            !departamento.equalsIgnoreCase("Ingles")) {
            System.out.println("ERROR: Departamento no válido");
        } else {
            this.departamento = departamento.toUpperCase();
        }
    }

    public void setTutoria(Grupo grupo) {
        this.tutoria = grupo;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }
}
