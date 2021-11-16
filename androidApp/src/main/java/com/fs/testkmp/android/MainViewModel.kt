package com.fs.testkmp.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.testkmp.Main
import com.fs.testkmp.data.Movie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val main: Main) : ViewModel() {

    val state = main.getPopularList()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList<Movie>())

    fun getMovieById(id: Int) =
        main.getMovieById(id)
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)
}
