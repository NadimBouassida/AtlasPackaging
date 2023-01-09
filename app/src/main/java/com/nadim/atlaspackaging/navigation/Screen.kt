package com.nadim.atlaspackaging.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen(route = "login_screen")
    object MainScreen: Screen(route = "main_screen")
    object MachineScreen: Screen(route = "machine_screen?machine={machine}")
    object DailyProductionScreen: Screen(route = "daily_production_screen?machine={machine}")
    object ArchiveScreen: Screen(route = "archive_screen?machine={machine}")
}
