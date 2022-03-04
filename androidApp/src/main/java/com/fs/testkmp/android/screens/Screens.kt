package com.fs.testkmp.android.screens

sealed class Screen(val title: String) {
    object MovieList : Screen("MovieList")
    object MovieContent : Screen("MovieContent")
    object Trailer : Screen("Trailer")
}
