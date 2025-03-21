package com.kformeck.fetchcodingexercise.ui.screens.results

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kformeck.fetchcodingexercise.R
import com.kformeck.fetchcodingexercise.ui.components.ContentContainer
import com.kformeck.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme
import kotlinx.coroutines.launch

@Composable
fun ResultsScreen(viewModel: ResultsViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()
    val scrollToTopScope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf { viewModel.isScrollToTopVisible(scrollState.firstVisibleItemIndex) }
    }

    ContentContainer(applyHorizontalPadding = false) {
        TitleBar(viewModel::navigateBack)
        Box(Modifier.fillMaxSize()) {
            ResultsListView(
                hiringDataGroups = uiState.value.results,
                scrollState = scrollState,
                modifier = Modifier.align(Alignment.TopCenter),
            )

            androidx.compose.animation.AnimatedVisibility(
                visible = showScrollToTopButton,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = dimensionResource(R.dimen.padding_large),
                        bottom = dimensionResource(R.dimen.padding_medium),
                    ),
            ) {
                ScrollToTopButton(
                    onClick = {
                        scrollToTopScope.launch {
                            scrollState.scrollToItem(0)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun TitleBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.desc_button_back),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = stringResource(R.string.text_results_title_bar_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ResultsListView(
    hiringDataGroups: List<HiringDataCardGroup>,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState,
        contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_small)),
        modifier = modifier.fillMaxWidth(),
    ) {
        hiringDataGroups.forEach { group ->
            stickyHeader {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                ) {
                    Text(
                        text = group.listIdDisplay,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    )
                }
            }
            items(
                items = group.items,
                key = { it.id },
            ) {
                Column(Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))) {
                    HiringDataCard(it)
                }
            }
        }
    }
}

@Composable
private fun HiringDataCard(
    hiringDataItem: HiringDataCardItem,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.text_prefix_id),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = hiringDataItem.id.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.text_prefix_name),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = hiringDataItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
private fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowUpward,
            contentDescription = stringResource(R.string.desc_button_scroll_to_top),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleBarPreview() {
    FetchCodingExerciseTheme {
        TitleBar({})
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultsListViewPreview() {
    FetchCodingExerciseTheme {
        ResultsListView(
            hiringDataGroups = listOf(
                HiringDataCardGroup(
                    listId = "1",
                    items = listOf(
                        HiringDataCardItem(id = 101, name = "Item 101"),
                        HiringDataCardItem(id = 102, name = "Item 102"),
                        HiringDataCardItem(id = 103, name = "Item 103"),
                    ),
                ),
                HiringDataCardGroup(
                    listId = "2",
                    items = listOf(
                        HiringDataCardItem(id = 201, name = "Item 201"),
                        HiringDataCardItem(id = 202, name = "Item 202"),
                        HiringDataCardItem(id = 203, name = "Item 203"),
                    ),
                ),
            ),
            scrollState = rememberLazyListState(),
        )
    }
}
