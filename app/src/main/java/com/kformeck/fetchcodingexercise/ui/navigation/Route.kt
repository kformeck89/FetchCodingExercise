package com.kformeck.fetchcodingexercise.ui.navigation

sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Results : Route("results")
}
