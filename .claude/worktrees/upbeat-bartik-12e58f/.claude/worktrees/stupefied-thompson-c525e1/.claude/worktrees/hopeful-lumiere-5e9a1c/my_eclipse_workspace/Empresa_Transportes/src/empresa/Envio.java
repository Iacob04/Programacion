package empresa;

import java.time.LocalDate;

public class Envio {

    private Cliente cliente;
    private Conductor conductor;
    private Vehiculo vehiculo;
    private Ruta ruta;
    private LocalDate fecha;
    private double coste;

    public Envio(Cliente cliente, Conductor conductor, Vehiculo vehiculo, Ruta ruta, LocalDate fecha, double coste) {
        this.cliente = cliente;
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.ruta = ruta;
        this.fecha = fecha;
        this.coste = coste;

        cliente.registrarEnvio(this);
        conductor.registrarEnvio(this);
        vehiculo.registrarEnvio(this);
    }

    public Ruta getRuta() {
        return ruta;
    }

    public double getCoste() {
        return coste;
    }

    
    public String toString() {
        return "Envío (" + fecha + ")\n" +
               "Cliente: " + cliente.getNombre() + " " + cliente.getApellidos() + "\n" +
               "Conductor: " + conductor.getNombre() + " " + conductor.getApellidos() + "\n" +
               "Vehículo: " + vehiculo.getTipoVehiculo() + " (" + vehiculo.getMatricula() + ")\n" +
               "Ruta: " + ruta.getOrigen() + " → " + ruta.getDestino() + "\n" +
               "Coste: " + coste + " €";
    }
}
