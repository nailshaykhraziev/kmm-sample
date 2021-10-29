package com.fs.testkmp.android

sealed class Screen(val title: String) {
    object MovieList : Screen("MovieList")
    object MovieContent : Screen("MovieContent")
}
