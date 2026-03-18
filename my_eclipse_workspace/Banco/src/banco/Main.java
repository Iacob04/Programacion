
package banco;

public class Main {

	public static void main(String[] args) {
		
		Banco banco = new Banco("Creditos Informáticos",  "ES68 1234");
		
		
		Sucursal madrid = new Sucursal (banco,"Calle del Pez", 5, "Madrid", 28032, "0078");
		Sucursal sevilla = new Sucursal (banco,"Calle Dolores", 7, "Sevilla", 47181, "3365");
		
		Clientes c1 = new Clientes ("Gabriel Alexandru", "Iacob", "y12345678h", 642335123, madrid);
		Clientes c2 = new Clientes ("Fernando", "Alonso", "78956412G", 654489237, sevilla);
		Clientes c3 = new Clientes ("Mario", "Carcalete", "y12334789h", 642356123, madrid);
		
		Cuentas cuenta1 = new Cuentas(c1,10066, madrid, "123456789112");
		Cuentas cuenta2 = new Cuentas(c1,c2,10067, sevilla, "123456789112");
		
		c1.mostrarClientes();
		
		banco.listarSucursales();
		
		madrid.listarClientes();
		
		cuenta1.verCuenta();
		
	}

}
