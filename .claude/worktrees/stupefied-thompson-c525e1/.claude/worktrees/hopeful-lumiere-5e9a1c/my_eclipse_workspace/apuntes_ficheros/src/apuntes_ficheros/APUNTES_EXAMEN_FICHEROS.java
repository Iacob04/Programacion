package apuntes_ficheros;



// ============================================================
//   APUNTES EXAMEN - MANEJO DE FICHEROS EN JAVA
//   Alumno: Gabriel Alexandru Iacob | DAM - FP Grado Superior
// ============================================================

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class APUNTES_EXAMEN_FICHEROS {

    // ============================================================
    // 1. LECTURA DE FICHEROS DE TEXTO
    // ============================================================

    // --- MÉTODO 1: FileReader + BufferedReader (el más común) ---
    public static void leerConBufferedReader(String ruta) {
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- MÉTODO 2: Scanner (útil si necesitas leer por palabras/tokens) ---
    public static void leerConScanner(String ruta) {
        try {
            File fichero = new File(ruta);
            Scanner lector = new Scanner(fichero);
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- MÉTODO 3: Files.readAllLines → devuelve ArrayList<String> ---
    public static void leerConReadAllLines(String ruta) {
        ArrayList<String> lineas = null;
        Path fichero = Path.of(ruta);
        try {
            lineas = (ArrayList<String>) Files.readAllLines(fichero);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (lineas != null) {
            for (String linea : lineas) {
                System.out.println(linea);
            }
        }
    }

    // --- MÉTODO 4: Files.readString → devuelve todo el fichero como String ---
    public static void leerConReadString(String ruta) {
        Path fichero = Path.of(ruta);
        String contenido = null;
        try {
            contenido = Files.readString(fichero);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(contenido);
    }


    // ============================================================
    // 2. ESCRITURA DE FICHEROS DE TEXTO
    // ============================================================

    // --- FileWriter: el parámetro 'true' = APPEND (no sobreescribe) ---
    public static void escribirConFileWriter(String ruta) {
        try (FileWriter pluma = new FileWriter(ruta, true)) {
            pluma.write("Primera línea\n");
            pluma.write("Segunda línea\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- BufferedWriter: más eficiente, tiene .newLine() ---
    public static void escribirConBufferedWriter(String ruta) {
        try (BufferedWriter pluma = new BufferedWriter(new FileWriter(ruta, true))) {
            pluma.write("Hola mundo");
            pluma.newLine();          // salto de línea independiente del SO
            pluma.write("Segunda línea");
            pluma.newLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- PrintWriter: el más potente, tiene printf() y println() ---
    public static void escribirConPrintWriter(String ruta) {
        try (PrintWriter pluma = new PrintWriter(new FileWriter(ruta, true))) {
            pluma.println("Línea con println");
            pluma.printf("Nombre: %s, Edad: %d, Nota: %.2f%n", "Ana", 21, 8.75);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Files.write: escribe una lista de Strings ---
    public static void escribirConFilesWrite(String ruta) {
        Path fichero = Paths.get(ruta);
        ArrayList<String> lineas = new ArrayList<>(List.of("Línea 1", "Línea 2", "Línea 3"));
        try {
            Files.write(fichero, lineas, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Files.writeString: escribe un String directamente ---
    public static void escribirConFilesWriteString(String ruta) {
        Path fichero = Paths.get(ruta);
        String contenido = "Hola mundo\n";
        try {
            Files.writeString(fichero, contenido, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ============================================================
    // 3. PROCESAR FICHERO CSV (split por delimitador)
    // ============================================================
    // Ejemplo: cada línea es  "A33:Aprender Java:1:false"

    public static void procesarCSV(String ruta) {
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(":");   // cambiar ":" por tu separador
                String id       = partes[0];
                String titulo   = partes[1];
                int prioridad   = Integer.parseInt(partes[2]);
                boolean hecho   = partes[3].equals("1"); // o "true", según tu fichero
                System.out.printf("ID: %s | %s | P:%d | Hecho:%b%n", id, titulo, prioridad, hecho);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ============================================================
    // 4. FICHEROS BINARIOS CON DataOutputStream / DataInputStream
    // ============================================================
    // Escribe tipos primitivos en binario → leerlos en el MISMO orden

    public static void escribirBinarioPrimitivos(String ruta) {
        try (DataOutputStream binario = new DataOutputStream(new FileOutputStream(ruta))) {
            binario.writeInt(42);
            binario.writeDouble(3.14);
            binario.writeBoolean(true);
            binario.writeChar('J');
            binario.writeUTF("Hola binario");     // writeUTF para Strings
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void leerBinarioPrimitivos(String ruta) {
        try (DataInputStream binario = new DataInputStream(new FileInputStream(ruta))) {
            System.out.println(binario.readInt());
            System.out.println(binario.readDouble());
            System.out.println(binario.readBoolean());
            System.out.println(binario.readChar());
            System.out.println(binario.readUTF());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ============================================================
    // 5. SERIALIZACIÓN DE OBJETOS (ObjectOutputStream / ObjectInputStream)
    // ============================================================
    // La clase a guardar DEBE implementar Serializable
    // Los atributos 'static' NO se serializan
    // Poner 'transient' en campos que NO quieres guardar

    // --- Grabar un solo objeto ---
    public static void grabarObjeto(Object obj, String ruta) {
        try (ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(ruta))) {
            binario.writeObject(obj);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Leer un solo objeto (necesitas hacer cast) ---
    // MiClase obj = (MiClase) leerObjeto("fichero.bin");
    public static Object leerObjeto(String ruta) {
        Object obj = null;
        try (ObjectInputStream binario = new ObjectInputStream(new FileInputStream(ruta))) {
            obj = binario.readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return obj;
    }

    // --- Grabar una lista entera de objetos ---
    public static void grabarLista(ArrayList<?> lista, String ruta) {
        try (ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(ruta))) {
            binario.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Leer la lista entera ---
    // ArrayList<MiClase> lista = (ArrayList<MiClase>) leerLista("fichero.bin");
    public static ArrayList<?> leerLista(String ruta) {
        ArrayList<?> lista = null;
        try (ObjectInputStream binario = new ObjectInputStream(new FileInputStream(ruta))) {
            lista = (ArrayList<?>) binario.readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    // PATRÓN COMPLETO: leer → modificar → volver a grabar
    /*
        ArrayList<MiClase> lista = (ArrayList<MiClase>) leerLista("datos.bin");
        lista.add(nuevoObjeto);
        grabarLista(lista, "datos.bin");   // sobreescribe con la lista actualizada
    */


    // ============================================================
    // 6. FICHERO DE ACCESO ALEATORIO (RandomAccessFile)
    // ============================================================
    // Cada registro tiene tamaño FIJO → puedes ir directamente a cualquier posición
    // Los chars en Java ocupan 2 bytes → nombre de 20 chars = 40 bytes
    // int ocupa 4 bytes

    static final int TAM_NOMBRE   = 20;                       // chars
    static final int TAM_REGISTRO = (TAM_NOMBRE * 2) + 4;    // bytes: chars*2 + int

    // --- Escribir un nombre de longitud fija (rellena con espacios) ---
    public static void escribirNombre(RandomAccessFile raf, String nombre) throws Exception {
        char[] chars = new char[TAM_NOMBRE];
        for (int i = 0; i < TAM_NOMBRE; i++) {
            chars[i] = (i < nombre.length()) ? nombre.charAt(i) : ' ';
        }
        for (char c : chars) {
            raf.writeChar(c);
        }
    }

    // --- Leer un nombre de longitud fija ---
    public static String leerNombre(RandomAccessFile raf) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAM_NOMBRE; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();   // .trim() quita los espacios de relleno
    }

    // --- Calcular posición del registro N (registros empiezan en 1) ---
    // long posicion = TAM_REGISTRO * (numeroRegistro - 1);

    // --- Leer un registro concreto ---
    public static void leerRegistro(String ruta, int numero) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long posicion = TAM_REGISTRO * (numero - 1);
            if (posicion >= raf.length()) {
                System.out.println("Registro " + numero + " no existe");
                return;
            }
            raf.seek(posicion);           // mover cabezal a la posición
            String nombre = leerNombre(raf);
            int edad      = raf.readInt();
            if (nombre.charAt(0) != '*') {  // '*' = marcado como borrado
                System.out.printf("Registro %d: %s, %d años%n", numero, nombre, edad);
            } else {
                System.out.println("Registro " + numero + " está borrado");
            }
        }
    }

    // --- Escribir un nuevo registro al final ---
    public static void nuevoRegistro(String ruta, String nombre, int edad) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.seek(raf.length());       // ir al final del fichero
            escribirNombre(raf, nombre);
            raf.writeInt(edad);
        }
    }

    // --- Modificar un registro existente ---
    public static void modificarRegistro(String ruta, int numero, String nombre, int edad) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            long posicion = TAM_REGISTRO * (numero - 1);
            if (posicion >= raf.length()) {
                System.out.println("Registro no existe");
                return;
            }
            raf.seek(posicion);
            String nombreActual = leerNombre(raf);
            if (nombreActual.charAt(0) == '*') {
                System.out.println("El registro está borrado, no se puede modificar");
                return;
            }
            raf.seek(posicion);           // volver a la posición para sobreescribir
            escribirNombre(raf, nombre);
            raf.writeInt(edad);
        }
    }

    // --- Borrado lógico: marca el nombre con '*' (NO borra físicamente) ---
    public static void borrarRegistro(String ruta, int numero) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            long posicion = TAM_REGISTRO * (numero - 1);
            if (posicion >= raf.length()) {
                System.out.println("Registro no existe");
                return;
            }
            raf.seek(posicion);
            String nombre = leerNombre(raf);
            if (nombre.charAt(0) == '*') {
                System.out.println("Ya estaba borrado");
                return;
            }
            String nombreBorrado = "*" + nombre.substring(1); // sustituir primer char
            raf.seek(posicion);
            escribirNombre(raf, nombreBorrado);
            System.out.println("Registro " + numero + " borrado");
        }
    }

    // --- Leer todos los registros ---
    public static void leerTodo(String ruta) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            int num = 1;
            while (raf.getFilePointer() < raf.length()) {
                String nombre = leerNombre(raf);
                int edad      = raf.readInt();
                if (nombre.charAt(0) != '*') {
                    System.out.printf("Registro %d: %s, %d años%n", num, nombre, edad);
                }
                num++;
            }
        }
    }


    // ============================================================
    // 7. COMPROBAR SI UN FICHERO EXISTE
    // ============================================================

    public static boolean existeFichero(String ruta) {
        File f = new File(ruta);
        return f.isFile();   // devuelve true si existe y es un fichero
    }


    // ============================================================
    // 8. HASHMAP + FICHERO (leer/escribir diccionarios clave:valor)
    // ============================================================

    // Formato del fichero:  usuario:contraseña  (una por línea)
    public static HashMap<String, String> leerHashMapDesdeFichero(String ruta) {
        HashMap<String, String> mapa = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                int pos = linea.indexOf(":");
                mapa.put(linea.substring(0, pos), linea.substring(pos + 1));
            }
        } catch (Exception e) {
            System.out.println("Fichero no encontrado o error de lectura");
        }
        return mapa;
    }

    public static void grabarHashMapEnFichero(String clave, String valor, String ruta) {
        try (PrintWriter pluma = new PrintWriter(new FileWriter(ruta, true))) {
            pluma.printf("%s:%s%n", clave, valor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ============================================================
    // 9. ALGORITMO DE BURBUJA (ordenar ArrayList por un campo int)
    // ============================================================
    // Adapta MiClase y .campo a tu ejercicio

    /*
    public static void ordenarPorBurbuja(ArrayList<MiClase> lista) {
        boolean hayCambios = true;
        while (hayCambios) {
            hayCambios = false;
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i).campo > lista.get(i + 1).campo) {  // cambiar '>' por '<' para orden descendente
                    MiClase temp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, temp);
                    hayCambios = true;
                }
            }
        }
    }
    */


    // ============================================================
    // 10. PLANTILLA CLASE SERIALIZABLE
    // ============================================================

    /*
    import java.io.Serializable;
    import java.util.ArrayList;

    public class MiClase implements Serializable {

        // 'static' y 'transient' NO se serializan
        private String nombre;
        private int valor;
        private transient String campoNoGuardado;  // transient = no se guarda
        private static ArrayList<MiClase> lista = new ArrayList<>();  // static = no se guarda

        public MiClase(String nombre, int valor) {
            this.nombre = nombre;
            this.valor  = valor;
            MiClase.lista.add(this);   // auto-registro en la lista estática
        }

        public void mostrar() {
            System.out.printf("%s -> %d%n", nombre, valor);
        }

        public static void mostrarTodos() {
            for (MiClase obj : lista) {
                obj.mostrar();
            }
        }
    }
    */


    // ============================================================
    // MAIN DE EJEMPLO - borra lo que no necesites
    // ============================================================

    public static void main(String[] args) {

        String txt    = "datos.txt";
        String bin    = "datos.bin";
        String dat    = "agenda.dat";

        // --- Texto ---
        leerConBufferedReader(txt);
        escribirConPrintWriter(txt);
        procesarCSV(txt);

        // --- Binario primitivos ---
        escribirBinarioPrimitivos(bin);
        leerBinarioPrimitivos(bin);

        // --- Objetos serializados ---
        // grabarObjeto(miObjeto, bin);
        // MiClase obj = (MiClase) leerObjeto(bin);

        // --- Acceso aleatorio ---
        try {
            nuevoRegistro(dat, "Alexandru", 19);
            leerTodo(dat);
            leerRegistro(dat, 1);
            modificarRegistro(dat, 1, "Alex", 20);
            borrarRegistro(dat, 1);
        } catch (Exception e) {
            System.out.println("Error RAF: " + e.getMessage());
        }

        // --- HashMap desde fichero ---
        HashMap<String, String> mapa = leerHashMapDesdeFichero("login.txt");
        System.out.println(mapa);
    }
}
