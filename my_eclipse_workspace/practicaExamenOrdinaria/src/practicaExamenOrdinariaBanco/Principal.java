// ============================================================================
//  Principal.java
//  ARCHIVO 2 de 2 — La clase con el MAIN (el programa que se ejecuta).
//  Usa la clase CuentaBancaria, que está en el OTRO archivo del proyecto.
//  Para ejecutar el programa: clic derecho sobre Principal.java -> Run.
// ============================================================================

public class Principal {

    public static void main(String[] args) {

        // Creamos 3 cuentas. Cada "new" llama al CONSTRUCTOR de CuentaBancaria
        // (que está en el otro archivo) y asigna el número automáticamente.
        CuentaBancaria c1 = new CuentaBancaria("Ana", 500);    // nº1, saldo 500
        CuentaBancaria c2 = new CuentaBancaria("Luis", -100);  // nº2, saldo -> 0
        CuentaBancaria c3 = new CuentaBancaria("Marta", 1000); // nº3, saldo 1000

        // --- Operaciones ---
        c1.ingresar(200);              // Ana: 500 -> 700
        c1.ingresar(-50);              // inválido: avisa y no cambia nada

        boolean ok = c3.retirar(1500); // Marta no tiene 1500 -> false
        System.out.println("¿Retirada de 1500 de Marta correcta? " + ok);

        c3.retirar(300);               // Marta: 1000 -> 700

        // --- Mostramos cada cuenta (usa el toString) ---
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        // --- Total de cuentas creadas (método static: se llama con la clase) ---
        
        System.out.println("Total de cuentas creadas: "
                          + CuentaBancaria.getTotalCuentas());
    }
}

/*
 ─────────────────────────────────────────────────────────────────────────────
  SALIDA ESPERADA:
 ─────────────────────────────────────────────────────────────────────────────
  La cantidad a ingresar debe ser positiva.
  ¿Retirada de 1500 de Marta correcta? false
  Cuenta nº1 | Ana | Saldo: 700,00 €
  Cuenta nº2 | Luis | Saldo: 0,00 €
  Cuenta nº3 | Marta | Saldo: 700,00 €
  Total de cuentas creadas: 3
 ─────────────────────────────────────────────────────────────────────────────
*/
