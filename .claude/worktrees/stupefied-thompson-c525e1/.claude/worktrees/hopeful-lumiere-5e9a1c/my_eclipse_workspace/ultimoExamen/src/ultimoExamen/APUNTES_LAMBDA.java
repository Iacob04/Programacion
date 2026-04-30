package ultimoExamen;

// ╔══════════════════════════════════════════════════════════════════════════╗
// ║         APUNTES EXAMEN — FUNCIONES LAMBDA & STREAMS                     ║
// ║     Gabriel Alexandru Iacob | DAM | 3ª Evaluación                       ║
// ║  COPIA-PEGA DIRECTO. Cambia los tipos/nombres según el ejercicio.        ║
// ╚══════════════════════════════════════════════════════════════════════════╝

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class APUNTES_LAMBDA {

    // ═══════════════════════════════════════════════════════════════════
    // 0. QUÉ ES UNA LAMBDA
    // ═══════════════════════════════════════════════════════════════════
    /*
        Sintaxis:  (parámetros) -> expresión
                   (parámetros) -> { bloque de código; }

        - Sustituye a una clase anónima que implemente una @FunctionalInterface
        - Una @FunctionalInterface tiene EXACTAMENTE UN método abstracto
        - Si el cuerpo es una sola expresión, no necesita return ni {}
        - Si tiene un solo parámetro, no necesita paréntesis: x -> x * 2
    */

    // ═══════════════════════════════════════════════════════════════════
    // 1. INTERFACES FUNCIONALES ESTÁNDAR (java.util.function)
    // ═══════════════════════════════════════════════════════════════════

    public static void interfacesFuncionales() {

        // --- Runnable: sin parámetros, sin retorno ---
        Runnable saludo = () -> System.out.println("Hola mundo");
        saludo.run();  // → Hola mundo

        // --- Consumer<T>: recibe T, no devuelve nada ---
        Consumer<String> imprimir = s -> System.out.println(">> " + s);
        imprimir.accept("Hola");  // → >> Hola

        // --- Supplier<T>: sin parámetros, devuelve T ---
        Supplier<String> nombre = () -> "Alexandru";
        System.out.println(nombre.get());  // → Alexandru

        // --- Function<T, R>: recibe T, devuelve R ---
        Function<String, Integer> longitud = s -> s.length();
        System.out.println(longitud.apply("Hola"));  // → 4

        // --- Predicate<T>: recibe T, devuelve boolean ---
        Predicate<Integer> esMayor18 = n -> n >= 18;
        System.out.println(esMayor18.test(21));   // → true
        System.out.println(esMayor18.test(15));   // → false

        // --- Comparator<T>: compara dos T, devuelve int ---
        Comparator<String> porLongitud = (a, b) -> a.length() - b.length();
        // Comparator.comparing es más limpio (ver sección 3)
    }

    // ═══════════════════════════════════════════════════════════════════
    // 2. INTERFACES FUNCIONALES PROPIAS (las que crea el profe)
    // ═══════════════════════════════════════════════════════════════════
    /*
        // En su propio archivo o como inner interface:
        @FunctionalInterface
        interface Operacion {
            int calcular(int a, int b);   // un solo método abstracto
        }

        // Uso:
        Operacion suma  = (a, b) -> a + b;
        Operacion resta = (a, b) -> a - b;
        Operacion multi = (a, b) -> a * b;

        System.out.println(suma.calcular(5, 3));   // → 8
        System.out.println(resta.calcular(10, 4)); // → 6
        System.out.println(multi.calcular(3, 3));  // → 9
    */

    @FunctionalInterface
    interface Operacion {
        int calcular(int a, int b);
    }

    public static void ejemploInterfazPropia() {
        Operacion suma   = (a, b) -> a + b;
        Operacion resta  = (a, b) -> a - b;
        Operacion multi  = (a, b) -> a * b;
        Operacion modulo = (a, b) -> a % b;

        System.out.println("Suma:    " + suma.calcular(10, 3));    // → 13
        System.out.println("Resta:   " + resta.calcular(10, 3));   // → 7
        System.out.println("Multi:   " + multi.calcular(10, 3));   // → 30
        System.out.println("Módulo:  " + modulo.calcular(10, 3));  // → 1

        // Pasar lambda como parámetro de método:
        System.out.println("Via método: " + operar(5, 3, suma));   // → 8
    }

    public static int operar(int a, int b, Operacion op) {
        return op.calcular(a, b);   // ejecuta la lambda que se le pase
    }

    // ═══════════════════════════════════════════════════════════════════
    // 3. ORDENAR CON LAMBDA (ArrayList.sort / List.sort)
    // ═══════════════════════════════════════════════════════════════════
    public static void ordenarConLambda() {
        ArrayList<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Beatriz", "David"));

        // Orden alfabético (natural)
        nombres.sort((a, b) -> a.compareTo(b));
        System.out.println(nombres);  // → [Ana, Beatriz, Carlos, David]

        // Orden inverso
        nombres.sort((a, b) -> b.compareTo(a));
        System.out.println(nombres);  // → [David, Carlos, Beatriz, Ana]

        // Por longitud
        nombres.sort((a, b) -> a.length() - b.length());
        System.out.println(nombres);  // → [Ana, Ana...] (los más cortos primero)

        // --- Comparator.comparing (más limpio) ---
        nombres.sort(Comparator.comparing(String::length));         // por longitud ascendente
        nombres.sort(Comparator.comparing(String::length).reversed()); // descendente
        nombres.sort(Comparator.naturalOrder());                    // alfabético
        nombres.sort(Comparator.reverseOrder());                    // alfabético inverso
    }

    // Ordenar lista de objetos propios (ej: Persona con nombre y edad)
    /*
        ArrayList<Persona> personas = new ArrayList<>();
        // ordenar por nombre:
        personas.sort((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
        // ordenar por edad:
        personas.sort((p1, p2) -> p1.getEdad() - p2.getEdad());
        // ordenar por nombre y desempate por edad:
        personas.sort(Comparator.comparing(Persona::getNombre).thenComparingInt(Persona::getEdad));
    */

    // ═══════════════════════════════════════════════════════════════════
    // 4. FOREACH CON LAMBDA
    // ═══════════════════════════════════════════════════════════════════
    public static void forEachLambda() {
        ArrayList<String> lista = new ArrayList<>(List.of("Rojo", "Verde", "Azul"));

        // forEach clásico con lambda
        lista.forEach(color -> System.out.println(color));

        // Referencia a método (equivalente, más corto)
        lista.forEach(System.out::println);

        // Con índice (necesitas un contador manual o usa IntStream)
        lista.forEach(item -> System.out.println("- " + item.toUpperCase()));
    }

    // ═══════════════════════════════════════════════════════════════════
    // 5. STREAMS — PIPELINE DE OPERACIONES
    // ═══════════════════════════════════════════════════════════════════
    /*
        PIPELINE: lista.stream()
                       .operacionIntermedia1(...)   ← devuelven Stream, se encadenan
                       .operacionIntermedia2(...)
                       .operacionTerminal(...)       ← ejecuta todo y devuelve resultado

        OPERACIONES INTERMEDIAS (lazy, no ejecutan hasta el terminal):
            .filter(predicate)       → filtra elementos
            .map(function)           → transforma cada elemento
            .sorted()                → ordena (natural)
            .sorted(comparator)      → ordena con comparador
            .distinct()              → elimina duplicados
            .limit(n)                → máximo n elementos
            .skip(n)                 → salta los primeros n

        OPERACIONES TERMINALES (ejecutan el pipeline):
            .forEach(consumer)       → recorre, no devuelve nada
            .collect(Collectors.toList())  → devuelve ArrayList
            .count()                 → número de elementos (long)
            .findFirst()             → Optional<T> con el primer elemento
            .anyMatch(predicate)     → true si alguno cumple
            .allMatch(predicate)     → true si todos cumplen
            .noneMatch(predicate)    → true si ninguno cumple
            .min(comparator)         → Optional<T> con el mínimo
            .max(comparator)         → Optional<T> con el máximo
            .mapToInt(...).sum()     → suma de ints
            .mapToInt(...).average() → OptionalDouble con la media
    */

    public static void ejemplosStream() {
        ArrayList<Integer> numeros = new ArrayList<>(List.of(1, 5, 3, 8, 2, 7, 4, 9, 6));

        // --- filter: solo los mayores de 5 ---
        List<Integer> mayores = numeros.stream()
            .filter(n -> n > 5)
            .collect(Collectors.toList());
        System.out.println("Mayores de 5: " + mayores);  // → [8, 7, 9, 6]

        // --- sorted + filter ---
        List<Integer> ordenados = numeros.stream()
            .filter(n -> n % 2 == 0)   // solo pares
            .sorted()                   // orden ascendente
            .collect(Collectors.toList());
        System.out.println("Pares ordenados: " + ordenados);  // → [2, 4, 6, 8]

        // --- map: transforma a String ---
        List<String> cadenas = numeros.stream()
            .map(n -> "Num:" + n)
            .collect(Collectors.toList());
        System.out.println(cadenas);

        // --- count ---
        long cuantos = numeros.stream()
            .filter(n -> n > 5)
            .count();
        System.out.println("Cuántos > 5: " + cuantos);  // → 4

        // --- sum ---
        int suma = numeros.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Suma: " + suma);  // → 45

        // --- max y min ---
        Optional<Integer> maximo = numeros.stream().max(Comparator.naturalOrder());
        maximo.ifPresent(m -> System.out.println("Máximo: " + m));  // → 9

        // --- anyMatch ---
        boolean hayMayorQue10 = numeros.stream().anyMatch(n -> n > 10);
        System.out.println("Hay alguno > 10: " + hayMayorQue10);  // → false
    }

    // ═══════════════════════════════════════════════════════════════════
    // 6. STREAMS CON OBJETOS PROPIOS (más habitual en examen)
    // ═══════════════════════════════════════════════════════════════════
    /*
        // Clase de ejemplo:
        class Alumno {
            String nombre;
            double nota;
            Alumno(String n, double nota) { this.nombre = n; this.nota = nota; }
            public String getNombre() { return nombre; }
            public double getNota()   { return nota; }
        }

        ArrayList<Alumno> alumnos = new ArrayList<>();

        // Filtrar aprobados (nota >= 5):
        List<Alumno> aprobados = alumnos.stream()
            .filter(a -> a.getNota() >= 5.0)
            .collect(Collectors.toList());

        // Obtener solo los nombres:
        List<String> nombres = alumnos.stream()
            .map(Alumno::getNombre)          // referencia a método getter
            .collect(Collectors.toList());

        // Ordenar por nota descendente:
        alumnos.sort(Comparator.comparingDouble(Alumno::getNota).reversed());

        // Media de notas:
        OptionalDouble media = alumnos.stream()
            .mapToDouble(Alumno::getNota)
            .average();
        media.ifPresent(m -> System.out.printf("Media: %.2f%n", m));

        // El alumno con mejor nota:
        Optional<Alumno> mejor = alumnos.stream()
            .max(Comparator.comparingDouble(Alumno::getNota));
        mejor.ifPresent(a -> System.out.println("Mejor: " + a.getNombre()));
    */

    // ═══════════════════════════════════════════════════════════════════
    // 7. REFERENCIAS A MÉTODOS (Method References)
    // ═══════════════════════════════════════════════════════════════════
    /*
        Sintaxis: Clase::método  o  objeto::método

        Tipo                Sintaxis                    Equivalente lambda
        ─────────────────────────────────────────────────────────────────
        Static              Integer::parseInt           s -> Integer.parseInt(s)
        De instancia (obj)  System.out::println         s -> System.out.println(s)
        De instancia (tipo) String::toUpperCase         s -> s.toUpperCase()
        Constructor         Persona::new                () -> new Persona()

        Ejemplos:
        lista.forEach(System.out::println);
        lista.stream().map(String::toUpperCase).collect(Collectors.toList());
        lista.stream().map(Integer::parseInt).collect(Collectors.toList());
    */

    // ═══════════════════════════════════════════════════════════════════
    // 8. LAMBDA EN THREADS (Runnable)
    // ═══════════════════════════════════════════════════════════════════
    public static void lambdaEnThreads() {
        // Sin lambda (clase anónima):
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread sin lambda");
            }
        });

        // Con lambda (mucho más corto):
        Thread t2 = new Thread(() -> System.out.println("Thread CON lambda"));

        t1.start();
        t2.start();
    }

    // ═══════════════════════════════════════════════════════════════════
    // 9. PREDICATE — encadenar condiciones
    // ═══════════════════════════════════════════════════════════════════
    public static void encadenarPredicates() {
        Predicate<Integer> par      = n -> n % 2 == 0;
        Predicate<Integer> mayor5   = n -> n > 5;

        // and, or, negate
        Predicate<Integer> parYmayor5 = par.and(mayor5);
        Predicate<Integer> parOMayor5 = par.or(mayor5);
        Predicate<Integer> noPar      = par.negate();

        ArrayList<Integer> nums = new ArrayList<>(List.of(1, 2, 4, 6, 7, 8, 10));
        nums.stream().filter(parYmayor5).forEach(System.out::println); // → 6 8 10

        // En streams directamente:
        nums.stream()
            .filter(n -> n % 2 == 0 && n > 5)
            .forEach(System.out::println);
    }

    // ═══════════════════════════════════════════════════════════════════
    // 10. EJERCICIO TIPO EXAMEN COMPLETO
    // ═══════════════════════════════════════════════════════════════════
    /*
        Dado un ArrayList de nombres, muestra:
        a) Los que tienen más de 4 letras, en mayúsculas, ordenados
        b) Cuántos empiezan por 'A'
        c) El nombre más largo
    */
    public static void ejercicioCompleto() {
        ArrayList<String> nombres = new ArrayList<>(
            List.of("Ana", "Beatriz", "Carlos", "David", "Elena", "Alejandro", "Alba")
        );

        // a) más de 4 letras, mayúsculas, ordenados
        System.out.println("--- a) ---");
        nombres.stream()
            .filter(n -> n.length() > 4)
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);
        // → ALEJANDRO, BEATRIZ, CARLOS, DAVID, ELENA

        // b) cuántos empiezan por 'A'
        long conA = nombres.stream()
            .filter(n -> n.startsWith("A"))
            .count();
        System.out.println("--- b) Empiezan por A: " + conA);  // → 3 (Ana, Alejandro, Alba)

        // c) el nombre más largo
        Optional<String> masLargo = nombres.stream()
            .max(Comparator.comparingInt(String::length));
        masLargo.ifPresent(n -> System.out.println("--- c) Más largo: " + n)); // → Alejandro
    }

    // ═══════════════════════════════════════════════════════════════════
    // MAIN DE DEMOSTRACIÓN
    // ═══════════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Interfaces funcionales ===");
        interfacesFuncionales();

        System.out.println("\n=== Interfaz propia ===");
        ejemploInterfazPropia();

        System.out.println("\n=== Streams ===");
        ejemplosStream();

        System.out.println("\n=== Ejercicio completo ===");
        ejercicioCompleto();
    }
}
