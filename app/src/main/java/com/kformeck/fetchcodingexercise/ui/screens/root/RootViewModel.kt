package com.kformeck.fetchcodingexercise.ui.screens.root

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.kformeck.fetchcodingexercise.ui.LoadingManager
import com.kformeck.fetchcodingexercise.ui.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    loadingManager: LoadingManager,
    private val navigator: RouteNavigator,
) : ViewModel() {
    val loadingState = loadingManager.state

    fun setNavController(navController: NavHostController) {
        navigator.setNavController(navController)
    }
}
