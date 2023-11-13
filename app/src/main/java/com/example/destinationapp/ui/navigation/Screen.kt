package com.example.destinationapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Ticket : Screen("ticket")
    object Profile : Screen("profile")
    object DetailDestination: Screen("home/{destinationId}") {
        fun createRoute(destinationId: Long) = "home/$destinationId"
    }
}