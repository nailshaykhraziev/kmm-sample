package com.fs.testkmp.di

import android.content.Context
import com.fs.testkmp.database.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun filePathModule() = module {

    single(named(FILE_PATH_QUALIFIER)) {
        getDbPath(get())
    }

    single {
        getSqlDelight(get())
    }
}

private fun getDbPath(
    context: Context
): String = context.filesDir.absolutePath

private fun getSqlDelight(
    context: Context
): SqlDriver = AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
