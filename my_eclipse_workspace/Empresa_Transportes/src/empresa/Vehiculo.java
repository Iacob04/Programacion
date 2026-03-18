package empresa;

import java.util.ArrayList;

public class Vehiculo {

    private String matricula;
    private String tipo_vehiculo;
    private Conductor conductor;
    private Empresa empresa;
    private ArrayList<Envio> envios = new ArrayList<>();

    public Vehiculo(String matricula, String tipo_vehiculo, Conductor conductor, Empresa empresa) {
        this.matricula = matricula;
        this.tipo_vehiculo = tipo_vehiculo;
        this.conductor = conductor;
        this.empresa = empresa;
        this.empresa.anyadeVehiculo(this);
    }

    public void registrarEnvio(Envio e) {
        envios.add(e);
    }

    public void listarEnvios() {
        System.out.println("Envíos realizados con el vehículo " 
                           + matricula + " (" + tipo_vehiculo + "):");

        if (envios.isEmpty()) {
            System.out.println("No tiene envíos registrados.");
            return;
        }

        for (Envio e : envios) {
            System.out.println(e);
            System.out.println();
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipoVehiculo() {
        return tipo_vehiculo;
    }

    
    public String toString() {
        return "Vehículo: " + tipo_vehiculo +
               ", Matrícula: " + matricula +
               ", Conductor: " + conductor.getNombre() + " " + conductor.getApellidos();
    }
}
