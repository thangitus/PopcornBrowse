package com.app.movie.core.presentation.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Star

const val NUM_OF_STARS = 5

@Composable
fun RatingLevel(level: Double, starSize: Int) {
    fun filledStar(item: Int): Boolean = item <= (level * NUM_OF_STARS) / 10

    fun tint(rate: Int): Color = when {
        filledStar(rate) -> Color.Yellow
        else -> Color.Gray
    }

    LazyRow {
        items(NUM_OF_STARS, key = { it }) {
            Icon(
                painter = rememberVectorPainter(FontAwesomeIcons.Solid.Star),
                contentDescription = null,
                modifier = Modifier.size(starSize.dp),
                tint = tint(it)
            )
        }
    }
}