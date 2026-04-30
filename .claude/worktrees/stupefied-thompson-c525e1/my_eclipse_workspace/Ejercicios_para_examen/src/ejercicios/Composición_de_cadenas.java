
package ejercicios;

public class Composición_de_cadenas {

	public static void main(String[] args) {
		   // Declaración de dos cadenas de texto iniciales
        String texto1 = "Examen 1T01";
        String texto2 = "Octubre-2025";

        // Dividir texto1 usando el espacio como separador
        // Resultado: partes1[0] = "Examen", partes1[1] = "1T01"
        String[] partes1 = texto1.split(" ");
        
        // Dividir texto2 usando el guión como separador  
        // Resultado: partes2[0] = "Octubre", partes2[1] = "2025"
        String[] partes2 = texto2.split("-");

        // Construir el resultado combinando las partes:
        // partes1[1] + "-" + partes2[1] + " " + partes1[0] + " " + partes2[0]
        // "1T01" + "-" + "2025" + " " + "Examen" + " " + "Octubre"
        // Resultado intermedio: "1T01-2025 Examen Octubre"
        String resultado = partes1[1] + "-" + partes2[1] + " " + partes1[0] + " " + partes2[0];
        
        // Añadir la suma de las longitudes de ambos textos originales entre paréntesis
        // texto1.length() = 11, texto2.length() = 12, suma = 23
        // Resultado final: "1T01-2025 Examen Octubre (23)"
        resultado += " (" + (texto1.length() + texto2.length()) + ")";

        // Mostrar el resultado final por consola
        System.out.println("Resultado: " + resultado);
	}

}
