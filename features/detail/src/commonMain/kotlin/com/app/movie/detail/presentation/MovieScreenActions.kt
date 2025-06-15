package com.app.movie.detail.presentation

internal sealed class MovieScreenActions

internal class OnInitMovieScreen(
    val id: Long
) : MovieScreenActions()

