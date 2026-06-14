package RA5_Ordinaria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Ejercicios {

	public static void main(String[] args) {
		
		String fichero = "C:\\Users\\alexi\\Programacion\\my_eclipse_workspace\\Extraordinaria\\src\\RA5_Ordinaria\\paises.csv";
		leerFichero(fichero);
		escribirFichero(fichero);

	}
	
	public static void leerFichero(String fichero) {
		
		ArrayList<String> paises = new ArrayList<>();
		ArrayList<String> capitales = new ArrayList<>();
		ArrayList<String> monedas = new ArrayList<>();
		ArrayList<String> animales = new ArrayList<>();

		try (BufferedReader lec = new BufferedReader(new FileReader(fichero))) {

		    lec.readLine(); // Saltar cabecera

		    String linea;
		    while ((linea = lec.readLine()) != null) {
		        String[] c = linea.split(",");
		        if (c.length != 4) {
		            continue; // línea mal formada
		        }
		        paises.add(c[0]);
		        capitales.add(c[1]);
		        monedas.add(c[2]);
		        animales.add(c[3]);
		    }
		    
		    if (paises.isEmpty()) {
	            System.out.println("No hay datos de ningún país en el fichero");
	            return;
	        }
		  
		    System.out.println("Paises en el fichero: "+ paises.size());
		    System.out.print("Nombres: ");

		    for (int i = 0; i < paises.size(); i++) {

		        if (i == paises.size() - 1) {
		            System.out.print(paises.get(i));
		        } else if (i == paises.size() - 2) {
		            System.out.print(paises.get(i) + " y ");
		        } else {
		            System.out.print(paises.get(i) + ", ");
		        }
		    }
		    System.out.println();
		    System.out.print("La capital de los mismos son: ");
		    for (int i = 0; i < capitales.size(); i++) {

		        if (i == capitales.size() - 1) {
		            System.out.print(capitales.get(i));
		        } else if (i == capitales.size() - 2) {
		            System.out.print(capitales.get(i) + " y ");
		        } else {
		            System.out.print(capitales.get(i) + ", ");
		        }
		    }
		    
		    System.out.println();
		    System.out.print("Sus monedas oficiales son: ");
		    for (int i = 0; i < monedas.size(); i++) {

		        if (i == monedas.size() - 1) {
		            System.out.print(monedas.get(i));
		        } else if (i == monedas.size() - 2) {
		            System.out.print(monedas.get(i) + " y ");
		        } else {
		            System.out.print(monedas.get(i) + ", ");
		        }
		    }
		    
		    System.out.println();
		    System.out.print("Sus animales más representativos son ");
		    for (int i = 0; i < animales.size(); i++) {

		        if (i == animales.size() - 1) {
		            System.out.print(animales.get(i));
		        } else if (i == animales.size() - 2) {
		            System.out.print(animales.get(i) + " y ");
		        } else {
		            System.out.print(animales.get(i) + ", ");
		        }
		    }

		} catch (Exception e) {
		   
		}
	}
	
	public static void escribirFichero(String fichero) {
		 ArrayList<String[]> datos = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

	            String linea = br.readLine(); // cabecera

	            if (linea == null) {
	                System.out.println("No hay datos de ningún país en el fichero");
	                return;
	            }

	            while ((linea = br.readLine()) != null) {

	                String[] c = linea.split(",");

	                if (c.length != 4) {
	                    continue;
	                }

	                // solo país, capital, animal
	                datos.add(new String[]{c[0], c[1], c[3]});
	            }

	        } catch (Exception e) {
	            
	        }

	        if (datos.isEmpty()) {
	            System.out.println("No hay datos de ningún país en el fichero");
	            return;
	        }

	        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero))) {

	            pw.println("País,Capital,Animal");

	            for (String[] d : datos) {
	                pw.println(d[0] + "," + d[1] + "," + d[2]);
	            }

	        } catch (Exception e) {
	            
	        }
		
	}

}
