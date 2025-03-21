package com.kformeck.fetchcodingexercise.api

data class FetchHiringDataResponse(
    val items: List<FetchHiringDataItem> = emptyList(),
)

data class FetchHiringDataItem(
    val id: Int,
    val listId: Int,
    val name: String?,
)
