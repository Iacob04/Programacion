package carnaval;

public class Main {
    public static void main(String[] args) {
        
        // CREACION DE AGRUPACIONES
        Chirigota c1 = new Chirigota("Los Inhumanos", 8.50, 10);
        Chirigota c2 = new Chirigota("El Yuyu de Cai", 9.00, 8);
        Cupletista cup1 = new Cupletista("Juan Carlos Aragon", 9.50, "Grosero");
        Cupletista cup2 = new Cupletista("Raulito el de la Guitarra", 8.75, "Fino");
        Chirigota c3 = new Chirigota("La Chirigota del Selu", 8.25, 9);
        
        // CREACION DE SESIONES
        Sesion s1 = new Sesion("Preliminares - Sesion 1", "Manu Sanchez", 6);
        Sesion s2 = new Sesion("Cuartos de Final", "Juanjo Artero");
        Sesion s3 = new Sesion("Semifinal", "Manu Sanchez");
        
        // CAMBIOS EN LAS SESIONES
        s2.cambiarPresentador("Manu Sanchez");
        s2.asignarMaxAgrupaciones(5);
        s1.asignarMaxAgrupaciones(4);
        
        // ASIGNACION DE AGRUPACIONES A LAS SESIONES
        s2.asignarAgrupacion(cup1);
        s2.asignarAgrupacion(cup2);
        s2.asignarAgrupacion(c3);
        s3.asignarAgrupacion(c3);
        
        // LISTADO DE INFORMACION DE UNA SESION
        s2.listarInfo();
        
        // CALCULO DE PUNTUACIONES
        c1.calcularPuntuacion();
        cup1.calcularPuntuacion();
        c2.calcularPuntuacion();
    }
}
