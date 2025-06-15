package com.app.movie.core.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.movie.core.presentation.theme.backgroundGradient
import com.app.movie.core.presentation.theme.darkenRed
import com.app.movie.core.presentation.theme.primaryWhite

@Composable
fun ErrorView(
    onRetry: () -> Unit,
    errorMsg: String? = null
) {
    Column(Modifier.fillMaxSize().background(backgroundGradient).padding(16.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            Text(
                text = "Error loading information :(",
                color = primaryWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            errorMsg?.let {
                Text(
                    text = it,
                    color = primaryWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Button(
            onClick = { onRetry() },
            colors = ButtonDefaults.buttonColors(darkenRed),
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "Retry",
                color = primaryWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}