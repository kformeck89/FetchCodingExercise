package com.kformeck.fetchcodingexercise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kformeck.fetchcodingexercise.ui.screens.home.HomeScreen
import com.kformeck.fetchcodingexercise.ui.screens.results.ResultsScreen

@Composable
fun FetchCodingExerciseNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.path,
    ) {
        composable(route = Route.Home.path) {
            HomeScreen()
        }
        composable(route = Route.Results.path) {
            ResultsScreen()
        }
    }
}
