package com.kformeck.fetchcodingexercise.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.kformeck.fetchcodingexercise.R

@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    applyHorizontalPadding: Boolean = true,
    content: @Composable () -> Unit,
) {
    val horizontalPadding = dimensionResource(R.dimen.padding_medium)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                start =
                    if (applyHorizontalPadding) {
                        horizontalPadding
                    } else {
                        0.dp
                    },
                end =
                    if (applyHorizontalPadding) {
                        horizontalPadding
                    } else {
                        0.dp
                    },
                top = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_medium),
            ),
    ) {
        content()
    }
}
