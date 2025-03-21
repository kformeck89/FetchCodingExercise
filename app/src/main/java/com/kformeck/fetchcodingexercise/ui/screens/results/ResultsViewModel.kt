package com.kformeck.fetchcodingexercise.ui.screens.results

import androidx.lifecycle.ViewModel
import com.kformeck.fetchcodingexercise.api.FetchRepository
import com.kformeck.fetchcodingexercise.ui.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    fetchRepository: FetchRepository,
    private val navigator: RouteNavigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ResultsUiState(
        results = fetchRepository.hiringData.value.items
            .filter { !it.name.isNullOrBlank() }
            .sortedBy { it.listId }
            .groupBy { it.listId }
            .map { group ->
                HiringDataCardGroup(
                    listId = group.key.toString(),
                    items = group.value
                        // Sort by ID instead of name for better readability of the data
                        // This only works because name always equals "Item " + id
                        // Sorting by name causes incorrect numerical ordering because it's a string
                        .sortedBy { it.id }
                        .map { HiringDataCardItem.fromFetchHiringDataItem(it) },
                )
            }
    ))
    val uiState = _uiState.asStateFlow()

    fun navigateBack() = navigator.navigateBack()

    fun isScrollToTopVisible(firstVisibleItemIndex: Int) = firstVisibleItemIndex > 0
}
