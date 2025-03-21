package com.kformeck.fetchcodingexercise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kformeck.fetchcodingexercise.ui.screens.root.RootScreen
import com.kformeck.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchCodingExerciseTheme {
                RootScreen()
            }
        }
    }
}
