package Badminton;

public class Main {

	public static void main(String[] args) {
		
		Clubes club1 = new Clubes("Chamartín");
		Clubes club2 = new Clubes("El Espinillo");
		Clubes club3 = new Clubes("Poona");
		Clubes club4 = new Clubes("Paracuellos de Jarama");
		Clubes club5 = new Clubes("Bad Fly");
		Clubes club6 = new Clubes("La Orden");
	
		
		// CLUB 1: Chamartín (7 jugadores)
		
		Jugadores j1 = new Jugadores(club1, 1, "Carlos", "Martínez", "12345678A", 2005, "Española", 1);
		Jugadores j2 = new Jugadores(club1, 2, "Ana", "García", "23456789B", 2003, "Francesa", 2);
		Jugadores j3 = new Jugadores(club1, 1, "Luis", "Rodríguez", "34567890C", 2007, "Española", 3);
		Jugadores j4 = new Jugadores(club1, 2, "María", "López", "45678901D", 2010, "Española", 4);
		Jugadores j5 = new Jugadores(club1, 1, "Pedro", "Sánchez", "56789012E", 2001, "Portuguesa", 1);
		Jugadores j6 = new Jugadores(club1, 2, "Laura", "Pérez", "67890123F", 2006, "Española", 2);
		Jugadores j7 = new Jugadores(club1, 1, "Javier", "Gómez", "78901234G", 2012, "Española", 3);

		// CLUB 2: El Espinillo (5 jugadores)
		Jugadores j8 = new Jugadores(club2, 1, "Miguel", "Navarro", "11223344K", 2002, "Española", 1);
		Jugadores j9 = new Jugadores(club2, 2, "Carmen", "Ramírez", "22334455L", 2005, "Española", 2);
		Jugadores j10 = new Jugadores(club2, 1, "Roberto", "Herrera", "33445566M", 2009, "Argentina", 3);
		Jugadores j11 = new Jugadores(club2, 2, "Patricia", "Castro", "44556677N", 2000, "Española", 4);
		Jugadores j12 = new Jugadores(club2, 1, "Andrés", "Ortega", "55667788O", 2006, "Chilena", 1);

		// CLUB 3: Poona (9 jugadores)
		Jugadores j13 = new Jugadores(club3, 1, "Ricardo", "Vargas", "12121212U", 1998, "Mexicana", 1);
		Jugadores j14 = new Jugadores(club3, 2, "Silvia", "Campos", "23232323V", 2001, "Española", 2);
		Jugadores j15 = new Jugadores(club3, 1, "Gabriel", "Reyes", "34343434W", 2004, "Española", 3);
		Jugadores j16 = new Jugadores(club3, 2, "Marta", "Aguilar", "45454545X", 2008, "Española", 4);
		Jugadores j17 = new Jugadores(club3, 1, "Hugo", "Silva", "56565656Y", 2011, "Brasileña", 1);
		Jugadores j18 = new Jugadores(club3, 2, "Paula", "Mendoza", "67676767Z", 1999, "Española", 2);
		Jugadores j19 = new Jugadores(club3, 1, "Sergio", "Flores", "78787878A", 2006, "Española", 3);
		Jugadores j20 = new Jugadores(club3, 2, "Claudia", "Guerrero", "89898989B", 2009, "Española", 4);
		Jugadores j21 = new Jugadores(club3, 1, "Víctor", "Arias", "90909090C", 2012, "Española", 1);

		// CLUB 4: Paracuellos de Jarama (6 jugadores)
		Jugadores j22 = new Jugadores(club4, 1, "Alejandro", "Moreno", "13131313E", 2000, "Española", 1);
		Jugadores j23 = new Jugadores(club4, 2, "Lucía", "Herrero", "24242424F", 2003, "Española", 2);
		Jugadores j24 = new Jugadores(club4, 1, "Pablo", "Medina", "35353535G", 2006, "Española", 3);
		Jugadores j25 = new Jugadores(club4, 2, "Valentina", "Santos", "46464646H", 2009, "Colombiana", 4);
		Jugadores j26 = new Jugadores(club4, 1, "Martín", "Cortés", "57575757I", 2012, "Española", 1);
		Jugadores j27 = new Jugadores(club4, 2, "Camila", "Vega", "68686868J", 1997, "Española", 2);

		// CLUB 5: Bad Fly (8 jugadores)
		Jugadores j28 = new Jugadores(club5, 1, "Sebastián", "Vidal", "14141414O", 1999, "Española", 1);
		Jugadores j29 = new Jugadores(club5, 2, "Antonella", "León", "25252525P", 2002, "Italiana", 2);
		Jugadores j30 = new Jugadores(club5, 1, "Maximiliano", "Cabrera", "36363636Q", 2005, "Española", 3);
		Jugadores j31 = new Jugadores(club5, 2, "Renata", "Fuentes", "47474747R", 2008, "Española", 4);
		Jugadores j32 = new Jugadores(club5, 1, "Benjamín", "Carrasco", "58585858S", 2011, "Española", 1);
		Jugadores j33 = new Jugadores(club5, 2, "Julieta", "Hidalgo", "69696969T", 1996, "Española", 2);
		Jugadores j34 = new Jugadores(club5, 1, "Thiago", "Arias", "70707070U", 2004, "Española", 3);
		Jugadores j35 = new Jugadores(club5, 2, "Emilia", "Soto", "81818181V", 2007, "Española", 4);

		// CLUB 6: La Orden (4 jugadores)
		Jugadores j36 = new Jugadores(club6, 1, "Joaquín", "Sanz", "15151515Y", 2001, "Española", 1);
		Jugadores j37 = new Jugadores(club6, 2, "Amparo", "Gil", "26262626Z", 2004, "Española", 2);
		Jugadores j38 = new Jugadores(club6, 1, "Gonzalo", "Soler", "37373737A", 2007, "Española", 3);
		Jugadores j39 = new Jugadores(club6, 2, "Rocío", "Blanco", "48484848B", 2010, "Española", 4);
		
		// Entrenadores con licencia 3 (mínimo requerido) y 4
		Entrenadores entrenador1 = new Entrenadores(club1, 1, "Carlos", "García", "12345678A", "Española", 3);
		Entrenadores entrenador2 = new Entrenadores(club1, 1, "Miguel", "Fernández", "23456789B", "Española", 4);

		Entrenadores entrenador3 = new Entrenadores(club2, 1, "Andrés", "López", "34567890C", "Argentina", 3);
		Entrenadores entrenador4 = new Entrenadores(club2, 2, "María", "Sánchez", "45678901D", "Española", 4);

		Entrenadores entrenador5 = new Entrenadores(club3, 1, "Pedro", "Martínez", "56789012E", "Francesa", 3);
		Entrenadores entrenador6 = new Entrenadores(club3, 2, "Laura", "Pérez", "67890123F", "Española", 4);

		Entrenadores entrenador7 = new Entrenadores(club4, 1, "Juan", "Rodríguez", "78901234G", "Peruana", 3);
		Entrenadores entrenador8 = new Entrenadores(club4, 2, "Ana", "Gómez", "89012345H", "Española", 4);

		Entrenadores entrenador9 = new Entrenadores(club5, 1, "Luis", "Torres", "90123456I", "Alemana", 3);
		Entrenadores entrenador10 = new Entrenadores(club5, 2, "Carmen", "Ruiz", "01234567J", "Española", 4);
		
		
		Clubes.listarClubes();
		System.out.println();
		club1.listarJugadoresDeCadaClub();
		club1.listarEntrenadoresDeCadaClub();
		System.out.println();
		club2.listarJugadoresDeCadaClub();
	}

}
