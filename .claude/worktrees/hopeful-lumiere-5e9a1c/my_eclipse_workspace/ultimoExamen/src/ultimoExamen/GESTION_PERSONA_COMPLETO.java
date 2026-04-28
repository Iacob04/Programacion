package ultimoExamen;

// ╔══════════════════════════════════════════════════════════════════════════╗
// ║   EJERCICIO COMPLETO — GESTIÓN DE PERSONA (3ª Evaluación)               ║
// ║   Sistema médico completo: Persona → Médico/Paciente → CentroMédico     ║
// ║   Gabriel Alexandru Iacob | DAM                                          ║
// ║   TOTALMENTE EJECUTABLE. Copia y adapta nombres al enunciado.            ║
// ╚══════════════════════════════════════════════════════════════════════════╝

import java.time.LocalDate;
import java.util.ArrayList;

// ═══════════════════════════════════════════════════════════════════════════
// ► FICHERO ÚNICO con todas las clases para fácil lectura.
//   En el examen las separarás en archivos individuales.
// ═══════════════════════════════════════════════════════════════════════════


// ─────────────────────────────────────────────────────────────────────────
// CLASE ABSTRACTA PERSONA — base de toda la jerarquía
// ─────────────────────────────────────────────────────────────────────────
abstract class Persona {

    // Campos protegidos: visibles en Medico y Paciente
    protected String nombre;
    protected String apellidos;
    protected CentroMedico centro;

    // Lista de consultas que tiene esta persona (médico o paciente)
    protected ArrayList<Consulta> consultas = new ArrayList<>();

    // Constructor base
    public Persona(String nombre, String apellidos, CentroMedico centro) {
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.centro    = centro;
    }

    // Método abstracto: cada subclase implementa cómo cambiar de centro
    public abstract void cambioCentro(CentroMedico nuevoCentro);

    // Añade una consulta a la lista de esta persona
    public void anyadirConsulta(Consulta c) {
        this.consultas.add(c);
    }

    // Lista todas las consultas de esta persona
    public void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println(getNombreCompleto() + " no tiene consultas.");
            return;
        }
        System.out.println("Consultas de " + getNombreCompleto() + ":");
        for (Consulta c : consultas) {
            c.mostrar();
        }
    }

    // Getters
    public String getNombre()    { return nombre; }
    public String getApellidos() { return apellidos; }
    public CentroMedico getCentro() { return centro; }

    // Nombre completo = nombre + apellidos
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidos;
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }
}


// ─────────────────────────────────────────────────────────────────────────
// CLASE MÉDICO — extiende Persona
// ─────────────────────────────────────────────────────────────────────────
class Medico extends Persona {

    private String especialidad;
    private String numColegiado;
    private ArrayList<Paciente> pacientes = new ArrayList<>();

    public Medico(String nombre, String apellidos, String especialidad,
                  String numColegiado, CentroMedico centro) {
        super(nombre, apellidos, centro);     // llama al constructor de Persona
        this.especialidad  = especialidad;
        this.numColegiado  = numColegiado;
        this.centro.anyadirMedico(this);      // se añade solo al centro al crearse
    }

    @Override
    public void cambioCentro(CentroMedico nuevoCentro) {
        this.centro.eliminarMedico(this);     // se borra del centro anterior
        this.centro = nuevoCentro;
        this.centro.anyadirMedico(this);      // se añade al nuevo
    }

    // Añade un paciente a la lista de este médico
    public void anyadirPaciente(Paciente p) {
        this.pacientes.add(p);
    }

    // Lista todos sus pacientes
    public void listarPacientes() {
        System.out.println("Pacientes del Dr. " + getNombreCompleto() + ":");
        for (Paciente p : pacientes) {
            p.mostrarResumen();
        }
    }

    public String getEspecialidad() { return especialidad; }
    public String getNumColegiado() { return numColegiado; }

    @Override
    public String toString() {
        return "Dr. " + getNombreCompleto() + " (" + especialidad + ")";
    }
}


// ─────────────────────────────────────────────────────────────────────────
// CLASE PACIENTE — extiende Persona
// ─────────────────────────────────────────────────────────────────────────
class Paciente extends Persona {

    private String DNI;
    private String telefono;

    public Paciente(String nombre, String apellidos, String DNI,
                    String telefono, CentroMedico centro) {
        super(nombre, apellidos, centro);
        this.DNI      = DNI;
        this.telefono = telefono;
        this.centro.anyadirPaciente(this);    // se añade solo al centro al crearse
    }

    @Override
    public void cambioCentro(CentroMedico nuevoCentro) {
        this.centro.eliminarPaciente(this);
        this.centro = nuevoCentro;
        this.centro.anyadirPaciente(this);
    }

    // Muestra datos del paciente
    public void mostrarResumen() {
        System.out.printf("  Paciente: %s | DNI: %s | Tel: %s%n",
            getNombreCompleto(), DNI, telefono);
    }

    public String getDNI()      { return DNI; }
    public String getTelefono() { return telefono; }
}


// ─────────────────────────────────────────────────────────────────────────
// CLASE CONSULTA — une Médico + Paciente + datos clínicos
// ─────────────────────────────────────────────────────────────────────────
class Consulta {

    private Medico   medico;
    private Paciente paciente;
    private LocalDate fecha;
    private String   sintomas;
    private String   diagnostico;

    public Consulta(Paciente paciente, Medico medico,
                    LocalDate fecha, String sintomas, String diagnostico) {
        this.paciente    = paciente;
        this.medico      = medico;
        this.fecha       = fecha;
        this.sintomas    = sintomas;
        this.diagnostico = diagnostico;

        // Al crear la consulta se registra en: el centro, el médico y el paciente
        medico.getCentro().anyadirConsulta(this);
        paciente.anyadirConsulta(this);
        medico.anyadirConsulta(this);
    }

    public void mostrar() {
        System.out.println("  (" + fecha + ") Dr: " + medico.getApellidos() +
                           " | Paciente: " + paciente.getNombreCompleto());
        System.out.println("  Síntomas: " + sintomas);
        System.out.println("  Diagnóstico: " + diagnostico);
        System.out.println("  ─────────────────────────────────────");
    }

    public Medico getMedico()     { return medico; }
    public Paciente getPaciente() { return paciente; }
    public LocalDate getFecha()   { return fecha; }
}


// ─────────────────────────────────────────────────────────────────────────
// CLASE CENTROMEDICO — gestora principal (tiene las listas)
// ─────────────────────────────────────────────────────────────────────────
class CentroMedico {

    private String nombre;
    private String codigo;

    private ArrayList<Medico>   medicos   = new ArrayList<>();
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public CentroMedico(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    // ── MÉDICOS ──────────────────────────────────────────────────────
    public void anyadirMedico(Medico m) {
        medicos.add(m);
    }

    public void eliminarMedico(Medico m) {
        medicos.remove(m);
    }

    public void listarMedicos() {
        System.out.println("Médicos de " + nombre + ":");
        for (Medico m : medicos) {
            System.out.println("  " + m);
        }
    }

    // ── PACIENTES ────────────────────────────────────────────────────
    public void anyadirPaciente(Paciente p) {
        pacientes.add(p);
    }

    public void eliminarPaciente(Paciente p) {
        pacientes.remove(p);
    }

    public void listarPacientes() {
        System.out.println("Pacientes de " + nombre + ":");
        for (Paciente p : pacientes) {
            p.mostrarResumen();
        }
    }

    // ── CONSULTAS ────────────────────────────────────────────────────
    public void anyadirConsulta(Consulta c) {
        consultas.add(c);
    }

    public void listarConsultas() {
        System.out.println("Consultas de " + nombre + ":");
        for (Consulta c : consultas) {
            c.mostrar();
        }
    }

    // ── BUSCAR ───────────────────────────────────────────────────────
    public Medico buscarMedicoPorApellido(String apellido) {
        for (Medico m : medicos) {
            if (m.getApellidos().equalsIgnoreCase(apellido)) {
                return m;
            }
        }
        return null; // no encontrado
    }

    public Paciente buscarPacientePorDNI(String dni) {
        for (Paciente p : pacientes) {
            if (p.getDNI().equalsIgnoreCase(dni)) {
                return p;
            }
        }
        return null;
    }

    // ── STATS ────────────────────────────────────────────────────────
    public int totalMedicos()   { return medicos.size(); }
    public int totalPacientes() { return pacientes.size(); }
    public int totalConsultas() { return consultas.size(); }

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
}


// ─────────────────────────────────────────────────────────────────────────
// MAIN — todo junto funcionando
// ─────────────────────────────────────────────────────────────────────────
class MainGestionPersona {

    public static void main(String[] args) {

        // ── 1. Crear centros médicos ───────────────────────────────────
        CentroMedico centro1 = new CentroMedico("La Elipa", "CM-001");
        CentroMedico centro2 = new CentroMedico("Ciudad de los Ángeles", "CM-002");

        // ── 2. Crear médicos (se añaden solos al centro en el constructor) ─
        Medico medico1 = new Medico("Alexandru", "Iacob",     "Neurocirugía", "COL-001", centro1);
        Medico medico2 = new Medico("Daniel",    "Segura",    "Urología",     "COL-002", centro1);
        Medico medico3 = new Medico("Sara",      "García",    "Podología",    "COL-003", centro2);
        Medico medico4 = new Medico("Sergio",    "Serrano",   "Ginecología",  "COL-004", centro2);

        // ── 3. Crear pacientes (se añaden solos al centro) ─────────────
        Paciente paciente1 = new Paciente("Mario",  "Carcalete", "12345678A", "666111222", centro1);
        Paciente paciente2 = new Paciente("Marcos", "Piñeros",   "87654321B", "666333444", centro2);
        Paciente paciente3 = new Paciente("Laura",  "Rodríguez", "11223344C", "666555666", centro1);

        // ── 4. Crear consultas (se registran en medico, paciente y centro) ─
        Consulta c1 = new Consulta(paciente1, medico2, LocalDate.now(),
                                   "Le duele la espalda",    "Reposo y paracetamol");
        Consulta c2 = new Consulta(paciente2, medico1, LocalDate.now(),
                                   "Migraña frecuente",      "Resonancia magnética");
        Consulta c3 = new Consulta(paciente3, medico2, LocalDate.of(2026, 4, 10),
                                   "Infección urinaria",     "Antibióticos 7 días");

        // ── 5. Mostrar información ──────────────────────────────────────
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     CENTRO 1: " + centro1.getNombre());
        System.out.println("╚══════════════════════════════╝");
        centro1.listarMedicos();
        System.out.println();
        centro1.listarPacientes();
        System.out.println();
        centro1.listarConsultas();

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     CENTRO 2: " + centro2.getNombre());
        System.out.println("╚══════════════════════════════╝");
        centro2.listarMedicos();
        System.out.println();

        System.out.println("── Consultas del médico Segura ──");
        medico2.listarConsultas();

        System.out.println("── Consultas del paciente Carcalete ──");
        paciente1.listarConsultas();

        // ── 6. Cambio de centro ─────────────────────────────────────────
        System.out.println("\n>> Antes del cambio: paciente2 está en " + paciente2.getCentro().getNombre());
        paciente2.cambioCentro(centro1);
        System.out.println(">> Después del cambio: paciente2 está en " + paciente2.getCentro().getNombre());
        System.out.println(">> Pacientes en centro1 ahora: " + centro1.totalPacientes());

        // ── 7. Buscar ───────────────────────────────────────────────────
        Medico encontrado = centro1.buscarMedicoPorApellido("Segura");
        if (encontrado != null) {
            System.out.println("\nBúsqueda: encontrado → " + encontrado);
        }

        Paciente pEncontrado = centro1.buscarPacientePorDNI("12345678A");
        if (pEncontrado != null) {
            System.out.println("Paciente encontrado: " + pEncontrado.getNombreCompleto());
        }

        // ── 8. Estadísticas ─────────────────────────────────────────────
        System.out.println("\n── Stats Centro1 ──");
        System.out.println("Médicos:   " + centro1.totalMedicos());
        System.out.println("Pacientes: " + centro1.totalPacientes());
        System.out.println("Consultas: " + centro1.totalConsultas());
    }
}
