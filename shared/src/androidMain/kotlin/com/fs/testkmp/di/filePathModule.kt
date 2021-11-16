package com.fs.testkmp.di

import android.content.Context
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun filePathModule() = module {

    single(named(FILE_PATH_QUALIFIER)) {
        getDbPath(get())
    }
}

fun getDbPath(context: Context) =
    context.filesDir.absolutePath