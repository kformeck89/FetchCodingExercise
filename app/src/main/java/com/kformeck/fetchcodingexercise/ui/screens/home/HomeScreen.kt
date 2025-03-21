package com.kformeck.fetchcodingexercise.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kformeck.fetchcodingexercise.R
import com.kformeck.fetchcodingexercise.ui.components.ContentContainer
import com.kformeck.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    ContentContainer {
        AnimatedContent(uiState.value.hasError) {
            if (it) {
                ErrorView()
            } else {
                RetrieveDataView(viewModel::getHiringData)
            }
        }
    }
}

@Composable
private fun RetrieveDataView(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_fetch_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .width(dimensionResource(R.dimen.width_fetch_logo)),
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_large)))
            Text(
                text = stringResource(R.string.message_welcome_headline),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_large)))
            Text(
                text = stringResource(R.string.message_welcome_instruction),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
            )
        }
        RetrieveDataButton(
            icon = Icons.Filled.Download,
            text = stringResource(R.string.button_retrieve_data),
            onClick = onClick,
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
    }
}

@Composable
private fun RetrieveDataButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))) {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun ErrorView(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.message_error),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun RetrieveDataViewPreview() {
    FetchCodingExerciseTheme {
        ContentContainer {
            RetrieveDataView({})
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun ErrorViewPreview() {
    FetchCodingExerciseTheme {
        ContentContainer {
            ErrorView()
        }
    }
}
