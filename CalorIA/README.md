# CalorIA 🍽️

App de iOS para controlar tus calorías con inteligencia artificial: fotografía tu plato, escanea productos y sigue tu déficit calórico día a día.

## Funcionalidades

- **📷 Foto del plato** — Haz una foto de tu comida y la IA (API de Claude, con visión) identifica los alimentos, estima las porciones en gramos y calcula calorías y macronutrientes. Puedes revisar y deseleccionar alimentos antes de añadirlos.
- **🔍 Escáner de códigos de barras** — Escanea la etiqueta de cualquier producto; se busca en OpenFoodFacts (gratis, millones de productos), indicas cuántos gramos has comido y calcula las calorías ingeridas.
- **🔄 Reinicio diario** — El contador de "Hoy" se reinicia automáticamente cada día. El historial se conserva para las estadísticas.
- **📊 Gráficas semanales** — Barras de los últimos 7 días frente a tu objetivo: verde si cumpliste, rojo si superaste el límite, con resumen de días excedidos y media semanal.
- **🧮 Objetivo personalizado** — Al empezar te pide peso, edad, altura, sexo y nivel de actividad. Calcula tu mantenimiento (fórmula Mifflin-St Jeor) y tu objetivo diario con el déficit que elijas (−250 / −500 / −750 kcal).
- **👨‍🍳 Recetas con IA** — Indica tus gustos y restricciones, y la IA genera recetas adaptadas a tu objetivo con calorías por ración, ingredientes con cantidades y pasos de preparación. Puedes registrar una ración directamente en el diario.
- **✍️ Entrada manual** — Para todo lo demás.

## Requisitos

- **Xcode 16** o superior
- **iOS 17.0** o superior (usa SwiftData y Swift Charts)
- Una **clave de API de Anthropic** para las funciones de IA (foto y recetas):
  1. Crea una cuenta en [console.anthropic.com](https://console.anthropic.com)
  2. Genera una clave de API (empieza por `sk-ant-…`)
  3. Introdúcela en el onboarding de la app o en **Ajustes → Clave API**

> 💰 **Coste**: el análisis de cada foto y la generación de recetas usan la API de Claude (modelo `claude-opus-4-8`), que es de pago — del orden de céntimos por uso. El escáner de códigos de barras usa OpenFoodFacts y es **gratuito**.

## Cómo ejecutarla

1. Abre `CalorIA.xcodeproj` con Xcode.
2. En el target **CalorIA → Signing & Capabilities**, selecciona tu equipo de desarrollo (Apple ID gratuito vale).
3. Conecta tu iPhone (la cámara y el escáner no funcionan en el simulador) y pulsa ▶︎ Run.
4. Completa el onboarding con tus datos y tu clave de API.

## Estructura del proyecto

```
CalorIA/
├── CalorIAApp.swift           # Punto de entrada, contenedor SwiftData
├── Models/Models.swift        # UserProfile, FoodEntry, Recipe (SwiftData)
├── Services/
│   ├── CalorieCalculator.swift    # Mifflin-St Jeor: BMR, TDEE, objetivo
│   ├── ClaudeService.swift        # API de Claude: visión (fotos) y recetas
│   ├── OpenFoodFactsService.swift # Búsqueda de productos por código de barras
│   └── KeychainHelper.swift       # Clave API cifrada en el llavero
└── Views/
    ├── RootView.swift             # Onboarding o app según haya perfil
    ├── OnboardingView.swift       # 3 pasos: datos, actividad/déficit, gustos
    ├── TodayView.swift            # Diario del día (se reinicia a diario)
    ├── PhotoAnalysisView.swift    # Cámara/galería + análisis con IA
    ├── BarcodeScannerView.swift   # Escáner AVFoundation + cantidad ingerida
    ├── WeeklyChartView.swift      # Gráfica Swift Charts de 7 días
    ├── RecipesView.swift          # Generación y detalle de recetas
    ├── SettingsView.swift         # Editar perfil, gustos, clave API
    └── ManualEntryView.swift      # Entrada rápida manual
```

## Notas técnicas

- Los datos se guardan **solo en el dispositivo** (SwiftData). No hay servidor propio.
- La clave de API se guarda en el **llavero del sistema** (Keychain), no en UserDefaults.
- Las fotos se redimensionan a 1024 px antes de enviarse a la API para reducir coste y latencia.
- Las respuestas de la IA usan **salida estructurada** (`output_config.format` con JSON Schema), por lo que el JSON siempre es válido y tipado.
- El objetivo diario nunca baja de 1200 kcal por seguridad.
