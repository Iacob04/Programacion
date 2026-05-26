package ultimoExamen;

// ╔══════════════════════════════════════════════════════════════════════════╗
// ║     APUNTES EXAMEN — ORIENTACIÓN A OBJETOS (FINAL DE CURSO)             ║
// ║     Gabriel Alexandru Iacob | DAM | 3ª Evaluación                       ║
// ║  COPIA-PEGA DIRECTO. Renombra clases/atributos según el enunciado.      ║
// ╚══════════════════════════════════════════════════════════════════════════╝

import java.util.ArrayList;

// ═══════════════════════════════════════════════════════════════════════════
// PATRÓN COMPLETO DEL EXAMEN:
//   1. Clase Padre abstracta  (con campos protected y métodos comunes)
//   2. Clases Hija            (extends Padre, añaden sus campos)
//   3. Clase Gestora/Manager  (tiene ArrayList de hijos, métodos CRUD)
//   4. Main                   (crea objetos y llama métodos)
// ═══════════════════════════════════════════════════════════════════════════

public class APUNTES_OOP_FINAL_CURSO {

    // ════════════════════════════════════════════════════════════════════
    // PARTE 1 — CLASE PADRE ABSTRACTA
    // ════════════════════════════════════════════════════════════════════
    /*
        ¿Cuándo usar abstract?
          - La clase padre NO se instancia directamente
          - Tiene al menos un método abstracto (obligatorio en hijas)
          - Los atributos comunes van aquí con 'protected' (visibles en hijas)

        ¿Cuándo NO es abstract?
          - Si el padre sí se puede instanciar solo (menos habitual en examen)
    */

    // ─── Plantilla clase abstracta ───────────────────────────────────────
    // abstract class Padre {
    //     protected String nombre;       // protected = visible en hijas
    //     protected String apellidos;
    //     protected int edad;
    //     protected static int contador = 0;  // contador estático compartido
    //
    //     public Padre(String nombre, String apellidos) {
    //         this.nombre    = nombre;
    //         this.apellidos = apellidos;
    //         Padre.contador++;    // cada vez que se crea uno, suma 1
    //     }
    //
    //     // Método abstracto: las hijas DEBEN implementarlo
    //     public abstract void mostrar();
    //
    //     // Método concreto: se hereda tal cual (hijas pueden sobreescribirlo)
    //     public String getNombreCompleto() {
    //         return this.nombre + " " + this.apellidos;
    //     }
    //
    //     // Getters estándar
    //     public String getNombre()    { return this.nombre; }
    //     public String getApellidos() { return this.apellidos; }
    //     public static int getContador() { return contador; }
    // }

    // ════════════════════════════════════════════════════════════════════
    // PARTE 2 — CLASE HIJA CON EXTENDS
    // ════════════════════════════════════════════════════════════════════
    /*
        class Hija extends Padre {
            private String campoPropio;   // atributo exclusivo de Hija

            // super() debe ser la PRIMERA línea del constructor
            public Hija(String nombre, String apellidos, String campoPropio) {
                super(nombre, apellidos);         // llama al constructor del padre
                this.campoPropio = campoPropio;
            }

            // Implementar el método abstracto del padre (obligatorio si Padre es abstract)
            @Override
            public void mostrar() {
                System.out.println(getNombreCompleto() + " - " + this.campoPropio);
            }

            // Sobreescribir un método concreto del padre (opcional)
            @Override
            public String getNombreCompleto() {
                return super.getNombreCompleto() + " (" + campoPropio + ")";
            }

            public String getCampoPropio() { return campoPropio; }
        }
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 3 — CLASE GESTORA (la que tiene ArrayList)
    // ════════════════════════════════════════════════════════════════════
    /*
        class Gestora {
            private String nombre;
            private ArrayList<Hija> lista = new ArrayList<>();

            public Gestora(String nombre) {
                this.nombre = nombre;
            }

            // --- AÑADIR ---
            public void anyadir(Hija h) {
                this.lista.add(h);
            }

            // --- ELIMINAR ---
            public void eliminar(Hija h) {
                this.lista.remove(h);    // usa .equals() o la referencia al mismo objeto
            }

            // --- LISTAR TODOS ---
            public void listar() {
                for (Hija h : lista) {
                    h.mostrar();
                }
            }

            // --- BUSCAR POR NOMBRE ---
            public Hija buscarPorNombre(String nombre) {
                for (Hija h : lista) {
                    if (h.getNombre().equalsIgnoreCase(nombre)) {
                        return h;
                    }
                }
                return null;  // no encontrado
            }

            // --- CONTAR ---
            public int total() {
                return lista.size();
            }

            public String getNombre() { return nombre; }
        }
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 4 — RELACIONES ENTRE CLASES
    // ════════════════════════════════════════════════════════════════════
    /*
        ASOCIACIÓN: un objeto TIENE una referencia a otro
            class Medico { private Centro_medico centro; }

        COMPOSICIÓN: el objeto CREA sus dependencias (si muere él, mueren ellas)
            class Grupo { private ArrayList<Alumno> alumnos = new ArrayList<>(); }

        HERENCIA: IS-A (es un)
            class Alumno extends Persona {}   // Alumno ES UNA Persona

        CLAVE: cuándo añadir en constructor vs. método aparte
            - Si la relación es permanente desde la creación → en el constructor
            - Si puede cambiar después → con método setXxx() o anyadir()

        PATRÓN TÍPICO DEL EXAMEN:
            Al crear un objeto hijo, se añade automáticamente al padre/gestor:
            class Alumno extends Persona {
                public Alumno(String nombre, Grupo grupo) {
                    super(nombre);
                    grupo.anyadirAlumno(this);   // ← se añade solo al crearse
                }
            }
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 5 — POLIMORFISMO
    // ════════════════════════════════════════════════════════════════════
    /*
        // Lista de tipo Padre que guarda hijos mezclados:
        ArrayList<Padre> todos = new ArrayList<>();
        todos.add(new Hija1(...));
        todos.add(new Hija2(...));

        for (Padre p : todos) {
            p.mostrar();   // cada uno ejecuta SU versión de mostrar()
        }

        // Comprobar tipo con instanceof:
        for (Padre p : todos) {
            if (p instanceof Hija1) {
                Hija1 h = (Hija1) p;    // cast necesario para métodos de Hija1
                h.metodoEspecifico();
            }
        }
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 6 — ARRAYLIST: MÉTODOS CLAVE
    // ════════════════════════════════════════════════════════════════════
    /*
        ArrayList<Tipo> lista = new ArrayList<>();

        lista.add(objeto)              → añade al final
        lista.add(0, objeto)           → añade en posición 0
        lista.remove(objeto)           → elimina por referencia/equals
        lista.remove(0)                → elimina por índice
        lista.get(0)                   → obtiene por índice
        lista.set(0, nuevoObjeto)      → sustituye en índice 0
        lista.size()                   → número de elementos
        lista.isEmpty()                → true si está vacía
        lista.contains(objeto)         → true si contiene el objeto
        lista.indexOf(objeto)          → índice del objeto (-1 si no existe)
        lista.clear()                  → vacía la lista

        // Iterar con for-each (el más usado):
        for (Tipo t : lista) { ... }

        // Iterar con índice (cuando necesitas el índice):
        for (int i = 0; i < lista.size(); i++) {
            Tipo t = lista.get(i);
        }

        // Iterar y eliminar a la vez → usa Iterator para evitar ConcurrentModificationException:
        Iterator<Tipo> it = lista.iterator();
        while (it.hasNext()) {
            Tipo t = it.next();
            if (condicion) {
                it.remove();
            }
        }
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 7 — STATIC VS. INSTANCE
    // ════════════════════════════════════════════════════════════════════
    /*
        static:
          - Pertenece a la CLASE, no a una instancia concreta
          - Se accede con NombreClase.campo o NombreClase.metodo()
          - Existe aunque no hayas creado ningún objeto
          - Útil para: contadores, listas compartidas, constantes (final static)

        instance (sin static):
          - Pertenece a CADA OBJETO
          - Se accede con objeto.campo o objeto.metodo()

        Ejemplo de contador estático:
            class Persona {
                private static int contador = 0;
                public Persona(String nombre) {
                    Persona.contador++;
                }
                public static int getContador() { return contador; }
            }
            // Persona.getContador() → 0
            new Persona("Ana");   new Persona("Carlos");
            // Persona.getContador() → 2
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 8 — CONSTRUCTORES CON SUPER
    // ════════════════════════════════════════════════════════════════════
    /*
        REGLAS:
        1. Si la hija no llama a super(), Java llama al super() sin parámetros automáticamente.
           Si el padre no tiene constructor sin parámetros → ERROR de compilación.
        2. super() debe ser SIEMPRE la primera instrucción del constructor hijo.
        3. this() llama a otro constructor de la misma clase (también primera instrucción).

        Ejemplo con dos constructores en padre:
            abstract class Persona {
                protected String nombre;
                protected int edad;

                public Persona(String nombre, int edad) {
                    this.nombre = nombre;
                    this.edad   = edad;
                }
                public Persona(String nombre) {
                    this(nombre, 0);    // llama al constructor con dos parámetros
                }
            }

            class Alumno extends Persona {
                private String ciclo;
                public Alumno(String nombre, int edad, String ciclo) {
                    super(nombre, edad);   // llama al constructor de Persona con 2 params
                    this.ciclo = ciclo;
                }
            }
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 9 — INTERFACES (alternativa a clase abstracta)
    // ════════════════════════════════════════════════════════════════════
    /*
        interface Describible {
            void describir();               // abstracto por defecto
            default void saludar() {        // con implementación por defecto
                System.out.println("Hola");
            }
        }

        class Producto implements Describible {
            @Override
            public void describir() {
                System.out.println("Soy un producto");
            }
        }

        Diferencias abstract vs interface:
        - abstract: puede tener constructores, campos con valor
        - interface: no puede tener constructores, todos los campos son public static final
        - Una clase puede implementar VARIAS interfaces pero solo extends UNA clase
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 10 — TOSTRING Y EQUALS (override útil)
    // ════════════════════════════════════════════════════════════════════
    /*
        @Override
        public String toString() {
            return "Persona[nombre=" + nombre + ", edad=" + edad + "]";
        }
        // Ahora System.out.println(persona) imprime la info automáticamente

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Persona)) return false;
            Persona otra = (Persona) obj;
            return this.nombre.equals(otra.nombre);  // compara por nombre
        }
        // Con equals sobreescrito, lista.contains(p) y lista.remove(p) funcionan bien
    */

    // ════════════════════════════════════════════════════════════════════
    // PARTE 11 — RESUMEN PATRÓN EXAMEN (estructura típica)
    // ════════════════════════════════════════════════════════════════════
    /*
        Clases del ejercicio (ej: Hospital):
            Persona (abstract) ← campos: nombre, apellidos, DNI
                Medico   extends Persona ← +especialidad, +ArrayList<Paciente>
                Paciente extends Persona ← +DNI, +telefono
            Centro_medico          ← ArrayList<Medico>, ArrayList<Paciente>
            Consulta               ← ref a Medico, ref a Paciente, fecha, síntomas

        Flujo en Main:
            1. Crear gestores/centros
            2. Crear médicos y pacientes (se añaden solos al centro en constructor)
            3. Crear consultas (que vinculan médico + paciente)
            4. Listar, buscar, modificar
    */

    // ════════════════════════════════════════════════════════════════════
    // MAIN — ejemplo rápido de todo funcionando
    // ════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {

        // Solo es un archivo de referencia; el código ejecutable está en
        // GESTION_PERSONA_COMPLETO.java y EJERCICIOS_RESUELTOS_BBDD.java

        System.out.println("Este archivo son APUNTES. Abre GESTION_PERSONA_COMPLETO.java");
        System.out.println("para ver el ejercicio completo ejecutable.");

        // --- Demo rápida de ArrayList ---
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Ana");
        lista.add("Bruno");
        lista.add("Carlos");
        lista.remove("Bruno");
        System.out.println("Lista: " + lista);           // → [Ana, Carlos]
        System.out.println("Tamaño: " + lista.size());   // → 2
        System.out.println("Contiene Ana: " + lista.contains("Ana")); // → true
    }
}
