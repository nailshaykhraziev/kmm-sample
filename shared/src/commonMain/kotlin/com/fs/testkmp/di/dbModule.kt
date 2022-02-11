package com.fs.testkmp.di

import com.fs.testkmp.database.AppDatabase
import com.fs.testkmp.db.MovieDao
import com.fs.testkmp.db.SqlMovieDao
import com.squareup.sqldelight.ColumnAdapter
import comfstestkmp.Movies
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FILE_PATH_QUALIFIER = "FilePath"

const val DAO_QUALIFIER_DELIGHT = "SqlDelight"

val dbModule = module {

    single {
        AppDatabase.invoke(driver = get(), moviesAdapter = Movies.Adapter(listOfIntAdapter))
    }

    single<MovieDao>(named(DAO_QUALIFIER_DELIGHT)) {
        SqlMovieDao(get())
    }
}

private val listOfIntAdapter = object : ColumnAdapter<List<Int>, String> {
    override fun decode(databaseValue: String) =
        if (databaseValue.isEmpty()) {
            listOf()
        } else {
            databaseValue.split(",").map {
                it.toInt()
            }
        }

    override fun encode(value: List<Int>) = value.joinToString(separator = ",")
}
