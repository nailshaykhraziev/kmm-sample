package com.fs.testkmp.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.fs.testkmp.database.AppDatabase

actual fun filePathModule() = module {

    single(named(FILE_PATH_QUALIFIER)) {
        getDbPath(get())
    }

    single {
        getSqldelight(get())
    }
}

fun getDbPath(context: Context) =
    context.filesDir.absolutePath

fun getSqldelight(context: Context) =
    AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
