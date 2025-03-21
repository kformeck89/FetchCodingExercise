package com.kformeck.fetchcodingexercise.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class FetchRepository(private val fetchService: FetchService) {
    private val _hiringData = MutableStateFlow(FetchHiringDataResponse())
    val hiringData = _hiringData.asStateFlow()

    suspend fun getHiringData(
        onSuccess: () -> Unit,
        onError: () -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = fetchService.getHiringData()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    withContext(Dispatchers.Main) {
                        _hiringData.update { FetchHiringDataResponse(body) }
                        onSuccess()
                    }
                } else {
                    onError()
                }
            } catch (_: Exception) {
                onError()
            }
        }
    }
}
