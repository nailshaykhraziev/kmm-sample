package com.fs.testkmp.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.testkmp.Main
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val main: Main) : ViewModel() {

    val state = main.getPopularList()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun getMovieById(id: Int) =
        main.getMovieById(id)
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)
}
