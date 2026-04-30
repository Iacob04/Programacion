#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generador de PDF - Guia Completa Examen Final JAVA
Gabriel Alexandru Iacob | DAM | 3a Evaluacion
"""

import os
import html as html_mod
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle
from reportlab.lib.units import cm
from reportlab.lib import colors
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, PageBreak,
    Preformatted, HRFlowable, Table, TableStyle, KeepTogether
)
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont

# =============================================================================
# FUENTES
# =============================================================================
FONTS_DIR = r"C:\Windows\Fonts"

def try_register(name, filename):
    try:
        pdfmetrics.registerFont(TTFont(name, os.path.join(FONTS_DIR, filename)))
        return True
    except Exception:
        return False

ok_consolas = (try_register('Consolas',      'consola.ttf') and
               try_register('Consolas-Bold',  'consolab.ttf'))
ok_arial    = (try_register('Arial',          'arial.ttf') and
               try_register('Arial-Bold',     'arialbd.ttf') and
               try_register('Arial-Italic',   'ariali.ttf'))

CODE_FONT  = 'Consolas'     if ok_consolas else 'Courier'
CODE_BOLD  = 'Consolas-Bold' if ok_consolas else 'Courier-Bold'
TEXT_FONT  = 'Arial'         if ok_arial    else 'Helvetica'
TEXT_BOLD  = 'Arial-Bold'    if ok_arial    else 'Helvetica-Bold'
TEXT_ITAL  = 'Arial-Italic'  if ok_arial    else 'Helvetica-Oblique'

# =============================================================================
# COLORES
# =============================================================================
C_DARK_BLUE  = colors.HexColor('#0d47a1')
C_BLUE       = colors.HexColor('#1565c0')
C_LIGHT_BLUE = colors.HexColor('#1976d2')
C_ACCENT     = colors.HexColor('#0288d1')
C_CODE_BG    = colors.HexColor('#f8f9fa')
C_CODE_BORD  = colors.HexColor('#bbdefb')
C_TIP_BG     = colors.HexColor('#e8f5e9')
C_WARN_BG    = colors.HexColor('#fff3e0')
C_ERR_BG     = colors.HexColor('#fce4ec')
C_HEAD_BG    = colors.HexColor('#1565c0')
C_GRAY       = colors.HexColor('#546e7a')
C_DARK       = colors.HexColor('#212121')
C_EXER_BG   = colors.HexColor('#e3f2fd')

# =============================================================================
# ESTILOS
# =============================================================================
def mk_styles():
    S = {}

    # ── Portada ──────────────────────────────────────────────────────────────
    S['cov_title'] = ParagraphStyle('cov_title',
        fontName=TEXT_BOLD, fontSize=30, textColor=C_DARK_BLUE,
        spaceAfter=8, alignment=1, leading=36)

    S['cov_sub'] = ParagraphStyle('cov_sub',
        fontName=TEXT_FONT, fontSize=15, textColor=C_BLUE,
        spaceAfter=6, alignment=1, leading=20)

    S['cov_info'] = ParagraphStyle('cov_info',
        fontName=TEXT_FONT, fontSize=11, textColor=C_GRAY,
        spaceAfter=4, alignment=1, leading=16)

    # ── Indice ───────────────────────────────────────────────────────────────
    S['toc_title'] = ParagraphStyle('toc_title',
        fontName=TEXT_BOLD, fontSize=18, textColor=C_DARK_BLUE,
        spaceAfter=14, alignment=1)

    S['toc_h1'] = ParagraphStyle('toc_h1',
        fontName=TEXT_BOLD, fontSize=12, textColor=C_DARK,
        spaceAfter=4, leading=18, leftIndent=0)

    S['toc_h2'] = ParagraphStyle('toc_h2',
        fontName=TEXT_FONT, fontSize=10, textColor=C_GRAY,
        spaceAfter=2, leading=14, leftIndent=24)

    # ── Secciones ────────────────────────────────────────────────────────────
    S['h1'] = ParagraphStyle('h1',
        fontName=TEXT_BOLD, fontSize=20, textColor=C_DARK_BLUE,
        spaceBefore=16, spaceAfter=8, leading=26,
        borderPad=6)

    S['h2'] = ParagraphStyle('h2',
        fontName=TEXT_BOLD, fontSize=14, textColor=C_BLUE,
        spaceBefore=12, spaceAfter=6, leading=20)

    S['h3'] = ParagraphStyle('h3',
        fontName=TEXT_BOLD, fontSize=11, textColor=C_LIGHT_BLUE,
        spaceBefore=8, spaceAfter=4, leading=16)

    # ── Cuerpo ───────────────────────────────────────────────────────────────
    S['body'] = ParagraphStyle('body',
        fontName=TEXT_FONT, fontSize=10, textColor=C_DARK,
        spaceAfter=5, leading=15)

    S['bullet'] = ParagraphStyle('bullet',
        fontName=TEXT_FONT, fontSize=10, textColor=C_DARK,
        spaceAfter=3, leading=14, leftIndent=18)

    S['mono_inline'] = ParagraphStyle('mono_inline',
        fontName=CODE_FONT, fontSize=9, textColor=C_DARK,
        spaceAfter=3, leading=13)

    # ── Cajas de aviso ───────────────────────────────────────────────────────
    S['tip'] = ParagraphStyle('tip',
        fontName=TEXT_FONT, fontSize=10, textColor=colors.HexColor('#1b5e20'),
        spaceAfter=6, leading=15, leftIndent=8,
        backColor=C_TIP_BG, borderPad=6,
        borderColor=colors.HexColor('#4caf50'), borderWidth=0.5)

    S['warn'] = ParagraphStyle('warn',
        fontName=TEXT_FONT, fontSize=10, textColor=colors.HexColor('#e65100'),
        spaceAfter=6, leading=15, leftIndent=8,
        backColor=C_WARN_BG, borderPad=6,
        borderColor=colors.HexColor('#ff9800'), borderWidth=0.5)

    S['err'] = ParagraphStyle('err',
        fontName=TEXT_BOLD, fontSize=10, textColor=colors.HexColor('#b71c1c'),
        spaceAfter=6, leading=15, leftIndent=8,
        backColor=C_ERR_BG, borderPad=6,
        borderColor=colors.HexColor('#ef9a9a'), borderWidth=0.5)

    # ── Codigo ───────────────────────────────────────────────────────────────
    S['code'] = ParagraphStyle('code',
        fontName=CODE_FONT, fontSize=7.2, textColor=C_DARK,
        spaceAfter=0, spaceBefore=0, leading=10.5,
        leftIndent=8, rightIndent=4,
        backColor=C_CODE_BG,
        borderColor=C_CODE_BORD, borderWidth=0.8, borderPad=6,
        wordWrap='CJK')

    S['code_title'] = ParagraphStyle('code_title',
        fontName=TEXT_BOLD, fontSize=9, textColor=colors.white,
        spaceAfter=0, spaceBefore=0, leading=14,
        backColor=C_HEAD_BG, borderPad=4)

    # ── Ejercicios ───────────────────────────────────────────────────────────
    S['ex_title'] = ParagraphStyle('ex_title',
        fontName=TEXT_BOLD, fontSize=12, textColor=colors.white,
        spaceAfter=0, leading=18,
        backColor=C_HEAD_BG, borderPad=6)

    S['ex_body'] = ParagraphStyle('ex_body',
        fontName=TEXT_FONT, fontSize=10, textColor=C_DARK,
        spaceAfter=4, leading=15,
        backColor=C_EXER_BG, borderPad=5,
        borderColor=C_CODE_BORD, borderWidth=0.5)

    return S


# =============================================================================
# HELPERS
# =============================================================================
def code_block(text, S, title=None):
    """Crea un bloque de codigo con titulo opcional."""
    items = []
    if title:
        items.append(Paragraph(html_mod.escape(title), S['code_title']))
    escaped = html_mod.escape(text)
    items.append(Preformatted(escaped, S['code']))
    items.append(Spacer(1, 4))
    return items

def h1(text, S):
    line = HRFlowable(width='100%', thickness=2, color=C_DARK_BLUE, spaceAfter=4)
    return [Paragraph(text, S['h1']), line]

def h2(text, S):
    return [Paragraph(text, S['h2'])]

def h3(text, S):
    return [Paragraph(text, S['h3'])]

def body(text, S):
    return Paragraph(text, S['body'])

def bul(text, S):
    return Paragraph(f'•  {text}', S['bullet'])

def tip(text, S):
    return Paragraph(f'<b>CONSEJO:</b> {text}', S['tip'])

def warn(text, S):
    return Paragraph(f'<b>IMPORTANTE:</b> {text}', S['warn'])

def err(text, S):
    return Paragraph(f'<b>ATENCION:</b> {text}', S['err'])

def sp(n=6):
    return Spacer(1, n)

def hr():
    return HRFlowable(width='100%', thickness=0.5, color=C_CODE_BORD, spaceAfter=6)

def ex_header(num, title, S):
    return Paragraph(f'  Ejercicio {num} — {title}', S['ex_title'])

def read_java(path):
    with open(path, encoding='utf-8') as f:
        return f.read()

# =============================================================================
# CONSTRUCCION DEL PDF
# =============================================================================
def build_pdf():
    BASE = r"C:\Users\alexi\Programacion\my_eclipse_workspace\ultimoExamen\src\ultimoExamen"
    OUT  = r"C:\Users\alexi\Programacion\my_eclipse_workspace\ultimoExamen\GUIA_EXAMEN_JAVA.pdf"

    oop_code   = read_java(os.path.join(BASE, 'APUNTES_OOP_FINAL_CURSO.java'))
    lam_code   = read_java(os.path.join(BASE, 'APUNTES_LAMBDA.java'))
    bbdd_code  = read_java(os.path.join(BASE, 'APUNTES_BASES_DE_DATOS.java'))
    ej_bbdd    = read_java(os.path.join(BASE, 'EJERCICIOS_RESUELTOS_BBDD.java'))
    gestion    = read_java(os.path.join(BASE, 'GESTION_PERSONA_COMPLETO.java'))

    doc = SimpleDocTemplate(
        OUT, pagesize=A4,
        leftMargin=1.8*cm, rightMargin=1.8*cm,
        topMargin=2*cm, bottomMargin=2*cm,
        title='Guia Examen Final Java - DAM',
        author='Gabriel Alexandru Iacob'
    )

    S    = mk_styles()
    story = []

    # =========================================================================
    # PORTADA
    # =========================================================================
    story += [
        Spacer(1, 3*cm),
        Paragraph('GUIA COMPLETA EXAMEN FINAL', S['cov_title']),
        Paragraph('PROGRAMACION JAVA — DAM', S['cov_sub']),
        sp(8),
        HRFlowable(width='80%', thickness=2, color=C_DARK_BLUE, hAlign='CENTER', spaceAfter=16),
        sp(8),
        Paragraph('Gabriel Alexandru Iacob', S['cov_sub']),
        Paragraph('3a Evaluacion | Curso 2025-2026', S['cov_info']),
        sp(30),
        Paragraph('Contenido:', S['cov_info']),
        sp(4),
        Paragraph('Orientacion a Objetos (POO)  |  Lambdas &amp; Streams  |  JDBC / MySQL', S['cov_info']),
        Paragraph('Ejercicios resueltos  |  Ejercicio completo  |  Ejercicios propuestos', S['cov_info']),
        Spacer(1, 4*cm),
        HRFlowable(width='60%', thickness=1, color=C_GRAY, hAlign='CENTER'),
        sp(8),
        Paragraph('Copia-pega directo — adapta nombres al enunciado', S['cov_info']),
        PageBreak(),
    ]

    # =========================================================================
    # INDICE
    # =========================================================================
    story += [
        Paragraph('INDICE', S['toc_title']),
        HRFlowable(width='100%', thickness=1.5, color=C_DARK_BLUE, spaceAfter=12),
        sp(4),
        Paragraph('1.  Orientacion a Objetos (POO)', S['toc_h1']),
        Paragraph('1.1  Patron del examen: Padre abstracto / Hija / Gestora', S['toc_h2']),
        Paragraph('1.2  Relaciones entre clases (Asociacion, Composicion, Herencia)', S['toc_h2']),
        Paragraph('1.3  Polimorfismo e instanceof', S['toc_h2']),
        Paragraph('1.4  ArrayList: metodos esenciales', S['toc_h2']),
        Paragraph('1.5  static vs instance, constructores con super()', S['toc_h2']),
        Paragraph('1.6  Interfaces, toString y equals', S['toc_h2']),
        Paragraph('1.7  Codigo completo de referencia', S['toc_h2']),
        sp(6),
        Paragraph('2.  Lambdas y Streams', S['toc_h1']),
        Paragraph('2.1  Interfaces funcionales estandar (Runnable, Consumer, Supplier, Function, Predicate)', S['toc_h2']),
        Paragraph('2.2  Interfaces funcionales propias (@FunctionalInterface)', S['toc_h2']),
        Paragraph('2.3  Ordenar con lambda y Comparator', S['toc_h2']),
        Paragraph('2.4  Streams: intermedias y terminales', S['toc_h2']),
        Paragraph('2.5  Streams con objetos propios', S['toc_h2']),
        Paragraph('2.6  Referencias a metodos y Predicate encadenado', S['toc_h2']),
        Paragraph('2.7  Codigo completo de referencia', S['toc_h2']),
        sp(6),
        Paragraph('3.  Bases de Datos — JDBC + MySQL', S['toc_h1']),
        Paragraph('3.1  Plantilla base de conexion', S['toc_h2']),
        Paragraph('3.2  Statement y PreparedStatement', S['toc_h2']),
        Paragraph('3.3  TYPE_SCROLL_INSENSITIVE: navegar ResultSet', S['toc_h2']),
        Paragraph('3.4  Comprobar ResultSet vacio y contar filas', S['toc_h2']),
        Paragraph('3.5  Dos consultas anidadas', S['toc_h2']),
        Paragraph('3.6  INSERT / UPDATE / DELETE (CRUD)', S['toc_h2']),
        Paragraph('3.7  Tipos de datos SQL y getXxx()', S['toc_h2']),
        Paragraph('3.8  Codigo completo de referencia', S['toc_h2']),
        sp(6),
        Paragraph('4.  Ejercicios Resueltos BBDD', S['toc_h1']),
        Paragraph('10 ejercicios con patrones reales de classicmodels / sakila', S['toc_h2']),
        sp(6),
        Paragraph('5.  Ejercicio Completo: Sistema Medico', S['toc_h1']),
        Paragraph('Codigo ejecutable completo: Persona -> Medico/Paciente -> CentroMedico -> Consulta', S['toc_h2']),
        sp(6),
        Paragraph('6.  Ejercicios Propuestos (para practicar)', S['toc_h1']),
        Paragraph('6.1  POO: Sistema de Biblioteca', S['toc_h2']),
        Paragraph('6.2  Lambdas: Analisis de lista de productos', S['toc_h2']),
        Paragraph('6.3  BBDD: Consultas avanzadas', S['toc_h2']),
        PageBreak(),
    ]

    # =========================================================================
    # SECCION 1 — ORIENTACION A OBJETOS
    # =========================================================================
    story += h1('1. ORIENTACION A OBJETOS (POO)', S)
    story += [
        body('Patron mas habitual en el examen: una jerarquia de clases con una clase abstracta padre, '
             'clases hijas que la extienden, y una clase gestora que contiene ArrayLists.', S),
        sp(8),
    ]

    story += h2('1.1  Patron del examen — estructura minima', S)
    story += [
        body('El examen siempre sigue el mismo esquema de 4 capas:', S),
        bul('<b>Clase Padre abstracta</b>: campos protected comunes + metodo abstracto', S),
        bul('<b>Clases Hija</b> (una o varias): extends Padre, anaden sus propios campos', S),
        bul('<b>Clase Gestora</b>: tiene un ArrayList&lt;Hija&gt;, metodos CRUD y busqueda', S),
        bul('<b>Main</b>: crea objetos y llama a los metodos', S),
        sp(6),
        warn('super() debe ser SIEMPRE la primera instruccion del constructor hijo.', S),
        sp(6),
    ]

    story += code_block(
        """// ── CLASE PADRE ABSTRACTA ────────────────────────────────────────────
abstract class Padre {
    protected String nombre;       // protected = visible en hijas sin getter
    protected String apellidos;
    protected static int contador = 0;  // compartido entre todas las instancias

    public Padre(String nombre, String apellidos) {
        this.nombre    = nombre;
        this.apellidos = apellidos;
        Padre.contador++;
    }

    public abstract void mostrar();    // las hijas DEBEN implementarlo

    public String getNombreCompleto() { return nombre + " " + apellidos; }
    public static int getContador()   { return contador; }
}

// ── CLASE HIJA ────────────────────────────────────────────────────────────
class Hija extends Padre {
    private String campoPropio;

    public Hija(String nombre, String apellidos, String campoPropio) {
        super(nombre, apellidos);         // llama al constructor del padre
        this.campoPropio = campoPropio;
    }

    @Override
    public void mostrar() {
        System.out.println(getNombreCompleto() + " - " + campoPropio);
    }

    public String getCampoPropio() { return campoPropio; }
}

// ── CLASE GESTORA ─────────────────────────────────────────────────────────
class Gestora {
    private String nombre;
    private ArrayList<Hija> lista = new ArrayList<>();

    public Gestora(String nombre) { this.nombre = nombre; }

    public void anyadir(Hija h)   { lista.add(h); }
    public void eliminar(Hija h)  { lista.remove(h); }

    public void listar() {
        for (Hija h : lista) { h.mostrar(); }
    }

    public Hija buscarPorNombre(String nombre) {
        for (Hija h : lista) {
            if (h.getNombre().equalsIgnoreCase(nombre)) return h;
        }
        return null;  // no encontrado
    }

    public int total() { return lista.size(); }
}

// ── MAIN ──────────────────────────────────────────────────────────────────
// 1. Crear gestora
Gestora g = new Gestora("MiGestora");
// 2. Crear hijas (pueden anyadirse solas al gestor en el constructor)
Hija h1 = new Hija("Ana", "Lopez", "Dato1");
g.anyadir(h1);
Hija h2 = new Hija("Carlos", "Ruiz", "Dato2");
g.anyadir(h2);
// 3. Listar y buscar
g.listar();
Hija encontrada = g.buscarPorNombre("Ana");""",
        S, title='Patron completo del examen (Padre / Hija / Gestora / Main)')

    story += h2('1.2  Relaciones entre clases', S)
    story += [
        bul('<b>Asociacion</b>: un objeto TIENE una referencia a otro (campo de tipo objeto)', S),
        bul('<b>Composicion</b>: el objeto CREA sus dependencias (ArrayList creado en la declaracion)', S),
        bul('<b>Herencia</b>: IS-A — Alumno extends Persona ("Alumno ES UNA Persona")', S),
        sp(4),
        tip('Patron tipico del examen: al crear una hija, se aniade sola al gestor con "gestor.anyadir(this)" en su constructor.', S),
        sp(6),
    ]

    story += code_block(
        """// PATRON: el objeto se registra solo en el gestor al crearse
class Alumno extends Persona {
    public Alumno(String nombre, Grupo grupo) {
        super(nombre);
        grupo.anyadirAlumno(this);   // <- se aniade solo al crearse
    }
}

// ASOCIACION: un Medico tiene una referencia a su CentroMedico
class Medico extends Persona {
    private CentroMedico centro;  // referencia a otro objeto (asociacion)
    private ArrayList<Paciente> pacientes = new ArrayList<>();  // composicion
}""",
        S, title='Relaciones entre clases')

    story += h2('1.3  Polimorfismo e instanceof', S)
    story += code_block(
        """// Lista de tipo Padre que guarda objetos de distintas subclases
ArrayList<Padre> todos = new ArrayList<>();
todos.add(new Hija1("Ana",    "Lopez", "Dato1"));
todos.add(new Hija2("Carlos", "Ruiz",  42));

for (Padre p : todos) {
    p.mostrar();   // cada objeto ejecuta SU version de mostrar()
}

// Comprobar el tipo real con instanceof
for (Padre p : todos) {
    if (p instanceof Hija1) {
        Hija1 h = (Hija1) p;      // cast necesario para metodos de Hija1
        h.metodoExclusivoDeHija1();
    } else if (p instanceof Hija2) {
        Hija2 h = (Hija2) p;
        h.otroMetodo();
    }
}""",
        S, title='Polimorfismo e instanceof')

    story += h2('1.4  ArrayList — metodos esenciales', S)
    story += code_block(
        """ArrayList<Tipo> lista = new ArrayList<>();

lista.add(objeto)           // aniade al final
lista.add(0, objeto)        // aniade en la posicion 0
lista.remove(objeto)        // elimina por referencia (usa equals)
lista.remove(0)             // elimina por indice
lista.get(0)                // obtiene por indice
lista.set(0, nuevoObjeto)   // sustituye en el indice 0
lista.size()                // numero de elementos
lista.isEmpty()             // true si esta vacia
lista.contains(objeto)      // true si lo contiene
lista.indexOf(objeto)       // indice (-1 si no existe)
lista.clear()               // vacia la lista

// Iterar (el mas usado en el examen):
for (Tipo t : lista) {
    t.metodo();
}

// Iterar con indice:
for (int i = 0; i < lista.size(); i++) {
    Tipo t = lista.get(i);
}

// Eliminar mientras se itera (usa Iterator para evitar errores):
Iterator<Tipo> it = lista.iterator();
while (it.hasNext()) {
    Tipo t = it.next();
    if (condicion) it.remove();
}""",
        S, title='ArrayList: metodos clave')

    story += h2('1.5  static vs instance / constructores con super()', S)
    story += code_block(
        """// STATIC: pertenece a la CLASE, existe sin instancias
class Persona {
    private static int contador = 0;   // 1 copia compartida entre todos
    public Persona(String nombre) {
        Persona.contador++;            // incrementa con cada new
    }
    public static int getContador() { return contador; }
}
// Uso: Persona.getContador() → no necesitas un objeto

// SUPER: llama al constructor del padre (SIEMPRE primera instruccion)
class Alumno extends Persona {
    private String ciclo;
    public Alumno(String nombre, int edad, String ciclo) {
        super(nombre, edad);   // llama a Persona(String, int)
        this.ciclo = ciclo;
    }
}

// THIS: llama a otro constructor de la misma clase
class Persona {
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad   = edad;
    }
    public Persona(String nombre) {
        this(nombre, 0);    // delega al constructor con 2 params
    }
}""",
        S, title='static vs instance, super() y this()')

    story += h2('1.6  Interfaces, toString y equals', S)
    story += code_block(
        """// INTERFACE: contrato sin estado (puede tener metodos default)
interface Describible {
    void describir();                   // abstracto por defecto
    default void saludar() {           // con implementacion
        System.out.println("Hola");
    }
}

// Una clase puede implementar VARIAS interfaces
class Producto implements Describible, Comparable<Producto> { ... }

// TOSTRING: permite usar System.out.println(objeto)
@Override
public String toString() {
    return "Persona[nombre=" + nombre + ", edad=" + edad + "]";
}

// EQUALS: para que lista.contains() y lista.remove() funcionen bien
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Persona)) return false;
    Persona otra = (Persona) obj;
    return this.nombre.equals(otra.nombre);
}""",
        S, title='Interfaces, toString y equals')

    story += h2('1.7  Codigo completo de referencia (APUNTES_OOP_FINAL_CURSO.java)', S)
    story += code_block(oop_code, S, title='APUNTES_OOP_FINAL_CURSO.java — codigo completo')
    story.append(PageBreak())

    # =========================================================================
    # SECCION 2 — LAMBDAS Y STREAMS
    # =========================================================================
    story += h1('2. LAMBDAS Y STREAMS', S)
    story += [
        body('Las lambdas sustituyen a clases anonimas con una sola linea. '
             'Los streams permiten procesar colecciones de forma declarativa encadenando operaciones.', S),
        sp(8),
    ]

    story += h2('2.1  Interfaces funcionales estandar', S)
    story += code_block(
        """// RUNNABLE — sin parametros, sin retorno
Runnable saludo = () -> System.out.println("Hola mundo");
saludo.run();

// CONSUMER<T> — recibe T, no devuelve nada
Consumer<String> imprimir = s -> System.out.println(">> " + s);
imprimir.accept("Hola");

// SUPPLIER<T> — sin parametros, devuelve T
Supplier<String> nombre = () -> "Alexandru";
System.out.println(nombre.get());

// FUNCTION<T, R> — recibe T, devuelve R
Function<String, Integer> longitud = s -> s.length();
System.out.println(longitud.apply("Hola"));  // -> 4

// PREDICATE<T> — recibe T, devuelve boolean
Predicate<Integer> esMayor18 = n -> n >= 18;
System.out.println(esMayor18.test(21));   // -> true

// COMPARATOR<T> — compara dos T (ver seccion 2.3)
Comparator<String> porLong = (a, b) -> a.length() - b.length();""",
        S, title='Interfaces funcionales estandar (java.util.function)')

    story += h2('2.2  Interfaces funcionales propias (@FunctionalInterface)', S)
    story += code_block(
        """// Definicion de la interfaz (exactamente UN metodo abstracto)
@FunctionalInterface
interface Operacion {
    int calcular(int a, int b);
}

// Uso: asignar lambdas a la interfaz
Operacion suma   = (a, b) -> a + b;
Operacion resta  = (a, b) -> a - b;
Operacion multi  = (a, b) -> a * b;
Operacion modulo = (a, b) -> a % b;

System.out.println(suma.calcular(10, 3));   // -> 13
System.out.println(resta.calcular(10, 3));  // -> 7
System.out.println(multi.calcular(10, 3));  // -> 30

// Pasar la lambda como PARAMETRO de un metodo
public static int operar(int a, int b, Operacion op) {
    return op.calcular(a, b);
}
System.out.println(operar(5, 3, suma));    // -> 8
System.out.println(operar(5, 3, resta));   // -> 2""",
        S, title='Interface funcional propia y pasarla como parametro')

    story += h2('2.3  Ordenar con lambda y Comparator', S)
    story += code_block(
        """ArrayList<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Beatriz"));

// Orden alfabetico (natural)
nombres.sort((a, b) -> a.compareTo(b));
System.out.println(nombres);  // -> [Ana, Beatriz, Carlos]

// Orden inverso
nombres.sort((a, b) -> b.compareTo(a));

// Por longitud
nombres.sort((a, b) -> a.length() - b.length());

// Comparator.comparing (mas limpio y seguro)
nombres.sort(Comparator.comparing(String::length));          // ascendente
nombres.sort(Comparator.comparing(String::length).reversed()); // descendente
nombres.sort(Comparator.naturalOrder());    // alfabetico
nombres.sort(Comparator.reverseOrder());   // inverso

// Para objetos propios (ej: Persona con nombre y edad)
personas.sort((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
personas.sort((p1, p2) -> p1.getEdad() - p2.getEdad());

// Desempate: primero por nombre, luego por edad
personas.sort(Comparator.comparing(Persona::getNombre)
                         .thenComparingInt(Persona::getEdad));""",
        S, title='Ordenar con lambda y Comparator')

    story += h2('2.4  Streams — operaciones intermedias y terminales', S)
    story += [
        body('<b>Intermedias</b> (lazy, no ejecutan hasta la terminal):', S),
        bul('<b>filter(pred)</b>  — filtra elementos que cumplen la condicion', S),
        bul('<b>map(func)</b>     — transforma cada elemento', S),
        bul('<b>sorted()</b>      — ordena (natural o con comparador)', S),
        bul('<b>distinct()</b>    — elimina duplicados', S),
        bul('<b>limit(n)</b>      — maximo n elementos', S),
        bul('<b>skip(n)</b>       — salta los primeros n', S),
        sp(4),
        body('<b>Terminales</b> (ejecutan todo el pipeline):', S),
        bul('<b>forEach(consumer)</b>             — recorre, no devuelve nada', S),
        bul('<b>collect(Collectors.toList())</b>   — devuelve lista', S),
        bul('<b>count()</b>                        — numero de elementos (long)', S),
        bul('<b>findFirst()</b>                    — Optional&lt;T&gt; con el primero', S),
        bul('<b>anyMatch / allMatch / noneMatch</b> — boolean', S),
        bul('<b>min(comp) / max(comp)</b>          — Optional&lt;T&gt; con el minimo/maximo', S),
        bul('<b>mapToInt(...).sum()</b>            — suma de ints', S),
        bul('<b>mapToDouble(...).average()</b>     — OptionalDouble con la media', S),
        sp(6),
    ]

    story += code_block(
        """ArrayList<Integer> nums = new ArrayList<>(List.of(1, 5, 3, 8, 2, 7, 4, 9, 6));

// filter + collect
List<Integer> mayores = nums.stream()
    .filter(n -> n > 5)
    .collect(Collectors.toList());           // -> [8, 7, 9, 6]

// filter + sorted + collect (pares ordenados)
List<Integer> paresOrdenados = nums.stream()
    .filter(n -> n % 2 == 0)
    .sorted()
    .collect(Collectors.toList());           // -> [2, 4, 6, 8]

// map: transformar a String
List<String> cadenas = nums.stream()
    .map(n -> "Num:" + n)
    .collect(Collectors.toList());

// count
long cuantos = nums.stream().filter(n -> n > 5).count();  // -> 4

// sum
int suma = nums.stream().mapToInt(Integer::intValue).sum(); // -> 45

// max/min
Optional<Integer> maximo = nums.stream().max(Comparator.naturalOrder());
maximo.ifPresent(m -> System.out.println("Max: " + m));    // -> 9

// anyMatch / allMatch / noneMatch
boolean hayGrande = nums.stream().anyMatch(n -> n > 10);   // -> false
boolean todosPosi  = nums.stream().allMatch(n -> n > 0);   // -> true""",
        S, title='Streams: ejemplos de todas las operaciones')

    story += h2('2.5  Streams con objetos propios (mas habitual en examen)', S)
    story += code_block(
        """// Suponiendo clase Alumno con getNombre(), getNota()

// Filtrar aprobados (nota >= 5)
List<Alumno> aprobados = alumnos.stream()
    .filter(a -> a.getNota() >= 5.0)
    .collect(Collectors.toList());

// Obtener solo los nombres
List<String> nombres = alumnos.stream()
    .map(Alumno::getNombre)        // referencia a metodo getter
    .collect(Collectors.toList());

// Ordenar por nota descendente
alumnos.sort(Comparator.comparingDouble(Alumno::getNota).reversed());

// Media de notas
OptionalDouble media = alumnos.stream()
    .mapToDouble(Alumno::getNota)
    .average();
media.ifPresent(m -> System.out.printf("Media: %.2f%n", m));

// El alumno con mejor nota
Optional<Alumno> mejor = alumnos.stream()
    .max(Comparator.comparingDouble(Alumno::getNota));
mejor.ifPresent(a -> System.out.println("Mejor: " + a.getNombre()));

// Nombres de alumnos con nota > 7, en mayusculas, ordenados
List<String> buenos = alumnos.stream()
    .filter(a -> a.getNota() > 7.0)
    .map(a -> a.getNombre().toUpperCase())
    .sorted()
    .collect(Collectors.toList());""",
        S, title='Streams con objetos propios')

    story += h2('2.6  Referencias a metodos y Predicate encadenado', S)
    story += code_block(
        """// REFERENCIAS A METODOS
//  Tipo                  Sintaxis                  Equivalente lambda
//  Estatico              Integer::parseInt         s -> Integer.parseInt(s)
//  De instancia (obj)    System.out::println       s -> System.out.println(s)
//  De instancia (tipo)   String::toUpperCase       s -> s.toUpperCase()
//  Constructor           Persona::new              () -> new Persona()

lista.forEach(System.out::println);
lista.stream().map(String::toUpperCase).collect(Collectors.toList());

// PREDICATE ENCADENADO — and, or, negate
Predicate<Integer> par    = n -> n % 2 == 0;
Predicate<Integer> mayor5 = n -> n > 5;

Predicate<Integer> parYmayor5 = par.and(mayor5);
Predicate<Integer> parOMayor5 = par.or(mayor5);
Predicate<Integer> noPar      = par.negate();

nums.stream().filter(parYmayor5).forEach(System.out::println); // -> 6 8 10

// LAMBDA EN THREADS
Thread t = new Thread(() -> System.out.println("Thread con lambda"));
t.start();""",
        S, title='Referencias a metodos y Predicate')

    story += h2('2.7  Codigo completo de referencia (APUNTES_LAMBDA.java)', S)
    story += code_block(lam_code, S, title='APUNTES_LAMBDA.java — codigo completo')
    story.append(PageBreak())

    # =========================================================================
    # SECCION 3 — BASES DE DATOS
    # =========================================================================
    story += h1('3. BASES DE DATOS — JDBC + MySQL', S)
    story += [
        body('JDBC (Java DataBase Connectivity) permite conectarse a MySQL desde Java. '
             'Los imports necesarios son de java.sql.*. '
             'La conexion se gestiona con try-with-resources para cerrarse sola.', S),
        sp(6),
    ]

    story += h2('3.1  Plantilla base de conexion', S)
    story += code_block(
        """import java.sql.*;

// Datos de conexion (cambia segun el ejercicio)
static String USUARIO  = "admin";
static String PASSWORD = "1234";
static String SERVIDOR = "jdbc:mysql://localhost:3306/classicmodels"; // <- cambia BD

// Plantilla try-with-resources: cierra la conexion aunque haya error
public static void plantillaBase() {
    try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
        System.out.println("Conexion OK");
        // <- tu codigo aqui
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// Patron para pasar la conexion como parametro (evita abrir N conexiones)
public static void main(String[] args) {
    try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
        metodo1(cnx, "parametro");
        metodo2(cnx, 42);
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

public static void metodo1(Connection cnx, String param) throws SQLException {
    // usa cnx directamente, sin abrir nueva conexion
}""",
        S, title='Plantilla base de conexion JDBC')

    story += h2('3.2  Statement vs PreparedStatement', S)
    story += [
        bul('<b>Statement</b>: consultas simples sin parametros. Suficiente para SELECT * FROM tabla.', S),
        bul('<b>PreparedStatement</b>: con parametros (?). Mas seguro y necesario para SCROLL.', S),
        sp(4),
    ]
    story += code_block(
        """// STATEMENT — sin parametros
Statement stmt = cnx.createStatement();
ResultSet rs   = stmt.executeQuery("SELECT * FROM employees");
while (rs.next()) {
    System.out.println(rs.getInt("employeeNumber") + " - " + rs.getString("firstName"));
}

// PREPAREDSTATEMENT — con parametros (? = marcador de posicion)
PreparedStatement ps = cnx.prepareStatement(
    "SELECT employeeNumber, firstName, lastName FROM employees WHERE lastName = ?"
);
ps.setString(1, apellido);   // 1 = posicion del primer ?
// ps.setInt(1, numero);     // para INT
// ps.setDouble(1, valor);   // para DOUBLE
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    System.out.printf("%d | %s %s%n",
        rs.getInt("employeeNumber"),
        rs.getString("firstName"),
        rs.getString("lastName"));
}""",
        S, title='Statement vs PreparedStatement')

    story += h2('3.3  TYPE_SCROLL_INSENSITIVE — navegar el ResultSet libremente', S)
    story += [
        warn('Necesitas TYPE_SCROLL_INSENSITIVE para usar: last(), first(), previous(), absolute(), relative()', S),
        sp(4),
    ]
    story += code_block(
        """// Necesario para navegar con last(), first(), previous(), etc.
PreparedStatement ps = cnx.prepareStatement(
    "SELECT * FROM actor WHERE first_name = ?",
    ResultSet.TYPE_SCROLL_INSENSITIVE,   // <- permite navegar
    ResultSet.CONCUR_READ_ONLY            // <- solo lectura
);
ps.setString(1, nombre);
ResultSet rs = ps.executeQuery();

rs.last();                  // mueve al ultimo registro
int total = rs.getRow();    // getRow() en last() = total de filas
System.out.println("Total: " + total);

rs.beforeFirst();           // vuelve al principio para recorrer normalmente
while (rs.next()) {
    System.out.println(rs.getString("first_name"));
}

// RECORRER EN ORDEN INVERSO
rs.afterLast();             // posicion despues del ultimo
while (rs.previous()) {     // false cuando no hay mas
    System.out.println("Fila " + rs.getRow() + ": " + rs.getString("first_name"));
}

// METODOS DE NAVEGACION (resumen):
// rs.next()          -> siguiente (el mas usado)
// rs.previous()      -> anterior (necesita SCROLL)
// rs.first()         -> primera fila
// rs.last()          -> ultima fila
// rs.beforeFirst()   -> antes de la primera (posicion inicial)
// rs.afterLast()     -> despues de la ultima (para recorrer con previous)
// rs.absolute(n)     -> fila n exacta (negativo = desde el final)
// rs.relative(n)     -> mueve n posiciones desde donde estas
// rs.getRow()        -> numero de la fila actual (0 si no hay)
// rs.isBeforeFirst() -> true si esta antes de la primera fila""",
        S, title='TYPE_SCROLL_INSENSITIVE y metodos de navegacion')

    story += h2('3.4  Comprobar ResultSet vacio y contar filas', S)
    story += code_block(
        """// FORMA 1: isBeforeFirst() — true si el ResultSet esta vacio
if (!rs.isBeforeFirst()) {
    System.out.println("Sin resultados");
    return;
}

// FORMA 2: next() devuelve false si no hay primera fila
if (!rs.next()) {
    System.out.println("Sin resultados");
    return;
}
// (atencion: ya hemos avanzado a la primera fila)

// FORMA 3 (con SCROLL): last() devuelve false si esta vacio
if (!rs.last()) {
    System.out.println("Sin resultados");
    return;
}
int total = rs.getRow();  // = numero total de filas
rs.beforeFirst();         // volver al inicio para recorrer

// COUNT(*) — forma mas simple de contar (una sola consulta)
Statement stmt = cnx.createStatement();
ResultSet rs   = stmt.executeQuery("SELECT COUNT(*) FROM employees");
rs.next();                        // COUNT siempre devuelve exactamente 1 fila
int total = rs.getInt(1);        // columna 1 = el resultado del COUNT""",
        S, title='Comprobar ResultSet vacio y contar filas')

    story += h2('3.5  Dos consultas anidadas (patron tipico del examen)', S)
    story += code_block(
        """public static void consultasAnidadas(Connection cnx, String apellido) throws SQLException {

    // CONSULTA 1: encontrar el jefe
    PreparedStatement ps1 = cnx.prepareStatement(
        "SELECT employeeNumber, firstName, lastName FROM employees WHERE lastName = ?"
    );
    ps1.setString(1, apellido);
    ResultSet rs1 = ps1.executeQuery();

    if (!rs1.isBeforeFirst()) {       // ResultSet vacio = no existe
        System.out.println("No existe: " + apellido);
        return;
    }

    // CONSULTA 2: se prepara fuera del while para reutilizarla
    PreparedStatement ps2 = cnx.prepareStatement(
        "SELECT firstName, lastName FROM employees WHERE reportsTo = ?"
    );

    while (rs1.next()) {
        int num = rs1.getInt("employeeNumber");
        System.out.println("Jefe: " + rs1.getString("firstName"));

        ps2.setInt(1, num);            // reutilizamos el mismo PreparedStatement
        ResultSet rs2 = ps2.executeQuery();

        if (!rs2.isBeforeFirst()) {
            System.out.println("  -> No le reporta nadie");
        } else {
            while (rs2.next()) {
                System.out.println("  -> " + rs2.getString("firstName"));
            }
        }
        rs2.close();                   // cerrar el ResultSet interno en cada iteracion
    }
    ps2.close(); rs1.close(); ps1.close();
}""",
        S, title='Dos consultas anidadas — patron del examen')

    story += h2('3.6  INSERT / UPDATE / DELETE — CRUD basico', S)
    story += [
        warn('CRUD usa executeUpdate() (no executeQuery()). Devuelve el numero de filas afectadas.', S),
        sp(4),
    ]
    story += code_block(
        """// INSERT
PreparedStatement ins = cnx.prepareStatement(
    "INSERT INTO employees (employeeNumber, firstName, lastName, email, officeCode, jobTitle) VALUES (?,?,?,?,?,?)"
);
ins.setInt(1, 9999);
ins.setString(2, "Alexandru");
ins.setString(3, "Iacob");
ins.setString(4, "alex@test.com");
ins.setString(5, "1");
ins.setString(6, "Sales Rep");
int filas = ins.executeUpdate();   // devuelve nro de filas insertadas
System.out.println("Insertadas: " + filas);  // -> 1

// UPDATE
PreparedStatement upd = cnx.prepareStatement(
    "UPDATE employees SET jobTitle = ? WHERE employeeNumber = ?"
);
upd.setString(1, "Manager");
upd.setInt(2, 9999);
System.out.println("Actualizadas: " + upd.executeUpdate());

// DELETE
PreparedStatement del = cnx.prepareStatement(
    "DELETE FROM employees WHERE employeeNumber = ?"
);
del.setInt(1, 9999);
System.out.println("Borradas: " + del.executeUpdate());""",
        S, title='CRUD: INSERT / UPDATE / DELETE')

    story += h2('3.7  Tipos de datos SQL y getXxx()', S)
    story += code_block(
        """// Tipo SQL      ->  metodo Java
// VARCHAR, CHAR, TEXT  ->  rs.getString("col")
// INT, TINYINT         ->  rs.getInt("col")
// BIGINT               ->  rs.getLong("col")
// DOUBLE, FLOAT        ->  rs.getDouble("col")
// BOOLEAN, TINYINT(1)  ->  rs.getBoolean("col")
// DATE                 ->  rs.getDate("col")       // java.sql.Date
// DATETIME, TIMESTAMP  ->  rs.getTimestamp("col")  // java.sql.Timestamp
// Por numero de columna (empieza en 1): rs.getInt(1), rs.getString(2)

// QUERIES SQL UTILES
// SELECT * FROM tabla
// SELECT col1, col2 FROM tabla WHERE col = ?
// SELECT * FROM tabla WHERE col LIKE ?         -> setString(1, "%texto%")
// SELECT * FROM tabla ORDER BY col ASC/DESC
// SELECT * FROM tabla WHERE col1 = ? AND col2 > ?
// SELECT COUNT(*) FROM tabla WHERE col = ?
// SELECT MAX(col), MIN(col), SUM(col), AVG(col) FROM tabla
// SELECT t1.col, t2.col FROM tabla1 t1 JOIN tabla2 t2 ON t1.id = t2.id
// SHOW TABLES                                  -> lista todas las tablas
// SELECT * FROM tabla LIMIT 10""",
        S, title='Tipos de datos y queries SQL utiles')

    story += h2('3.8  Codigo completo de referencia (APUNTES_BASES_DE_DATOS.java)', S)
    story += code_block(bbdd_code, S, title='APUNTES_BASES_DE_DATOS.java — codigo completo')
    story.append(PageBreak())

    # =========================================================================
    # SECCION 4 — EJERCICIOS RESUELTOS BBDD
    # =========================================================================
    story += h1('4. EJERCICIOS RESUELTOS — BBDD (JDBC)', S)
    story += [
        body('10 ejercicios con los patrones que han salido en clase. '
             'Cada ejercicio incluye comentario del patron que usa.', S),
        sp(6),
    ]
    story += code_block(ej_bbdd, S, title='EJERCICIOS_RESUELTOS_BBDD.java — 10 ejercicios completos')
    story.append(PageBreak())

    # =========================================================================
    # SECCION 5 — EJERCICIO COMPLETO: SISTEMA MEDICO
    # =========================================================================
    story += h1('5. EJERCICIO COMPLETO — SISTEMA MEDICO', S)
    story += [
        body('Ejercicio completamente ejecutable con todas las capas: clase abstracta, '
             'herencia, composicion, ArrayList y metodos CRUD. '
             'Sirve de plantilla directa para el examen.', S),
        sp(4),
        bul('<b>Persona</b> (abstracta) — campos: nombre, apellidos, centro', S),
        bul('<b>Medico</b> extends Persona — especialidad, numColegiado, ArrayList de pacientes', S),
        bul('<b>Paciente</b> extends Persona — DNI, telefono', S),
        bul('<b>Consulta</b> — une Medico + Paciente con fecha y diagnostico', S),
        bul('<b>CentroMedico</b> — gestora con listas de medicos, pacientes y consultas', S),
        bul('<b>MainGestionPersona</b> — demostracion completa', S),
        sp(8),
        tip('Los objetos Medico y Paciente se registran solos en el CentroMedico en su constructor. '
            'Las Consultas se registran en el medico, el paciente y el centro.', S),
        sp(6),
    ]
    story += code_block(gestion, S, title='GESTION_PERSONA_COMPLETO.java — sistema medico ejecutable')
    story.append(PageBreak())

    # =========================================================================
    # SECCION 6 — EJERCICIOS PROPUESTOS
    # =========================================================================
    story += h1('6. EJERCICIOS PROPUESTOS (para practicar)', S)
    story += [
        body('Ejercicios adicionales para reforzar antes del examen. '
             'Cada uno incluye el enunciado, las clases que debes crear y la solucion guiada.', S),
        sp(8),
    ]

    # ── Ejercicio 6.1 — POO: Biblioteca ─────────────────────────────────────
    story += h2('6.1  POO — Sistema de Biblioteca', S)
    story += [
        Paragraph('Ejercicio 6.1: Sistema de Biblioteca', S['ex_title']),
        sp(2),
        Paragraph(
            'Crea un sistema de gestion de una biblioteca con las siguientes clases: '
            'Elemento (abstracta, con titulo, anyoPublicacion y el metodo abstracto mostrar()), '
            'Libro (extiende Elemento, aniade autor e ISBN), '
            'Revista (extiende Elemento, aniade editorial y numeroEdicion), '
            'Prestamo (une un Elemento con un usuario y una fecha), '
            'Biblioteca (gestora con listas de elementos, prestamos y metodos CRUD).',
            S['ex_body']),
        sp(4),
        body('<b>Puntos clave a implementar:</b>', S),
        bul('Clase abstracta Elemento con campo static contador', S),
        bul('Libro y Revista extienden Elemento e implementan mostrar()', S),
        bul('Al crear un Libro o Revista, se aniade solo a la Biblioteca', S),
        bul('Biblioteca.prestar(elemento, usuario) crea y registra el Prestamo', S),
        bul('Biblioteca.buscarPorAutor(autor) devuelve lista de Libros', S),
        sp(6),
    ]
    story += code_block(
        """// SOLUCION GUIADA — Biblioteca

import java.time.LocalDate;
import java.util.ArrayList;

abstract class Elemento {
    protected String titulo;
    protected int anyoPublicacion;
    private static int contador = 0;

    public Elemento(String titulo, int anyoPublicacion, Biblioteca bib) {
        this.titulo           = titulo;
        this.anyoPublicacion  = anyoPublicacion;
        Elemento.contador++;
        bib.anyadirElemento(this);   // se registra solo
    }

    public abstract void mostrar();

    public String getTitulo()   { return titulo; }
    public int    getAnyo()     { return anyoPublicacion; }
    public static int getContador() { return contador; }

    @Override
    public String toString() { return titulo + " (" + anyoPublicacion + ")"; }
}

class Libro extends Elemento {
    private String autor;
    private String ISBN;

    public Libro(String titulo, int anyo, String autor, String ISBN, Biblioteca bib) {
        super(titulo, anyo, bib);
        this.autor = autor;
        this.ISBN  = ISBN;
    }

    @Override
    public void mostrar() {
        System.out.printf("[LIBRO] %s | Autor: %s | ISBN: %s%n", titulo, autor, ISBN);
    }

    public String getAutor() { return autor; }
    public String getISBN()  { return ISBN; }
}

class Revista extends Elemento {
    private String editorial;
    private int    numeroEdicion;

    public Revista(String titulo, int anyo, String editorial, int num, Biblioteca bib) {
        super(titulo, anyo, bib);
        this.editorial     = editorial;
        this.numeroEdicion = num;
    }

    @Override
    public void mostrar() {
        System.out.printf("[REVISTA] %s | Editorial: %s | Ed. %d%n",
            titulo, editorial, numeroEdicion);
    }
}

class Prestamo {
    private Elemento  elemento;
    private String    usuario;
    private LocalDate fecha;

    public Prestamo(Elemento elemento, String usuario) {
        this.elemento = elemento;
        this.usuario  = usuario;
        this.fecha    = LocalDate.now();
    }

    public void mostrar() {
        System.out.printf("Prestamo: '%s' -> %s (%s)%n",
            elemento.getTitulo(), usuario, fecha);
    }

    public Elemento getElemento() { return elemento; }
    public String   getUsuario()  { return usuario; }
}

class Biblioteca {
    private String             nombre;
    private ArrayList<Elemento>  elementos = new ArrayList<>();
    private ArrayList<Prestamo>  prestamos = new ArrayList<>();

    public Biblioteca(String nombre) { this.nombre = nombre; }

    public void anyadirElemento(Elemento e)  { elementos.add(e); }

    public void prestar(Elemento e, String usuario) {
        Prestamo p = new Prestamo(e, usuario);
        prestamos.add(p);
        System.out.println("Prestamo registrado: " + e.getTitulo());
    }

    public void listarElementos() {
        System.out.println("Catalogo de " + nombre + ":");
        for (Elemento e : elementos) e.mostrar();
    }

    public ArrayList<Libro> buscarPorAutor(String autor) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Elemento e : elementos) {
            if (e instanceof Libro) {
                Libro l = (Libro) e;
                if (l.getAutor().equalsIgnoreCase(autor)) resultado.add(l);
            }
        }
        return resultado;
    }

    public int totalElementos()  { return elementos.size(); }
    public int totalPrestamos()  { return prestamos.size(); }
}

// MAIN
public class MainBiblioteca {
    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca("Biblioteca Central");

        Libro   l1 = new Libro("El Quijote",    1605, "Cervantes",   "978-0",  bib);
        Libro   l2 = new Libro("Cien anios",     1967, "Garcia Marquez", "978-1", bib);
        Revista r1 = new Revista("National Geo", 2024, "NatGeo Press", 315,     bib);

        bib.listarElementos();

        bib.prestar(l1, "Mario Lopez");
        bib.prestar(r1, "Ana Garcia");

        System.out.println("Libros de Cervantes:");
        for (Libro l : bib.buscarPorAutor("Cervantes")) l.mostrar();

        System.out.println("Total elementos: "  + bib.totalElementos());
        System.out.println("Total prestamos: "  + bib.totalPrestamos());
        System.out.println("Elementos creados: " + Elemento.getContador());
    }
}""",
        S, title='Solucion: Sistema de Biblioteca')

    # ── Ejercicio 6.2 — Lambdas: Analisis de productos ──────────────────────
    story += [sp(12)]
    story += h2('6.2  Lambdas & Streams — Analisis de productos', S)
    story += [
        Paragraph('Ejercicio 6.2: Analisis de lista de productos', S['ex_title']),
        sp(2),
        Paragraph(
            'Dada una lista de Producto (nombre, precio, categoria, unidades), implementa con streams: '
            'a) listar los productos de categoria "Electronica" con precio > 100, ordenados por precio desc; '
            'b) calcular el precio medio de todos los productos; '
            'c) obtener el producto mas caro; '
            'd) contar cuantos productos tienen stock 0; '
            'e) obtener lista de nombres de todos los productos en mayusculas ordenados alfabeticamente.',
            S['ex_body']),
        sp(6),
    ]
    story += code_block(
        """import java.util.*;
import java.util.stream.*;

class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private int    unidades;

    public Producto(String nombre, double precio, String categoria, int unidades) {
        this.nombre    = nombre;
        this.precio    = precio;
        this.categoria = categoria;
        this.unidades  = unidades;
    }

    public String getNombre()    { return nombre; }
    public double getPrecio()    { return precio; }
    public String getCategoria() { return categoria; }
    public int    getUnidades()  { return unidades; }

    @Override
    public String toString() {
        return nombre + " (" + precio + "euro, " + categoria + ", stock: " + unidades + ")";
    }
}

public class MainProductos {
    public static void main(String[] args) {

        ArrayList<Producto> productos = new ArrayList<>(List.of(
            new Producto("Laptop",      999.99, "Electronica",  5),
            new Producto("Teclado",      49.99, "Electronica", 20),
            new Producto("Monitor",     299.99, "Electronica",  3),
            new Producto("Silla",       149.99, "Muebles",      8),
            new Producto("Mesa",        199.99, "Muebles",      0),
            new Producto("Auriculares", 129.99, "Electronica",  0),
            new Producto("Raton",        29.99, "Electronica", 15),
            new Producto("Lampara",      39.99, "Hogar",        6)
        ));

        // a) Electronica con precio > 100, ordenados por precio desc
        System.out.println("=== a) Electronica > 100 euros ===");
        productos.stream()
            .filter(p -> p.getCategoria().equals("Electronica") && p.getPrecio() > 100)
            .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
            .forEach(System.out::println);
        // -> Monitor (299.99), Auriculares (129.99), Laptop (999.99)... ordenados desc

        // b) Precio medio de todos
        OptionalDouble media = productos.stream()
            .mapToDouble(Producto::getPrecio)
            .average();
        media.ifPresent(m -> System.out.printf("%n=== b) Precio medio: %.2f euro ===%n", m));

        // c) Producto mas caro
        Optional<Producto> masCaro = productos.stream()
            .max(Comparator.comparingDouble(Producto::getPrecio));
        masCaro.ifPresent(p -> System.out.println("%n=== c) Mas caro: " + p));

        // d) Cuantos tienen stock 0
        long sinStock = productos.stream()
            .filter(p -> p.getUnidades() == 0)
            .count();
        System.out.println("%n=== d) Sin stock: " + sinStock + " productos ===");

        // e) Nombres en mayusculas ordenados
        System.out.println("%n=== e) Nombres ordenados ===");
        productos.stream()
            .map(p -> p.getNombre().toUpperCase())
            .sorted()
            .forEach(System.out::println);

        // BONUS: suma del valor total del inventario (precio * unidades)
        double valorTotal = productos.stream()
            .mapToDouble(p -> p.getPrecio() * p.getUnidades())
            .sum();
        System.out.printf("%nBonus - Valor inventario: %.2f euro%n", valorTotal);
    }
}""",
        S, title='Solucion: Analisis de productos con Streams')

    # ── Ejercicio 6.3 — BBDD avanzado ────────────────────────────────────────
    story += [sp(12)]
    story += h2('6.3  BBDD — Consultas avanzadas (classicmodels)', S)
    story += [
        Paragraph('Ejercicio 6.3: Consultas avanzadas JDBC', S['ex_title']),
        sp(2),
        Paragraph(
            'Implementa con JDBC sobre classicmodels: '
            'a) Mostrar los 5 productos mas vendidos (JOIN orderdetails + products, GROUP BY, ORDER BY SUM); '
            'b) Listar clientes que NO tienen ningun pedido (LEFT JOIN + WHERE IS NULL); '
            'c) Para cada oficina, mostrar cuantos empleados tiene; '
            'd) Buscar productos cuyo precio de compra supere la media.',
            S['ex_body']),
        sp(6),
    ]
    story += code_block(
        """import java.sql.*;

public class ConsultasAvanzadas {

    static final String SERVIDOR = "jdbc:mysql://localhost:3306/classicmodels";
    static final String USUARIO  = "admin";
    static final String PASSWORD = "1234";

    // a) 5 productos mas vendidos (SUM de quantityOrdered)
    public static void top5Productos(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery(
            "SELECT p.productName, SUM(od.quantityOrdered) AS totalVendido " +
            "FROM products p " +
            "JOIN orderdetails od ON p.productCode = od.productCode " +
            "GROUP BY p.productCode, p.productName " +
            "ORDER BY totalVendido DESC " +
            "LIMIT 5"
        );
        System.out.println("=== Top 5 productos mas vendidos ===");
        while (rs.next()) {
            System.out.printf("  %-40s -> %d unidades%n",
                rs.getString("productName"),
                rs.getInt("totalVendido"));
        }
    }

    // b) Clientes sin ningun pedido (LEFT JOIN + IS NULL)
    public static void clientesSinPedidos(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery(
            "SELECT c.customerName " +
            "FROM customers c " +
            "LEFT JOIN orders o ON c.customerNumber = o.customerNumber " +
            "WHERE o.orderNumber IS NULL " +
            "ORDER BY c.customerName"
        );
        System.out.println("%n=== Clientes sin pedidos ===");
        int count = 0;
        while (rs.next()) {
            System.out.println("  " + rs.getString("customerName"));
            count++;
        }
        System.out.println("  Total: " + count);
    }

    // c) Empleados por oficina (GROUP BY)
    public static void empleadosPorOficina(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery(
            "SELECT officeCode, COUNT(*) AS numEmpleados " +
            "FROM employees " +
            "GROUP BY officeCode " +
            "ORDER BY numEmpleados DESC"
        );
        System.out.println("%n=== Empleados por oficina ===");
        while (rs.next()) {
            System.out.printf("  Oficina %s: %d empleados%n",
                rs.getString("officeCode"),
                rs.getInt("numEmpleados"));
        }
    }

    // d) Productos con precio de compra sobre la media (subconsulta SQL)
    public static void productosSobreMedia(Connection cnx) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs   = stmt.executeQuery(
            "SELECT productName, buyPrice " +
            "FROM products " +
            "WHERE buyPrice > (SELECT AVG(buyPrice) FROM products) " +
            "ORDER BY buyPrice DESC"
        );
        System.out.println("%n=== Productos sobre la media de precio ===");
        while (rs.next()) {
            System.out.printf("  %-40s %.2f%n",
                rs.getString("productName"),
                rs.getDouble("buyPrice"));
        }
    }

    public static void main(String[] args) {
        try (Connection cnx = DriverManager.getConnection(SERVIDOR, USUARIO, PASSWORD)) {
            System.out.println("Conexion OK");
            top5Productos(cnx);
            clientesSinPedidos(cnx);
            empleadosPorOficina(cnx);
            productosSobreMedia(cnx);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}""",
        S, title='Solucion: Consultas avanzadas JDBC + classicmodels')

    # ── Resumen rapido final ─────────────────────────────────────────────────
    story += [sp(12)]
    story += h1('RESUMEN RAPIDO — COSAS QUE NO DEBES OLVIDAR', S)
    story += [
        body('<b>POO:</b>', S),
        bul('super() SIEMPRE primera instruccion en el constructor hijo', S),
        bul('abstract solo si la clase padre NO se instancia directamente', S),
        bul('protected permite acceso desde subclases sin getter', S),
        bul('instanceof + cast para acceder a metodos exclusivos de la hija', S),
        bul('toString() para que println(objeto) funcione bien', S),
        bul('equals() para que lista.contains() y lista.remove() funcionen bien', S),
        sp(6),
        body('<b>Lambdas y Streams:</b>', S),
        bul('@FunctionalInterface: exactamente UN metodo abstracto', S),
        bul('Pipeline = stream() + intermedias (lazy) + terminal (ejecuta)', S),
        bul('collect(Collectors.toList()) devuelve ArrayList', S),
        bul('count() devuelve long, no int', S),
        bul('max/min devuelven Optional<T> — usar .ifPresent() o .get()', S),
        bul('mapToDouble(...).average() devuelve OptionalDouble', S),
        sp(6),
        body('<b>JDBC:</b>', S),
        bul('try-with-resources cierra la conexion aunque haya excepcion', S),
        bul('TYPE_SCROLL_INSENSITIVE necesario para last(), previous(), etc.', S),
        bul('isBeforeFirst() = true si el ResultSet esta vacio', S),
        bul('COUNT(*) siempre devuelve 1 fila: usar rs.next() una sola vez', S),
        bul('CRUD usa executeUpdate(), no executeQuery()', S),
        bul('Pasar Connection como parametro para no abrir N conexiones', S),
        bul('Cerrar rs2.close() dentro del while de la consulta exterior', S),
        sp(12),
        HRFlowable(width='100%', thickness=1.5, color=C_DARK_BLUE, spaceAfter=8),
        Paragraph('Suerte en el examen — Alexandru Iacob | DAM 2025-2026', S['cov_info']),
    ]

    # ── Build ────────────────────────────────────────────────────────────────
    doc.build(story)
    print(f"PDF generado correctamente: {OUT}")
    return OUT


if __name__ == '__main__':
    output = build_pdf()
    print(f"Archivo: {output}")
