// ============================================================================
//  CuentaBancaria.java
//  ARCHIVO 1 de 2 — La CLASE (con sus atributos, constructor y métodos).
//  Aquí NO hay main. El main está en el otro archivo (Principal.java).
//  Ambos archivos van en el MISMO proyecto / mismo paquete.
// ============================================================================

public class CuentaBancaria {

    // ────────────────────────────────────────────────────────────────────
    // ATRIBUTOS (privados: encapsulación)
    // ────────────────────────────────────────────────────────────────────
    private String titular;
    private double saldo;
    private int    numeroCuenta;

    // static = una sola variable compartida por TODAS las cuentas.
    // Sirve para ir asignando los números 1, 2, 3...
    private static int contadorCuentas = 0;

    // ────────────────────────────────────────────────────────────────────
    // CONSTRUCTOR
    //    (Está DENTRO de la clase: el constructor no puede ir en otro
    //     archivo, siempre vive junto a su clase.)
    //    - Si el saldo inicial es negativo, lo deja a 0.
    //    - Asigna el número de cuenta automáticamente.
    // ────────────────────────────────────────────────────────────────────
    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = (saldoInicial < 0) ? 0 : saldoInicial;   // ternario
        contadorCuentas++;                 // una cuenta más en total
        this.numeroCuenta = contadorCuentas; // este objeto se queda ese nº
    }

    // ────────────────────────────────────────────────────────────────────
    // GETTERS (numeroCuenta NO tiene setter: no debe cambiarse)
    // ────────────────────────────────────────────────────────────────────
    public String getTitular()   { return titular; }
    public double getSaldo()     { return saldo; }
    public int getNumeroCuenta() { return numeroCuenta; }

    public void setTitular(String titular) { this.titular = titular; }

    // ────────────────────────────────────────────────────────────────────
    // INGRESAR: suma al saldo solo si la cantidad es positiva
    // ────────────────────────────────────────────────────────────────────
    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        } else {
            System.out.println("La cantidad a ingresar debe ser positiva.");
        }
    }

    // ────────────────────────────────────────────────────────────────────
    // RETIRAR: resta solo si hay fondos y la cantidad es positiva.
    //          Devuelve true/false según se pudo hacer o no.
    // ────────────────────────────────────────────────────────────────────
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    // ────────────────────────────────────────────────────────────────────
    // STATIC: total de cuentas creadas.
    //         Se llama con la clase:  CuentaBancaria.getTotalCuentas()
    // ────────────────────────────────────────────────────────────────────
    public static int getTotalCuentas() {
        return contadorCuentas;
    }

    // ────────────────────────────────────────────────────────────────────
    // toString: cómo se ve la cuenta al imprimirla
    // ────────────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return String.format("Cuenta nº%d | %s | Saldo: %.2f €",
                             numeroCuenta, titular, saldo);
    }
}
