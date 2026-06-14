package RA5_EjercicioPractica1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GestionEmpleados {

	public static void main(String[] args) {
		
		String fichero = "C:\\Users\\alexi\\Programacion\\my_eclipse_workspace\\Extraordinaria\\src\\RA5_EjercicioPractica1\\empleados.csv";
		leerFichero(fichero);
		

	}
	
	public static void leerFichero(String fichero) {
		
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<String> departamentos = new ArrayList<>();
		ArrayList<Double> salarios = new ArrayList<>();
		ArrayList<Integer> antiguedades = new ArrayList<>();

		try (BufferedReader lec = new BufferedReader(new FileReader(fichero))) {

		    lec.readLine(); // Saltar cabecera

		    String linea;
		    while ((linea = lec.readLine()) != null) {
		        String[] c = linea.split(",");
		        if (c.length != 4) {
		            continue; // línea mal formada
		        }
		        nombres.add(c[0]);
		        departamentos.add(c[1]);
		         Double salario = Double.parseDouble(c[2]);
		         salarios.add(salario);
		         int antiguedad = Integer.parseInt(c[3]);
		        antiguedades.add(antiguedad);
		    }
		    
		    if (nombres.isEmpty()) {
	            System.out.println("No hay empleados en el fichero");
	            return;
	        }
		  
		    System.out.println("Empleados en el fichero: "+ nombres.size());
		    System.out.print("Sus nombres son: ");

		    for (int i = 0; i < nombres.size(); i++) {

		        if (i == nombres.size() - 1) {
		            System.out.print(nombres.get(i));
		        } else if (i == nombres.size() - 2) {
		            System.out.print(nombres.get(i) + " y ");
		        } else {
		            System.out.print(nombres.get(i) + ", ");
		        }
		    }
		    System.out.println();
		    System.out.print("Trabajan en los departamentos: ");
		    for (int i = 0; i < departamentos.size(); i++) {

		        if (i == departamentos.size() - 1) {
		            System.out.print(departamentos.get(i));
		        } else if (i == departamentos.size() - 2) {
		            System.out.print(departamentos.get(i) + " y ");
		        } else {
		            System.out.print(departamentos.get(i) + ", ");
		        }
		    }
		    
		    System.out.println();
		    System.out.print("El salario medio de la plantilla es: ");
		    double total  = 0;
		    for (int i = 0; i < salarios.size(); i++) {

		       total = total + salarios.get(i);
		    }
		    System.out.printf("%.2f€",(total/salarios.size()));
		    
		    System.out.println();
		    System.out.print("El empleado con mayor antigüedad es: ");
		    String nombre = "";
		    int antiguedad = 0;
		    for (int i = 0; i < antiguedades.size(); i++) {
		    	
		    	if(antiguedades.get(i) > antiguedad) {
		    		antiguedad = antiguedades.get(i);
		    		nombre = nombres.get(i);
		    	}
		       
		    }
		    System.out.printf("%s, con %d años",nombre,antiguedad);
		   

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
