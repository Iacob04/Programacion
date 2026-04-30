package carnaval;

import java.util.ArrayList;

public class Sesion {
    private String nombre;
    private String codigo;
    private String presentador;
    private int numAgrupaciones = 0;
    private int siguientePuesto = 0;
    private ArrayList<Agrupacion> participantes = null;
    
    private static int contadorSesiones = 0;
    
    public Sesion(String nombre, String presentador) {
        this.nombre = nombre;
        this.presentador = presentador;
        contadorSesiones++;
        this.codigo = obtenerCodigo();
        System.out.printf("Sesion: %s. %s. Presentador: %s.\n", this.codigo, this.nombre, this.presentador);
    }
    
    public Sesion(String nombre, String presentador, int numAgrupaciones) {
        this.nombre = nombre;
        this.presentador = presentador;
        this.numAgrupaciones = numAgrupaciones;
        this.participantes = new ArrayList<Agrupacion>();
        contadorSesiones++;
        this.codigo = obtenerCodigo();
        System.out.printf("Sesion: %s. %s. Presentador: %s. Max. agrupaciones: %d\n", this.codigo, this.nombre, this.presentador, this.numAgrupaciones);
    }
    
    public static String obtenerCodigo() {
        String codigo = String.valueOf(contadorSesiones);
        for(int i = codigo.length(); i < 3; i++) {
            codigo = "0" + codigo;
        }
        codigo = "SES-" + codigo;
        return codigo;
    }
    
    public void cambiarPresentador(String nuevoPresentador) {
        this.presentador = nuevoPresentador;
        System.out.printf("El presentador de la Sesion %s ha cambiado. Ahora es %s\n", this.codigo, this.presentador);
    }
    
    public void asignarMaxAgrupaciones(int numAgrupaciones) {
        if(this.participantes == null) {
            this.numAgrupaciones = numAgrupaciones;
            this.participantes = new ArrayList<Agrupacion>();
            System.out.printf("%d agrupaciones maximo asignadas a la sesion %s\n", this.numAgrupaciones, this.codigo);
        } else {
            System.out.printf("Ya hay %d agrupaciones maximo asignadas a la sesion %s. Este dato no puede cambiarse\n", this.numAgrupaciones, this.codigo);
        }
    }
    
    public void asignarAgrupacion(Agrupacion a) {
        if(this.participantes == null) {
            System.out.printf("No se puede asignar a %s a la sesion %s. No tiene aun definido el numero de agrupaciones.\n", a.getNombre(), this.codigo);
        } else if(this.siguientePuesto == this.numAgrupaciones) {
            System.out.printf("No se puede asignar a %s a la sesion %s. Maximo de agrupaciones cubierto.\n", a.getNombre(), this.codigo);
        } else {
            this.participantes.add(a);
            this.siguientePuesto++;
            System.out.printf("%s asignado a la sesion %s\n", a.getNombre(), this.codigo);
        }
    }
    
    public void listarInfo() {
        System.out.printf("Sesion %s. %s\n", this.codigo, this.nombre);
        System.out.printf("Presentador: %s\n", this.presentador);
        System.out.printf("Agrupaciones asignadas:\n");
        for(Agrupacion a : participantes) {
            System.out.printf("%s. %s\n", a.getCodigo(), a.getNombre());
        }
    }
}
