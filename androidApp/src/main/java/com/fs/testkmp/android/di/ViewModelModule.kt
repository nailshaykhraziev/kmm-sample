package com.fs.testkmp.android.di

import com.fs.testkmp.Main
import com.fs.testkmp.android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(get())
    }
}
