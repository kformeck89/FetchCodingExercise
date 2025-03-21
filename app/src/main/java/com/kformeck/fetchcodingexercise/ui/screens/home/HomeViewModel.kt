package com.kformeck.fetchcodingexercise.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kformeck.fetchcodingexercise.api.FetchRepository
import com.kformeck.fetchcodingexercise.ui.LoadingManager
import com.kformeck.fetchcodingexercise.ui.navigation.Route
import com.kformeck.fetchcodingexercise.ui.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRepository: FetchRepository,
    private val navigator: RouteNavigator,
    private val loadingManager: LoadingManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getHiringData() {
        viewModelScope.launch {
            loadingManager.showLoadingIndicator()
            fetchRepository.getHiringData(
                onSuccess = {
                    loadingManager.hideLoadingIndicator()
                    navigator.navigateTo(Route.Results)
                },
                onError = {
                    loadingManager.hideLoadingIndicator()
                    _uiState.update { it.copy(hasError = true) }

                    viewModelScope.launch {
                        delay(ERROR_VIEW_DURATION)
                        _uiState.update { it.copy(hasError = false) }
                    }
                },
            )
        }
    }

    companion object {
        private const val ERROR_VIEW_DURATION = 2500L
    }
}
