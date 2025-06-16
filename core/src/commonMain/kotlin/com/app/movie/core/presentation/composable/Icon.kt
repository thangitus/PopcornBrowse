package com.app.movie.core.presentation.composable

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon as ComposeIcon

@Composable
fun Icon(
    icon: ImageVector,
    color: Color = Color.Unspecified,
    size: Int,
    modifier: Modifier = Modifier,
) {
    val painter = rememberVectorPainter(icon)

    ComposeIcon(
        painter = painter,
        contentDescription = "",
        modifier = modifier.size(size.dp),
        tint = color
    )
}