package com.example.destinationapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Ticket : Screen("ticket")
    object Profile : Screen("profile")
    object DetailDestination: Screen("destination/{destinationId}") {
        fun createRoute(destinationId: Long) = "destination/$destinationId"
    }
    object Destination : Screen("destination")
}