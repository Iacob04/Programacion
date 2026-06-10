import SwiftUI
import SwiftData
import AVFoundation

// MARK: - Flujo completo: escanear → buscar producto → indicar cantidad → guardar

struct BarcodeScanFlowView: View {
    @Environment(\.dismiss) private var cerrar
    @Environment(\.modelContext) private var contexto

    @State private var producto: ProductoEscaneado?
    @State private var buscando = false
    @State private var mensajeError: String?
    @State private var gramosTexto = "100"

    private var gramos: Double { Double(gramosTexto.replacingOccurrences(of: ",", with: ".")) ?? 0 }

    var body: some View {
        NavigationStack {
            Group {
                if let producto {
                    formularioCantidad(producto)
                } else if buscando {
                    ProgressView("Buscando producto…")
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                } else {
                    ZStack(alignment: .bottom) {
                        BarcodeScannerView { codigo in
                            buscarProducto(codigo)
                        }
                        .ignoresSafeArea(edges: .bottom)

                        Text("Apunta al código de barras del producto")
                            .font(.footnote)
                            .padding(8)
                            .background(.ultraThinMaterial, in: Capsule())
                            .padding(.bottom, 24)
                    }
                }
            }
            .navigationTitle("Escanear producto")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancelar") { cerrar() }
                }
            }
            .alert("Producto no disponible", isPresented: .constant(mensajeError != nil)) {
                Button("OK") {
                    mensajeError = nil
                    cerrar()
                }
            } message: {
                Text(mensajeError ?? "")
            }
        }
    }

    private func buscarProducto(_ codigo: String) {
        guard !buscando else { return }
        buscando = true
        Task {
            defer { buscando = false }
            do {
                producto = try await OpenFoodFactsService.buscarProducto(codigoBarras: codigo)
            } catch {
                mensajeError = error.localizedDescription
            }
        }
    }

    // MARK: ¿Cuánto has comido?

    private func formularioCantidad(_ producto: ProductoEscaneado) -> some View {
        Form {
            Section("Producto") {
                VStack(alignment: .leading, spacing: 4) {
                    Text(producto.nombre).font(.headline)
                    if let marca = producto.marca {
                        Text(marca).font(.caption).foregroundStyle(.secondary)
                    }
                }
                LabeledContent("Por 100 g", value: "\(Int(producto.kcalPor100g)) kcal")
            }

            Section("¿Cuánto has ingerido?") {
                HStack {
                    TextField("Cantidad", text: $gramosTexto)
                        .keyboardType(.decimalPad)
                    Text("g").foregroundStyle(.secondary)
                }
            }

            Section("Lo que vas a registrar") {
                LabeledContent("Calorías") {
                    Text("\(Int(producto.calorias(gramos: gramos))) kcal").bold()
                }
                LabeledContent("Proteínas", value: "\(Int(producto.proteinas(gramos: gramos))) g")
                LabeledContent("Carbohidratos", value: "\(Int(producto.carbohidratos(gramos: gramos))) g")
                LabeledContent("Grasas", value: "\(Int(producto.grasas(gramos: gramos))) g")
            }

            Section {
                Button("Añadir al diario") {
                    contexto.insert(FoodEntry(
                        nombre: producto.nombre,
                        calorias: producto.calorias(gramos: gramos),
                        proteinas: producto.proteinas(gramos: gramos),
                        carbohidratos: producto.carbohidratos(gramos: gramos),
                        grasas: producto.grasas(gramos: gramos),
                        gramos: gramos,
                        origen: "codigo"
                    ))
                    cerrar()
                }
                .frame(maxWidth: .infinity)
                .bold()
                .disabled(gramos <= 0)
            }
        }
    }
}

// MARK: - Escáner AVFoundation

struct BarcodeScannerView: UIViewControllerRepresentable {
    var alDetectar: (String) -> Void

    func makeUIViewController(context: Context) -> EscanerViewController {
        let controlador = EscanerViewController()
        controlador.alDetectar = alDetectar
        return controlador
    }

    func updateUIViewController(_ uiViewController: EscanerViewController, context: Context) {}
}

final class EscanerViewController: UIViewController, AVCaptureMetadataOutputObjectsDelegate {
    var alDetectar: ((String) -> Void)?

    private let sesion = AVCaptureSession()
    private var capaPrevia: AVCaptureVideoPreviewLayer?
    private var yaDetectado = false

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .black
        configurarSesion()
    }

    private func configurarSesion() {
        guard let dispositivo = AVCaptureDevice.default(for: .video),
              let entrada = try? AVCaptureDeviceInput(device: dispositivo),
              sesion.canAddInput(entrada) else { return }
        sesion.addInput(entrada)

        let salida = AVCaptureMetadataOutput()
        guard sesion.canAddOutput(salida) else { return }
        sesion.addOutput(salida)
        salida.setMetadataObjectsDelegate(self, queue: .main)
        salida.metadataObjectTypes = [.ean13, .ean8, .upce, .code128]

        let capa = AVCaptureVideoPreviewLayer(session: sesion)
        capa.videoGravity = .resizeAspectFill
        view.layer.addSublayer(capa)
        capaPrevia = capa

        DispatchQueue.global(qos: .userInitiated).async { [weak self] in
            self?.sesion.startRunning()
        }
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        capaPrevia?.frame = view.bounds
    }

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if sesion.isRunning { sesion.stopRunning() }
    }

    func metadataOutput(_ output: AVCaptureMetadataOutput,
                        didOutput metadataObjects: [AVMetadataObject],
                        from connection: AVCaptureConnection) {
        guard !yaDetectado,
              let objeto = metadataObjects.first as? AVMetadataMachineReadableCodeObject,
              let valor = objeto.stringValue else { return }
        yaDetectado = true
        sesion.stopRunning()
        UINotificationFeedbackGenerator().notificationOccurred(.success)
        alDetectar?(valor)
    }
}
