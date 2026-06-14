package RA4yRA7Practica;

public class MainGimnasio {

	public static void main(String[] args) {
		
		CalculadoraCuota anual = c->{
			
			double r = c.getPrecioMensual()*(1+Recargos.iva);
			return Math.round(r*100.0)/100.0;
			
		};
		
		CalculadoraCuota fraccionado = c->{
			
			double r = c.getPrecioMensual()*(1+Recargos.iva)* (1+Recargos.fraccionado);
			return Math.round(r*100.0)/100.0;
			
		};
		
		Cuota premium = new Cuota("Premium", 45.00);
		System.out.println(premium);
		System.out.println("Pago Anual: "+ anual.calcularCuota(premium)+"€");
		System.out.println("Pago Fraccionado: "+ fraccionado.calcularCuota(premium)+"€");
		
	}

}
