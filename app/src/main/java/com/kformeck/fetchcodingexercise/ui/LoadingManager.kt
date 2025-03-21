package com.kformeck.fetchcodingexercise.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoadingManager {
    private val _state = MutableStateFlow(LoadingUiState())
    val state = _state.asStateFlow()

    fun showLoadingIndicator() {
        _state.update { it.copy(isLoading = true) }
    }

    fun hideLoadingIndicator() {
        _state.update { it.copy(isLoading = false) }
    }
}
