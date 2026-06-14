package RA4yRA7_Ordinaria;

public class MainCafeteria {

	public static void main(String[] args) {
		
		CalculadoraPrecio normal = p->{
			
			double r = p.getPrecio()* (1+PvP.iva);
			return Math.round(r*100.0)/100.0;
		};
		
		CalculadoraPrecio descuento = p->{
			
			double r = p.getPrecio()* (1+PvP.descuento);
			return Math.round(r*100.0)/100.0;
		};
		
		Producto producto = new Producto("Capuchino",5.50);
		
		System.out.println(producto);  // usa el toString
		System.out.println("PvP Cliente Normal: " + normal.calcular(producto) + "€");
		System.out.println("PvP Cliente Habitual: " + descuento.calcular(producto) + "€");

	}

}
