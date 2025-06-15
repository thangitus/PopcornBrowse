package com.app.movie.home.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.presentation.composable.TextStyles
import com.app.movie.home.presentation.model.HomeMovieSource
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Times

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMoviesContent(
    movies: List<HomeMovie>,
    homeMovieSource: HomeMovieSource,
    searchQuery: String = "",
    onGoToMovie: (Long) -> Unit,
    onQueryChange: (String) -> Unit = { _ -> }
) {

    Scaffold(
        topBar = {
            SearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
                        query = searchQuery,
                        onQueryChange = onQueryChange,
                        onSearch = { /* no-op */ },
                        expanded = false,
                        onExpandedChange = {},
                        enabled = true,
                        placeholder = { Text("Search movies…") },

                        trailingIcon = {
                            if (searchQuery.isNotBlank()) {
                                IconButton(
                                    onClick = { onQueryChange("") },
                                ) {
                                    Icon(
                                        imageVector = FontAwesomeIcons.Solid.Times,
                                        modifier = Modifier.size(24.dp),
                                        contentDescription = "Clear search"
                                    )
                                }
                            }
                        },
                        colors = SearchBarDefaults.colors().inputFieldColors,
                    )
                },
                expanded = false,
                onExpandedChange = { /* no-op */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = 8.dp, end = 8.dp),
                colors = SearchBarDefaults.colors(),
                tonalElevation = SearchBarDefaults.TonalElevation,
                shadowElevation = SearchBarDefaults.ShadowElevation,
                content = { /* no suggestions */ }
            )
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            val textTitle = when (homeMovieSource) {
                HomeMovieSource.TRENDING -> "Trending Movies"
                HomeMovieSource.SEARCH -> if (searchQuery.isNotBlank()) "Search Results for \"$searchQuery\"" else "Search Results"
            }
            Text(
                text = textTitle,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                style = TextStyles.Heading2
            )
            HomeMoviesList(
                movies = movies,
                onGoToMovie = onGoToMovie,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
