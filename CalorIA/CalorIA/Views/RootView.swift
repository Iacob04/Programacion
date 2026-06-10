import SwiftUI
import SwiftData

/// Muestra el onboarding hasta que exista un perfil; después, la app completa.
struct RootView: View {
    @Query private var perfiles: [UserProfile]

    var body: some View {
        if let perfil = perfiles.first {
            MainTabView(perfil: perfil)
        } else {
            OnboardingView()
        }
    }
}

struct MainTabView: View {
    let perfil: UserProfile

    var body: some View {
        TabView {
            TodayView(perfil: perfil)
                .tabItem { Label("Hoy", systemImage: "fork.knife.circle.fill") }

            WeeklyChartView(perfil: perfil)
                .tabItem { Label("Semana", systemImage: "chart.bar.fill") }

            RecipesView(perfil: perfil)
                .tabItem { Label("Recetas", systemImage: "book.fill") }

            SettingsView(perfil: perfil)
                .tabItem { Label("Ajustes", systemImage: "gearshape.fill") }
        }
    }
}
