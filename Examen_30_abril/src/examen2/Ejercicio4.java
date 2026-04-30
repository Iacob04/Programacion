package examen2;

public class Ejercicio4 {

	public static void main(String[] args) {
		
		Rectangulo area = (l1,l2) -> {
		
		double resultado = (double)Math.round(1000*(l1*l2))/1000;
		return resultado;
		};
		
		System.out.println("Área : "+area.numeros(3.3, 2.33));
		
		Rectangulo perimetro = (l1,l2) -> {
			
			double resultado = (double)Math.round(1000*((2*l1)+(2*l2)))/1000;
			return resultado;
			};
		
		System.out.println( "Perímetro : "+perimetro.numeros(3.34444, 2.33));

	}

}
