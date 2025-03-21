package com.kformeck.fetchcodingexercise.ui.screens.results

import com.kformeck.fetchcodingexercise.api.FetchHiringDataItem

data class ResultsUiState(
    val results: List<HiringDataCardGroup> = emptyList(),
)

data class HiringDataCardGroup(
    val listId: String,
    val items: List<HiringDataCardItem>,
) {
    val listIdDisplay: String
        get() = "$LIST_ID_PREFIX $listId"

    companion object {
        private const val LIST_ID_PREFIX = "List ID:"
    }
}

data class HiringDataCardItem(
    val id: Int,
    val name: String,
) {
    companion object {
        fun fromFetchHiringDataItem(hiringDataItem: FetchHiringDataItem) =
            HiringDataCardItem(
                id = hiringDataItem.id,
                name = hiringDataItem.name ?: "",
            )
    }
}
