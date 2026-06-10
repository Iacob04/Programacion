import SwiftUI
import SwiftData
import PhotosUI

/// Flujo: elegir/hacer foto → analizar con Claude → revisar alimentos → añadir al diario.
struct PhotoAnalysisView: View {
    @Environment(\.dismiss) private var cerrar
    @Environment(\.modelContext) private var contexto

    @State private var imagen: UIImage?
    @State private var elementoGaleria: PhotosPickerItem?
    @State private var mostrarCamara = false

    @State private var analizando = false
    @State private var analisis: AnalisisPlato?
    @State private var seleccionados: Set<Int> = []
    @State private var mensajeError: String?

    var body: some View {
        NavigationStack {
            Group {
                if let analisis {
                    resultados(analisis)
                } else {
                    seleccionDeFoto
                }
            }
            .navigationTitle("Foto del plato")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancelar") { cerrar() }
                }
            }
            .alert("Error", isPresented: .constant(mensajeError != nil)) {
                Button("OK") { mensajeError = nil }
            } message: {
                Text(mensajeError ?? "")
            }
            .fullScreenCover(isPresented: $mostrarCamara) {
                CameraPicker { foto in imagen = foto }
                    .ignoresSafeArea()
            }
            .onChange(of: elementoGaleria) {
                Task {
                    if let datos = try? await elementoGaleria?.loadTransferable(type: Data.self),
                       let foto = UIImage(data: datos) {
                        imagen = foto
                    }
                }
            }
        }
    }

    // MARK: Selección y análisis

    private var seleccionDeFoto: some View {
        VStack(spacing: 20) {
            if let imagen {
                Image(uiImage: imagen)
                    .resizable()
                    .scaledToFit()
                    .frame(maxHeight: 320)
                    .clipShape(RoundedRectangle(cornerRadius: 16))
                    .padding(.horizontal)

                Button {
                    analizar(imagen)
                } label: {
                    if analizando {
                        ProgressView().frame(maxWidth: .infinity)
                    } else {
                        Label("Analizar con IA", systemImage: "sparkles")
                            .frame(maxWidth: .infinity)
                    }
                }
                .buttonStyle(.borderedProminent)
                .disabled(analizando)
                .padding(.horizontal)

                if analizando {
                    Text("Identificando alimentos y calculando calorías…")
                        .font(.caption).foregroundStyle(.secondary)
                }
            } else {
                ContentUnavailableView(
                    "Fotografía tu plato",
                    systemImage: "camera.viewfinder",
                    description: Text("La IA identificará los alimentos y estimará sus calorías.")
                )
            }

            HStack(spacing: 12) {
                Button {
                    mostrarCamara = true
                } label: {
                    Label("Cámara", systemImage: "camera.fill")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.bordered)

                PhotosPicker(selection: $elementoGaleria, matching: .images) {
                    Label("Galería", systemImage: "photo.on.rectangle")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.bordered)
            }
            .padding(.horizontal)

            Spacer()
        }
        .padding(.top)
    }

    private func analizar(_ foto: UIImage) {
        analizando = true
        Task {
            defer { analizando = false }
            do {
                let resultado = try await ClaudeService.analizarPlato(imagen: foto)
                guard resultado.esComida, !resultado.alimentos.isEmpty else {
                    mensajeError = "No se detectó comida en la imagen. Prueba con otra foto."
                    return
                }
                analisis = resultado
                seleccionados = Set(resultado.alimentos.indices)
            } catch {
                mensajeError = error.localizedDescription
            }
        }
    }

    // MARK: Resultados

    private func resultados(_ analisis: AnalisisPlato) -> some View {
        List {
            Section {
                Text(analisis.descripcion)
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
            }

            Section("Alimentos detectados") {
                ForEach(analisis.alimentos.indices, id: \.self) { indice in
                    let alimento = analisis.alimentos[indice]
                    Button {
                        if seleccionados.contains(indice) {
                            seleccionados.remove(indice)
                        } else {
                            seleccionados.insert(indice)
                        }
                    } label: {
                        HStack {
                            Image(systemName: seleccionados.contains(indice)
                                  ? "checkmark.circle.fill" : "circle")
                                .foregroundStyle(seleccionados.contains(indice) ? .green : .secondary)
                            VStack(alignment: .leading) {
                                Text(alimento.nombre).foregroundStyle(.primary)
                                Text("\(Int(alimento.gramos)) g · P \(Int(alimento.proteinas)) · C \(Int(alimento.carbohidratos)) · G \(Int(alimento.grasas))")
                                    .font(.caption).foregroundStyle(.secondary)
                            }
                            Spacer()
                            Text("\(Int(alimento.calorias)) kcal")
                                .bold().foregroundStyle(.primary)
                        }
                    }
                }
            }

            Section {
                LabeledContent("Total seleccionado") {
                    Text("\(Int(totalSeleccionado)) kcal").bold()
                }
                Button("Añadir al diario") { guardarSeleccion(analisis) }
                    .frame(maxWidth: .infinity)
                    .bold()
                    .disabled(seleccionados.isEmpty)
            }
        }
    }

    private var totalSeleccionado: Double {
        guard let analisis else { return 0 }
        return seleccionados.reduce(0) { $0 + analisis.alimentos[$1].calorias }
    }

    private func guardarSeleccion(_ analisis: AnalisisPlato) {
        for indice in seleccionados.sorted() {
            let alimento = analisis.alimentos[indice]
            contexto.insert(FoodEntry(
                nombre: alimento.nombre,
                calorias: alimento.calorias,
                proteinas: alimento.proteinas,
                carbohidratos: alimento.carbohidratos,
                grasas: alimento.grasas,
                gramos: alimento.gramos,
                origen: "foto"
            ))
        }
        cerrar()
    }
}

// MARK: - Cámara (UIImagePickerController)

struct CameraPicker: UIViewControllerRepresentable {
    var alCapturar: (UIImage) -> Void
    @Environment(\.dismiss) private var cerrar

    func makeUIViewController(context: Context) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.sourceType = .camera
        picker.delegate = context.coordinator
        return picker
    }

    func updateUIViewController(_ uiViewController: UIImagePickerController, context: Context) {}

    func makeCoordinator() -> Coordinator { Coordinator(self) }

    final class Coordinator: NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
        let padre: CameraPicker
        init(_ padre: CameraPicker) { self.padre = padre }

        func imagePickerController(_ picker: UIImagePickerController,
                                   didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
            if let imagen = info[.originalImage] as? UIImage {
                padre.alCapturar(imagen)
            }
            padre.cerrar()
        }

        func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
            padre.cerrar()
        }
    }
}
