package empresa;

import java.util.ArrayList;

public class Conductor extends Persona {

    private int anyos_experiencia;
    private Empresa empresa;
    private ArrayList<Envio> envios = new ArrayList<>();

    public Conductor(String nombre, String apellidos, String DNI, int anyos_experiencia, Empresa empresa) {
        super(nombre, apellidos, DNI);

        this.anyos_experiencia = anyos_experiencia;
        this.empresa = empresa;
        this.empresa.anyadeConductor(this);
    }

    public void registrarEnvio(Envio e) {
        envios.add(e);
    }

    public void listarEnvios() {
        System.out.println("Envíos realizados por el conductor " 
                           + getNombre() + " " + getApellidos() + ":");

        if (envios.isEmpty()) {
            System.out.println("No tiene envíos registrados.");
            return;
        }

        for (Envio e : envios) {
            System.out.println(e);
            System.out.println();
        }
    }

    public double calcularGastoTotal() {
        double total = 0;

        for (Envio e : envios) {
            total += e.getCoste();
        }

        System.out.println("Gasto total del cliente " + getNombre() + " " + getApellidos() + ": " + total + " €");

        return total;
    }

   
    public String toString() {
        return "Conductor: " + getNombre() + " " + getApellidos() +
               ", DNI: " + getDNI() +
               ", Años experiencia: " + anyos_experiencia;
    }
}
