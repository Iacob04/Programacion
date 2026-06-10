import SwiftUI
import SwiftData

@main
struct CalorIAApp: App {
    var body: some Scene {
        WindowGroup {
            RootView()
        }
        .modelContainer(for: [UserProfile.self, FoodEntry.self, Recipe.self])
    }
}
