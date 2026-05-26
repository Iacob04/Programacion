package practicaExamenOrdinaria;

import java.util.HashMap;

public class analisisVotosHashMap {

	public static void main(String[] args) {
	    String[] votos = {
                "Ana", "luis", "ANA", "", "Luis",
                "ana", "Marta", "luis", "  "
        };

        HashMap<String, Integer> resultados = new HashMap<>();

        int nulos = 0;

        for (int i = 0; i < votos.length; i++) {

            if (votos[i] == null || votos[i].trim().isEmpty()) {
                nulos++;
            } else {

                String nombre = votos[i].trim().toLowerCase();

                if (resultados.containsKey(nombre)) {
                    resultados.put(nombre, resultados.get(nombre) + 1);
                } else {
                    resultados.put(nombre, 1);
                }
            }
        }

        System.out.println("Ana: " + resultados.get("ana"));
        System.out.println("Luis: " + resultados.get("luis"));
        System.out.println("Marta: " + resultados.get("marta"));
        System.out.println("Nulos: " + nulos);
        
        String ganador = "";
        int maxVotos = 0;

        for(String nombre : resultados.keySet()) {

            int votosCandidato = resultados.get(nombre);

            if(votosCandidato > maxVotos) {
                maxVotos = votosCandidato;
                ganador = nombre;
            }
        }

        System.out.println("El ganador es " + ganador +
                           " con " + maxVotos + " votos");

	}

}
