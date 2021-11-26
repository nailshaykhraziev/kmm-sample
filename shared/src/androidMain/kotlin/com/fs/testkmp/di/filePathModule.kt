package com.fs.testkmp.di

import android.content.Context
import com.fs.testkmp.db.SqlMovieDao
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun filePathModule() = module {

    single(named(FILE_PATH_QUALIFIER)) {
        getDbPath(get())
    }

//    single {
//        AndroidSqliteDriver(database = MyDatabase.Schema, context, "test.db")
//    }
}

fun getDbPath(context: Context) =
    context.filesDir.absolutePath
