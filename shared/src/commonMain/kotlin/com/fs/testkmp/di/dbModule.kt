package com.fs.testkmp.di

import com.fs.testkmp.data.MovieEntity
import com.fs.testkmp.database.AppDatabase
import com.fs.testkmp.db.KodeinMovieDao
import com.fs.testkmp.db.MovieDao
import com.fs.testkmp.db.SqlMovieDao
import com.squareup.sqldelight.ColumnAdapter
import comfstestkmp.Movies
import org.kodein.db.DB
import org.kodein.db.impl.open
import org.kodein.db.orm.kotlinx.KotlinxSerializer
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FILE_PATH_QUALIFIER = "FilePath"

val dbModule = module {

    single {
        DB.open(get(named(FILE_PATH_QUALIFIER)), KotlinxSerializer {
            +MovieEntity.serializer()
        })
    }

    single {
        AppDatabase.invoke(driver = get(), moviesAdapter = Movies.Adapter(listOfIntAdapter))
    }

    single<MovieDao>(named("Kodein")) {
        KodeinMovieDao(get())
    }

    single<MovieDao>(named("SqlDelight")) {
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
