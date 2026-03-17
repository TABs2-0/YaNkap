package org.example.yankap

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.yankap.ui.screens.Home

// Simple manual navigation state matching the defined screens
enum class Screen {
    TRANSACTION,
    HISTORY,
    SETTINGS
}

@Suppress("FunctionName")
@Composable
@Preview
fun App() {
    Home().Home()
}