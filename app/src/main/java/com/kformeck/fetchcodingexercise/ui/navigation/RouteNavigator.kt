package com.kformeck.fetchcodingexercise.ui.navigation

import androidx.navigation.NavHostController

class RouteNavigator {
    private lateinit var navController: NavHostController

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    private fun navigateSingleTopTo(route: String) =
        navController.navigate(route) {
            launchSingleTop = true
        }

    fun navigateTo(route: Route) = navigateSingleTopTo(route.path)

    fun navigateBack() = navController.popBackStack()
}
