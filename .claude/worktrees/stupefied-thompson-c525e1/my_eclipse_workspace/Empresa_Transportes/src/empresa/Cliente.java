package empresa;

import java.util.ArrayList;

public class Cliente extends Persona {

    private Empresa empresa;
    private ArrayList<Envio> envios = new ArrayList<>();

    public Cliente(String nombre, String apellidos, String DNI, Empresa empresa) {
        super(nombre, apellidos, DNI);

        this.empresa = empresa;
        this.empresa.anyadeCliente(this);
    }

    public void registrarEnvio(Envio e) {
        envios.add(e);
    }

    public void listarEnvios() {
        System.out.println("Envíos del cliente " 
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
        return "Cliente: " + getNombre() + " " + getApellidos() +
               ", DNI: " + getDNI();
    }
}
