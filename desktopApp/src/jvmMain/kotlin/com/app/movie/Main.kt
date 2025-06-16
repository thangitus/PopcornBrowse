package com.app.movie

import androidx.compose.material.Text
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() {
    singleWindowApplication(
        title = "Demo",
        state = WindowState(size = DpSize(500.dp, 800.dp))
    ) {
        Text("Hello, World!")
    }
}
