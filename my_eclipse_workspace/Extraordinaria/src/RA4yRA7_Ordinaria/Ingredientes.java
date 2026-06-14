package RA4yRA7_Ordinaria;

public class Ingredientes {

    private String nombre;
    private Integer cantidad;   // CAMBIO IMPORTANTE
    private String medida;

    // con cantidad
    public Ingredientes(String nombre, Integer cantidad, String medida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.medida = medida;
    }

    // sin cantidad
    public Ingredientes(String nombre) {
        this.nombre = nombre;
        this.cantidad = null;
        this.medida = null;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getMedida() {
        return medida;
    }
}