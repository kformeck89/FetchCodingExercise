package com.kformeck.fetchcodingexercise.ui.screens.root

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.kformeck.fetchcodingexercise.ui.components.LoadingIndicator
import com.kformeck.fetchcodingexercise.ui.navigation.FetchCodingExerciseNavHost

@Composable
fun RootScreen(viewModel: RootViewModel = hiltViewModel()) {
    val loadingState = viewModel.loadingState.collectAsState()
    val navController = rememberNavController()

    viewModel.setNavController(navController)

    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            AnimatedContent(
                targetState = loadingState.value.isLoading,
                contentAlignment = Alignment.Center,
            ) {
                if (it) {
                    LoadingIndicator()
                } else {
                    FetchCodingExerciseNavHost(navController = navController)
                }
            }
        }
    }
}
