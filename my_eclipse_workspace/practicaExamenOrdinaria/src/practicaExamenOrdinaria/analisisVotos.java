package practicaExamenOrdinaria;

public class analisisVotos {

	public static void main(String[] args) {
	
		String[] votos = {"Ana", "luis", "ANA", "", "Luis",
                "ana", "Marta", "luis", "  "};
		
		int contadorAna = 0;
		int contadorLuis = 0;
		int contadorMarta = 0;
		
		int nulos = 0;
		
		for(int i = 0; i< votos.length; i++) {
			
			if(votos[i] == null || votos[i].trim().isEmpty()) {
			    nulos++;
			
			}
			else {
				String limpiar = votos[i];
				String limpio = limpiar.trim().toLowerCase();
				
				if(limpio.equals("ana")) {
					contadorAna++;
					
				}
				else if(limpio.equals("luis")) {
					contadorLuis++;
				}
				else {
					contadorMarta++;
				}
			}
			
		}
		
		System.out.println("Votos para Ana: "+ contadorAna);
		System.out.println("Votos para Luis: "+ contadorLuis);
		System.out.println("Votos para Marta: "+ contadorMarta);
		System.out.println("Votos nulos: "+ nulos);
		
		
		if(contadorAna > contadorLuis && contadorAna > contadorMarta) {
		    System.out.println("La ganadora es Ana");
		}
		else if(contadorLuis > contadorAna && contadorLuis > contadorMarta) {
		    System.out.println("El ganador es Luis");
		}
		else if(contadorMarta > contadorAna && contadorMarta > contadorLuis) {
		    System.out.println("La ganadora es Marta");
		}
		else {
		    System.out.println("Hay empate");
		}
		

	}

}
