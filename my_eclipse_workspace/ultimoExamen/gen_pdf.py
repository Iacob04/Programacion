"""Genera PLAN_DE_ESTUDIO_6H.pdf para el examen final Java de Gabriel."""

from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import cm, mm
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle,
    HRFlowable, KeepTogether
)
from reportlab.lib.enums import TA_CENTER, TA_LEFT, TA_JUSTIFY
from reportlab.platypus import PageBreak
import os

OUTPUT = os.path.join(os.path.dirname(__file__), "PLAN_DE_ESTUDIO_6H.pdf")

# ── Colores corporativos ────────────────────────────────────────────────────
AZUL_OSCURO   = colors.HexColor("#1A3A5C")
AZUL_MEDIO    = colors.HexColor("#2563EB")
AZUL_CLARO    = colors.HexColor("#DBEAFE")
VERDE_OSCURO  = colors.HexColor("#14532D")
VERDE_CLARO   = colors.HexColor("#DCFCE7")
NARANJA       = colors.HexColor("#EA580C")
NARANJA_CLARO = colors.HexColor("#FEF3C7")
ROJO_CLARO    = colors.HexColor("#FEE2E2")
ROJO_OSCURO   = colors.HexColor("#991B1B")
GRIS_CLARO    = colors.HexColor("#F1F5F9")
GRIS_MEDIO    = colors.HexColor("#94A3B8")
NEGRO         = colors.HexColor("#0F172A")
BLANCO        = colors.white
MORADO        = colors.HexColor("#7C3AED")
MORADO_CLARO  = colors.HexColor("#EDE9FE")

# ── Estilos ─────────────────────────────────────────────────────────────────
base = getSampleStyleSheet()

def style(name, parent="Normal", **kw):
    s = ParagraphStyle(name, parent=base[parent], **kw)
    return s

TITULO = style("Titulo", "Title",
    fontSize=26, textColor=BLANCO, alignment=TA_CENTER,
    spaceAfter=4, fontName="Helvetica-Bold")

SUBTITULO = style("Subtitulo", "Normal",
    fontSize=13, textColor=BLANCO, alignment=TA_CENTER,
    spaceAfter=2, fontName="Helvetica")

BLOQUE_HEADER = style("BloqueHeader", "Normal",
    fontSize=15, textColor=BLANCO, fontName="Helvetica-Bold",
    spaceAfter=6, spaceBefore=4, leftIndent=0)

SECCION = style("Seccion", "Normal",
    fontSize=11, textColor=AZUL_OSCURO, fontName="Helvetica-Bold",
    spaceBefore=10, spaceAfter=4)

CUERPO = style("Cuerpo", "Normal",
    fontSize=9.5, textColor=NEGRO, fontName="Helvetica",
    spaceBefore=2, spaceAfter=2, leading=13)

BULLET = style("Bullet", "Normal",
    fontSize=9.5, textColor=NEGRO, fontName="Helvetica",
    leftIndent=14, firstLineIndent=-8, spaceBefore=1, spaceAfter=1, leading=12)

EJERCICIO_TITLE = style("EjercicioTitle", "Normal",
    fontSize=10, textColor=AZUL_MEDIO, fontName="Helvetica-Bold",
    spaceBefore=6, spaceAfter=2)

EJERCICIO_BODY = style("EjercicioBody", "Normal",
    fontSize=9, textColor=NEGRO, fontName="Helvetica",
    leftIndent=8, spaceBefore=1, spaceAfter=1, leading=12)

CODE = style("Code", "Code",
    fontSize=7.8, textColor=NEGRO, fontName="Courier",
    backColor=GRIS_CLARO, leftIndent=8, rightIndent=4,
    spaceBefore=3, spaceAfter=3, leading=11,
    borderPadding=(3, 4, 3, 4))

TIP_STYLE = style("Tip", "Normal",
    fontSize=9, textColor=VERDE_OSCURO, fontName="Helvetica",
    leftIndent=12, spaceBefore=2, spaceAfter=1, leading=12)

# ── Helpers ──────────────────────────────────────────────────────────────────
def hr(color=GRIS_MEDIO, thickness=0.5):
    return HRFlowable(width="100%", thickness=thickness, color=color,
                      spaceAfter=4, spaceBefore=4)

def header_table(text, bg_color, text_color=BLANCO):
    """Cabecera de bloque con fondo coloreado."""
    data = [[Paragraph(text, ParagraphStyle("H", fontName="Helvetica-Bold",
                        fontSize=14, textColor=text_color, alignment=TA_LEFT))]]
    t = Table(data, colWidths=["100%"])
    t.setStyle(TableStyle([
        ("BACKGROUND", (0,0), (-1,-1), bg_color),
        ("TOPPADDING",    (0,0), (-1,-1), 8),
        ("BOTTOMPADDING", (0,0), (-1,-1), 8),
        ("LEFTPADDING",   (0,0), (-1,-1), 12),
        ("RIGHTPADDING",  (0,0), (-1,-1), 12),
        ("ROUNDEDCORNERS", [4,4,4,4]),
    ]))
    return t

def teoria_box(items, bg=AZUL_CLARO, border=AZUL_MEDIO):
    """Caja de teoría clave."""
    content = [Paragraph("&#x25B6; TEORÍA CLAVE",
                          ParagraphStyle("TH", fontName="Helvetica-Bold",
                                         fontSize=10, textColor=AZUL_OSCURO))]
    for item in items:
        content.append(Paragraph("• " + item, BULLET))
    data = [[content]]
    t = Table(data, colWidths=["100%"])
    t.setStyle(TableStyle([
        ("BACKGROUND", (0,0), (-1,-1), bg),
        ("BOX",        (0,0), (-1,-1), 1, border),
        ("TOPPADDING",    (0,0), (-1,-1), 8),
        ("BOTTOMPADDING", (0,0), (-1,-1), 8),
        ("LEFTPADDING",   (0,0), (-1,-1), 10),
        ("RIGHTPADDING",  (0,0), (-1,-1), 10),
    ]))
    return t

def ejercicio_box(numero, titulo, lineas, color=NARANJA_CLARO, border=NARANJA):
    """Caja de ejercicio propuesto."""
    header_style = ParagraphStyle("EH", fontName="Helvetica-Bold",
                                  fontSize=9.5, textColor=NARANJA)
    body_style   = ParagraphStyle("EB", fontName="Helvetica",
                                  fontSize=9, textColor=NEGRO, leading=12)
    content = [Paragraph(f"&#x2022; {numero} — {titulo}", header_style)]
    for linea in lineas:
        prefix = "    " if linea.startswith(("a)", "b)", "c)", "d)")) else ""
        content.append(Paragraph(prefix + linea, body_style))
    data = [[content]]
    t = Table(data, colWidths=["100%"])
    t.setStyle(TableStyle([
        ("BACKGROUND", (0,0), (-1,-1), color),
        ("BOX",        (0,0), (-1,-1), 0.7, border),
        ("LEFTLINE",   (0,0), (0,-1),  3,   border),
        ("TOPPADDING",    (0,0), (-1,-1), 6),
        ("BOTTOMPADDING", (0,0), (-1,-1), 6),
        ("LEFTPADDING",   (0,0), (-1,-1), 10),
        ("RIGHTPADDING",  (0,0), (-1,-1), 10),
    ]))
    return t

def code_box(lines):
    """Bloque de código."""
    txt = "<br/>".join(
        l.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
        for l in lines
    )
    return Paragraph(txt, CODE)

def sp(n=6):
    return Spacer(1, n)

# ── Construcción del documento ───────────────────────────────────────────────
def build_pdf():
    doc = SimpleDocTemplate(
        OUTPUT,
        pagesize=A4,
        leftMargin=1.8*cm, rightMargin=1.8*cm,
        topMargin=2*cm,    bottomMargin=2*cm,
    )
    W = A4[0] - 3.6*cm   # ancho disponible

    story = []

    # ══════════════════════════════════════════════════════════════════
    # PORTADA
    # ══════════════════════════════════════════════════════════════════
    portada_data = [[
        Paragraph("PLAN DE ESTUDIO", ParagraphStyle("P1", fontName="Helvetica-Bold",
                   fontSize=28, textColor=BLANCO, alignment=TA_CENTER, leading=32)),
        Paragraph("EXAMEN FINAL JAVA — 6 HORAS", ParagraphStyle("P2", fontName="Helvetica-Bold",
                   fontSize=18, textColor=colors.HexColor("#BFDBFE"), alignment=TA_CENTER, leading=22)),
        Paragraph("Gabriel Alexandru Iacob &nbsp;|&nbsp; DAM FP Superior &nbsp;|&nbsp; 3ª Evaluación",
                   ParagraphStyle("P3", fontName="Helvetica", fontSize=11,
                                  textColor=colors.HexColor("#E0F2FE"), alignment=TA_CENTER)),
    ]]
    # Empaquetar en una tabla para fondo azul
    portada_inner = [
        Paragraph("PLAN DE ESTUDIO", ParagraphStyle("P1", fontName="Helvetica-Bold",
                   fontSize=28, textColor=BLANCO, alignment=TA_CENTER, leading=32, spaceAfter=6)),
        Paragraph("EXAMEN FINAL JAVA — 6 HORAS", ParagraphStyle("P2", fontName="Helvetica-Bold",
                   fontSize=18, textColor=colors.HexColor("#BFDBFE"), alignment=TA_CENTER, leading=22, spaceAfter=6)),
        Paragraph("Gabriel Alexandru Iacob  |  DAM FP Superior  |  3ª Evaluación",
                   ParagraphStyle("P3", fontName="Helvetica", fontSize=11,
                                  textColor=colors.HexColor("#E0F2FE"), alignment=TA_CENTER)),
    ]
    portada_t = Table([[portada_inner]], colWidths=[W])
    portada_t.setStyle(TableStyle([
        ("BACKGROUND",    (0,0), (-1,-1), AZUL_OSCURO),
        ("TOPPADDING",    (0,0), (-1,-1), 24),
        ("BOTTOMPADDING", (0,0), (-1,-1), 24),
        ("LEFTPADDING",   (0,0), (-1,-1), 16),
        ("RIGHTPADDING",  (0,0), (-1,-1), 16),
    ]))
    story.append(portada_t)
    story.append(sp(12))

    # ── Tabla resumen de bloques ──────────────────────────────────────
    resumen_header = [
        Paragraph("BLOQUE", ParagraphStyle("RH", fontName="Helvetica-Bold",
                             fontSize=10, textColor=BLANCO, alignment=TA_CENTER)),
        Paragraph("TEMA", ParagraphStyle("RH", fontName="Helvetica-Bold",
                          fontSize=10, textColor=BLANCO, alignment=TA_CENTER)),
        Paragraph("TIEMPO", ParagraphStyle("RH", fontName="Helvetica-Bold",
                             fontSize=10, textColor=BLANCO, alignment=TA_CENTER)),
        Paragraph("PRIORIDAD", ParagraphStyle("RH", fontName="Helvetica-Bold",
                               fontSize=10, textColor=BLANCO, alignment=TA_CENTER)),
    ]
    cs = ParagraphStyle("CS", fontName="Helvetica", fontSize=9.5, alignment=TA_CENTER)
    resumen_rows = [
        resumen_header,
        [Paragraph("1", cs), Paragraph("Bases de Datos JDBC", cs),
         Paragraph("2 h", cs), Paragraph("ALTA", ParagraphStyle("P", fontName="Helvetica-Bold", fontSize=9.5, textColor=NARANJA, alignment=TA_CENTER))],
        [Paragraph("2", cs), Paragraph("Funciones Lambda + Streams", cs),
         Paragraph("1 h 30 min", cs), Paragraph("ALTA", ParagraphStyle("P", fontName="Helvetica-Bold", fontSize=9.5, textColor=NARANJA, alignment=TA_CENTER))],
        [Paragraph("3", cs), Paragraph("OOP Final de Curso", cs),
         Paragraph("1 h 30 min", cs), Paragraph("MUY ALTA", ParagraphStyle("P", fontName="Helvetica-Bold", fontSize=9.5, textColor=ROJO_OSCURO, alignment=TA_CENTER))],
        [Paragraph("4", cs), Paragraph("Gestión de Persona 3ª Ev.", cs),
         Paragraph("1 h", cs), Paragraph("MUY ALTA", ParagraphStyle("P", fontName="Helvetica-Bold", fontSize=9.5, textColor=ROJO_OSCURO, alignment=TA_CENTER))],
        [Paragraph("TOTAL", ParagraphStyle("PT", fontName="Helvetica-Bold", fontSize=9.5, alignment=TA_CENTER)),
         Paragraph("", cs), Paragraph("6 h", ParagraphStyle("PT", fontName="Helvetica-Bold", fontSize=9.5, alignment=TA_CENTER)),
         Paragraph("", cs)],
    ]
    col_w = [W*0.08, W*0.44, W*0.22, W*0.26]
    resumen_t = Table(resumen_rows, colWidths=col_w)
    resumen_t.setStyle(TableStyle([
        ("BACKGROUND",    (0,0), (-1,0),  AZUL_OSCURO),
        ("BACKGROUND",    (0,1), (-1,1),  AZUL_CLARO),
        ("BACKGROUND",    (0,2), (-1,2),  GRIS_CLARO),
        ("BACKGROUND",    (0,3), (-1,3),  MORADO_CLARO),
        ("BACKGROUND",    (0,4), (-1,4),  ROJO_CLARO),
        ("BACKGROUND",    (0,5), (-1,5),  GRIS_CLARO),
        ("GRID",          (0,0), (-1,-1), 0.5, GRIS_MEDIO),
        ("TOPPADDING",    (0,0), (-1,-1), 6),
        ("BOTTOMPADDING", (0,0), (-1,-1), 6),
        ("VALIGN",        (0,0), (-1,-1), "MIDDLE"),
    ]))
    story.append(resumen_t)
    story.append(sp(16))

    # ══════════════════════════════════════════════════════════════════
    # BLOQUE 1 — BASES DE DATOS
    # ══════════════════════════════════════════════════════════════════
    story.append(header_table("BLOQUE 1 — BASES DE DATOS JDBC   ⏱ 2 horas", AZUL_OSCURO))
    story.append(sp(8))

    story.append(teoria_box([
        "Conexión: DriverManager.getConnection(servidor, usuario, password)",
        "try-with-resources → cierra la conexión sola aunque haya excepción",
        "Statement → consulta fija sin parámetros",
        "PreparedStatement → usa ? como parámetros (setString, setInt...) · evita SQL injection",
        "TYPE_SCROLL_INSENSITIVE + CONCUR_READ_ONLY → para navegar libremente el ResultSet",
        "next() → avanza   |   previous() → retrocede   |   last()+getRow() → contar filas",
        "afterLast()+previous() → recorrer en orden inverso",
        "isBeforeFirst() == true → ResultSet vacío (ningún resultado)",
        "executeQuery() → SELECT   |   executeUpdate() → INSERT, UPDATE, DELETE",
    ]))
    story.append(sp(6))

    story.append(code_box([
        "// PLANTILLA MÍNIMA — copia siempre esto primero",
        'String URL  = "jdbc:mysql://localhost:3306/classicmodels";',
        'String USER = "admin";  String PASS = "1234";',
        "",
        "try (Connection cnx = DriverManager.getConnection(URL, USER, PASS)) {",
        "    PreparedStatement ps = cnx.prepareStatement(",
        '        "SELECT firstName, lastName FROM employees WHERE lastName = ?");',
        '    ps.setString(1, "Patterson");',
        "    ResultSet rs = ps.executeQuery();",
        "    while (rs.next()) {",
        '        System.out.println(rs.getString("firstName"));',
        "    }",
        "} catch (SQLException e) { System.out.println(e.getMessage()); }",
    ]))
    story.append(sp(8))

    story.append(Paragraph("EJERCICIOS PROPUESTOS", SECCION))
    story.append(hr(AZUL_MEDIO, 0.8))
    story.append(sp(4))

    story.append(ejercicio_box("Ejercicio B1", "Empleados por oficina",
        ["Conecta a classicmodels. Muestra todos los empleados con su officeCode y",
         "jobTitle, ordenados por officeCode (ORDER BY en SQL)."],
        AZUL_CLARO, AZUL_MEDIO))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio B2 ★", "Jefe → subordinados (patrón anidado)",
        ["Dado un apellido por parámetro, busca al empleado en employees.",
         "• Si NO existe → mensaje de error (usa isBeforeFirst).",
         "• Si existe → muestra su número y todos los que le reportan (reportsTo).",
         "• Si nadie le reporta → indícalo también.",
         "PISTA: 2 PreparedStatements, el 2º dentro del while del 1º."],
        NARANJA_CLARO, NARANJA))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio B3", "Top-5 productos más caros + pedidos",
        ["Muestra los 5 productos con mayor buyPrice (ORDER BY buyPrice DESC LIMIT 5).",
         "Para cada uno, usa COUNT(*) en orderdetails para ver cuántas veces fue pedido."],
        AZUL_CLARO, AZUL_MEDIO))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio B4 ★", "Contar actores con TYPE_SCROLL (sakila)",
        ["Conecta a sakila. Pide nombre de actor por Scanner.",
         "Con TYPE_SCROLL_INSENSITIVE: usa last()+getRow() para contar sin while.",
         "Luego llama beforeFirst() y recorre todos mostrando nombre y apellido."],
        NARANJA_CLARO, NARANJA))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio B5 ★★", "Oficinas + empleados por oficina",
        ["Muestra todas las oficinas. Para cada una, ejecuta:",
         "SELECT COUNT(*) FROM employees WHERE officeCode = ?",
         "Muestra el nombre de la ciudad y cuántos empleados tiene."],
        ROJO_CLARO, ROJO_OSCURO))
    story.append(sp(14))

    # ══════════════════════════════════════════════════════════════════
    # BLOQUE 2 — LAMBDA
    # ══════════════════════════════════════════════════════════════════
    story.append(header_table("BLOQUE 2 — FUNCIONES LAMBDA &amp; STREAMS   ⏱ 1h 30min", MORADO))
    story.append(sp(8))

    story.append(teoria_box([
        "Sintaxis: (params) -> expresión   ó   (params) -> { bloque; return ...; }",
        "@FunctionalInterface → interfaz con exactamente 1 método abstracto",
        "Interfaces estándar: Runnable, Consumer<T>, Supplier<T>, Function<T,R>, Predicate<T>",
        "ArrayList.sort((a,b) -> a.compareTo(b)) → ordena con lambda",
        "Comparator.comparing(Clase::getter) → ordena por campo de objeto",
        "lista.forEach(item -> ...) → recorre con lambda",
        "Streams: .stream() .filter(p) .map(f) .sorted() .collect(Collectors.toList())",
        "Terminales: .count() .max() .min() .anyMatch() .allMatch() .findFirst()",
        "mapToInt(Clase::getter).sum() / .average() → operaciones numéricas",
    ], MORADO_CLARO, MORADO))
    story.append(sp(6))

    story.append(code_box([
        "// Interfaz propia",
        "@FunctionalInterface interface Operacion { int calcular(int a, int b); }",
        "Operacion suma = (a, b) -> a + b;",
        "System.out.println(suma.calcular(5, 3));  // → 8",
        "",
        "// Stream completo",
        "List<String> resultado = nombres.stream()",
        '    .filter(n -> n.length() > 4)',
        "    .map(String::toUpperCase)",
        "    .sorted()",
        "    .collect(Collectors.toList());",
        "",
        "long cuantos = nums.stream().filter(n -> n > 5).count();",
        "Optional<Integer> max = nums.stream().max(Comparator.naturalOrder());",
        "max.ifPresent(m -> System.out.println(m));",
    ]))
    story.append(sp(8))

    story.append(Paragraph("EJERCICIOS PROPUESTOS", SECCION))
    story.append(hr(MORADO, 0.8))
    story.append(sp(4))

    story.append(ejercicio_box("Ejercicio L1", "Interfaz funcional propia",
        ["Crea @FunctionalInterface Calculadora con: double operar(double a, double b).",
         "Implementa 4 lambdas: suma, resta, multiplicación y división.",
         "Método estático ejecutar(double a, double b, Calculadora op) que las use.",
         "Pruébalo con los 4 operadores."],
        MORADO_CLARO, MORADO))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio L2 ★", "Streams con Strings",
        ["ArrayList de nombres de ciudades. Usa streams para:",
         "a) Las que tienen más de 6 letras, en mayúsculas, ordenadas alfabéticamente.",
         "b) Cuántas empiezan por vocal (A, E, I, O, U).",
         "c) El nombre más corto.",
         "d) Si TODAS tienen más de 2 letras (allMatch)."],
        NARANJA_CLARO, NARANJA))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio L3 ★★", "Streams con objetos propios",
        ["Clase Producto (nombre, precio: double, categoria: String). ArrayList de productos.",
         "a) Filtra precio > 100, ordena por precio descendente.",
         "b) Nombres de la categoria 'Electronica'.",
         "c) Media de precios de todos (mapToDouble + average).",
         "d) Ordena por nombre: Comparator.comparing(Producto::getNombre)."],
        NARANJA_CLARO, NARANJA))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio L4", "Comparator con lambda",
        ["Lista de palabras. Ordena con 3 criterios distintos:",
         "a) Por longitud ascendente: (a,b) -> a.length() - b.length()",
         "b) Por longitud descendente: .reversed()",
         "c) Alfabético ignorando mayúsculas: (a,b) -> a.compareToIgnoreCase(b)"],
        MORADO_CLARO, MORADO))
    story.append(sp(14))

    # ══════════════════════════════════════════════════════════════════
    # BLOQUE 3 — OOP
    # ══════════════════════════════════════════════════════════════════
    story.append(header_table("BLOQUE 3 — ORIENTACIÓN A OBJETOS FINAL DE CURSO   ⏱ 1h 30min", VERDE_OSCURO))
    story.append(sp(8))

    story.append(teoria_box([
        "abstract class Padre { protected campo; public abstract void metodo(); }",
        "protected → visible en la clase hija (NO en clases externas)",
        "class Hija extends Padre → hereda todo lo del padre",
        "super(params) → llama al constructor del padre, SIEMPRE la 1ª línea",
        "@Override → sobreescribir método del padre",
        "Patrón examen: en el constructor del hijo → padre.anyadir(this)",
        "ArrayList<T>: add(), remove(), get(i), size(), contains(), isEmpty()",
        "static → pertenece a la clase, no al objeto. Acceso: NombreClase.campo",
        "Polimorfismo: ArrayList<Padre> guarda hijos mezclados; cada uno ejecuta SU método",
    ], VERDE_CLARO, VERDE_OSCURO))
    story.append(sp(6))

    story.append(code_box([
        "abstract class Persona {",
        "    protected String nombre;",
        "    public Persona(String n) { this.nombre = n; }",
        "    public abstract void mostrar();          // hijas DEBEN implementarlo",
        "    public String getNombre() { return nombre; }",
        "}",
        "class Medico extends Persona {",
        "    private String especialidad;",
        "    public Medico(String n, String esp, Centro c) {",
        "        super(n);                            // 1ª línea siempre",
        "        this.especialidad = esp;",
        "        c.anyadirMedico(this);               // auto-registro",
        "    }",
        "    @Override public void mostrar() {",
        '        System.out.println("Dr. "+getNombre()+" - "+especialidad);',
        "    }",
        "}",
    ]))
    story.append(sp(8))

    story.append(Paragraph("EJERCICIOS PROPUESTOS", SECCION))
    story.append(hr(VERDE_OSCURO, 0.8))
    story.append(sp(4))

    story.append(ejercicio_box("Ejercicio O1", "Sistema Aeropuerto",
        ["Personal (abstract: nombre, apellidos, DNI, salario)",
         "→ Piloto (+ horasVuelo, tipoLicencia)",
         "→ AuxiliarVuelo (+ idiomas: ArrayList<String>, clase asignada)",
         "Clase Vuelo con ArrayList<Personal> y métodos anyadirTripulacion(Personal p), listarTripulacion().",
         "Main: 2 pilotos, 3 auxiliares, 2 vuelos (1 piloto + 2 auxiliares cada uno)."],
        VERDE_CLARO, VERDE_OSCURO))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio O2 ★", "Festival de Música",
        ["Artista (abstract: nombre, genero) → Cantante (+ numAlbumes) + GrupoMusica (+ numComponentes).",
         "Clase Caseta con ArrayList<Artista>; métodos: listarArtistas(), contarArtistas().",
         "CLAVE: en el constructor de Cantante y GrupoMusica → caseta.add(this).",
         "Main: 3 casetas, 2 cantantes y 2 grupos por caseta. Listar todo."],
        NARANJA_CLARO, NARANJA))
    story.append(sp(5))

    story.append(ejercicio_box("Ejercicio O3 ★★", "Banco con polimorfismo",
        ["Persona (abstract: nombre, DNI) → Cliente (+ saldo, ArrayList<Cuenta>) + Empleado (+ depto).",
         "Clase Banco: ArrayList<Cliente>, ArrayList<Empleado>.",
         "Métodos: anyadirCliente(), eliminarCliente(), buscarPorDNI(), totalClientes().",
         "POLIMORFISMO: ArrayList<Persona> mezclando clientes y empleados.",
         "Recórrelo con for-each llamando a mostrar() → cada tipo ejecuta el suyo."],
        ROJO_CLARO, ROJO_OSCURO))
    story.append(sp(14))

    # ══════════════════════════════════════════════════════════════════
    # BLOQUE 4 — GESTIÓN PERSONA
    # ══════════════════════════════════════════════════════════════════
    story.append(header_table("BLOQUE 4 — GESTIÓN DE PERSONA 3ª EVALUACIÓN   ⏱ 1 hora", NARANJA))
    story.append(sp(8))

    story.append(teoria_box([
        "Sistema completo: Persona (abstract) + subclases + clase gestora/centro",
        "Auto-registro: en el constructor del hijo → centro.anyadir(this)",
        "Cambio de centro: this.centro.eliminar(this) → this.centro = nuevo → nuevo.anyadir(this)",
        "Consulta/Cita: referencias cruzadas — tiene ref a Medico + ref a Paciente",
        "Al crear Consulta → se registra en: el medico, el paciente Y el centro",
        "LocalDate.now() → hoy   |   LocalDate.of(2026, 5, 15) → fecha concreta",
        "abstract void cambioCentro() → cada subclase implementa su lógica de cambio",
    ], NARANJA_CLARO, NARANJA))
    story.append(sp(6))

    story.append(code_box([
        "// Patrón consulta completa",
        "class Consulta {",
        "    public Consulta(Paciente p, Medico m, LocalDate f, String sint) {",
        "        this.paciente = p; this.medico = m; this.fecha = f;",
        "        // auto-registro en los tres sitios:",
        "        m.getCentro().anyadirConsulta(this);",
        "        p.anyadirConsulta(this);",
        "        m.anyadirConsulta(this);",
        "    }",
        "}",
    ]))
    story.append(sp(8))

    story.append(Paragraph("EJERCICIO FINAL INTEGRADOR", SECCION))
    story.append(hr(NARANJA, 0.8))
    story.append(sp(4))

    story.append(ejercicio_box("Ejercicio GF ★★★", "Centro Veterinario (ejercicio completo)",
        ["CLASES:",
         "Animal (abstract): nombre, especie, edad, dueno (ref a Cliente). Metodo abstracto: describir().",
         "Perro extends Animal: raza, tieneMicrochip. describir() → 'Perro: [nombre], raza X, microchip: si/no'.",
         "Gato extends Animal: esEsterilizado. describir() → 'Gato: [nombre], esterilizado: si/no'.",
         "Cliente: nombre, telefono, ArrayList<Animal> animales. Metodos: anyadirAnimal(Animal a), listarAnimales().",
         "Veterinario: nombre, especialidad, ArrayList<Cita> citas.",
         "Cita: Animal, Veterinario, LocalDate, motivo. Al crear Cita → vet.anyadirCita(this).",
         "CentroVet: ArrayList<Cliente>, ArrayList<Veterinario>. Metodos: anyadirCliente, listar, buscar.",
         "",
         "MAIN:",
         "1 centro 'PetCare', 2 veterinarios (cirugia + medicina general).",
         "3 clientes con 1-2 animales mezclados (Perros y Gatos).",
         "4 citas auto-registradas. Mostrar clientes con sus animales y citas de cada vet."],
        ROJO_CLARO, ROJO_OSCURO))
    story.append(sp(14))

    # ══════════════════════════════════════════════════════════════════
    # TIPS PARA EL EXAMEN
    # ══════════════════════════════════════════════════════════════════
    story.append(header_table("TIPS DEFINITIVOS PARA EL EXAMEN", NEGRO))
    story.append(sp(8))

    tips_data = [
        ["#", "CONSEJO", "TEMA"],
        ["1",  "Dibuja primero la jerarquía en papel: quién hereda de quién.", "OOP"],
        ["2",  "protected en el padre, NO private. Las hijas no pueden ver private.", "OOP"],
        ["3",  "super() SIEMPRE primera línea del constructor hijo.", "OOP"],
        ["4",  "En el constructor del hijo → gestor.anyadir(this).", "OOP"],
        ["5",  "try-with-resources para Connection. Cierra solo.", "JDBC"],
        ["6",  "PreparedStatement si hay ?, Statement si la query es fija.", "JDBC"],
        ["7",  "TYPE_SCROLL + last() + getRow() = contar sin contador manual.", "JDBC"],
        ["8",  "isBeforeFirst() == true → ResultSet vacío. Comprueba ANTES del while.", "JDBC"],
        ["9",  "COUNT(*) devuelve siempre 1 fila → usa rs.next() una sola vez.", "JDBC"],
        ["10", "Lambda sin {} ni return si es 1 sola expresión.", "Lambda"],
        ["11", "Stream siempre termina con operación terminal (.collect, .count...).", "Lambda"],
        ["12", "Comparator.comparing(Clase::getter) para ordenar objetos por campo.", "Lambda"],
        ["13", "Optional: usa .ifPresent(x -> ...) en vez de .get() directo.", "Lambda"],
    ]
    ts = ParagraphStyle("TS", fontName="Helvetica", fontSize=9, leading=12)
    tb = ParagraphStyle("TB", fontName="Helvetica-Bold", fontSize=9, textColor=AZUL_OSCURO, leading=12)
    tips_rows = [[
        Paragraph(row[0], ParagraphStyle("TN", fontName="Helvetica-Bold", fontSize=9,
                                         textColor=BLANCO, alignment=TA_CENTER)),
        Paragraph(row[1], ts if i > 0 else ParagraphStyle("TH", fontName="Helvetica-Bold",
                                                            fontSize=9.5, textColor=BLANCO)),
        Paragraph(row[2], ParagraphStyle("TT", fontName="Helvetica-Bold", fontSize=9,
                                         textColor=AZUL_OSCURO if i > 0 else BLANCO,
                                         alignment=TA_CENTER)),
    ] for i, row in enumerate(tips_data)]

    tips_t = Table(tips_rows, colWidths=[W*0.05, W*0.77, W*0.18])
    tips_ts = TableStyle([
        ("BACKGROUND",    (0,0), (-1,0),   AZUL_OSCURO),
        ("GRID",          (0,0), (-1,-1),  0.4, GRIS_MEDIO),
        ("TOPPADDING",    (0,0), (-1,-1),  5),
        ("BOTTOMPADDING", (0,0), (-1,-1),  5),
        ("LEFTPADDING",   (0,0), (-1,-1),  6),
        ("VALIGN",        (0,0), (-1,-1),  "MIDDLE"),
    ])
    # Colorear filas alternas
    for i in range(1, len(tips_rows)):
        bg = GRIS_CLARO if i % 2 == 0 else BLANCO
        tips_ts.add("BACKGROUND", (0,i), (-1,i), bg)
    # Colorear badges de tema
    colores_tema = {
        "OOP":   (VERDE_CLARO, VERDE_OSCURO),
        "JDBC":  (AZUL_CLARO,  AZUL_MEDIO),
        "Lambda":(MORADO_CLARO, MORADO),
    }
    for i, row in enumerate(tips_data[1:], start=1):
        tema = row[2]
        if tema in colores_tema:
            bg, fg = colores_tema[tema]
            tips_ts.add("BACKGROUND", (2,i), (2,i), bg)
    tips_t.setStyle(tips_ts)
    story.append(tips_t)
    story.append(sp(10))

    # ── Firma ──────────────────────────────────────────────────────────
    firma_data = [[
        Paragraph("Generado con Claude Code · Anthropic  |  Examen Final Java DAM 3ª Evaluación",
                  ParagraphStyle("F", fontName="Helvetica", fontSize=8,
                                 textColor=GRIS_MEDIO, alignment=TA_CENTER))
    ]]
    firma_t = Table(firma_data, colWidths=[W])
    firma_t.setStyle(TableStyle([
        ("TOPPADDING",    (0,0), (-1,-1), 6),
        ("BOTTOMPADDING", (0,0), (-1,-1), 6),
    ]))
    story.append(hr())
    story.append(firma_t)

    # ── Build ─────────────────────────────────────────────────────────
    doc.build(story)
    print(f"PDF generado: {OUTPUT}")

if __name__ == "__main__":
    build_pdf()
