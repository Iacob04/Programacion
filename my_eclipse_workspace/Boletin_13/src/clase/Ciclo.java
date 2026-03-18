package clase;

public class Ciclo {

    private Modulo[] primero = new Modulo[8];
    private Modulo[] segundo = new Modulo[8];
    private String nombre;
    private String grado;
    int numModulosPrimero = 0;
    int numModulosSegundo = 0;

    public Ciclo(String nombre, String grado) {
        this.nombre = nombre;
        this.grado = grado;
    }

    public void anadeModulo(Modulo m) {
        if (m.getCurso() == 1) {
            primero[this.numModulosPrimero] = m;
            numModulosPrimero++;
        } else {
            segundo[this.numModulosSegundo] = m;
            numModulosSegundo++;
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public Modulo[] getModulos(int curso) {
        return (curso == 1) ? primero : segundo;
    }

    public int getNumModulos(int curso) {
        return (curso == 1) ? numModulosPrimero : numModulosSegundo;
    }
}

