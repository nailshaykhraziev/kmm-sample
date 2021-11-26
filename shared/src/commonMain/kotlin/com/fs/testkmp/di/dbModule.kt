package com.fs.testkmp.di

import com.fs.testkmp.data.MovieEntity
import com.fs.testkmp.db.KodeinMovieDao
import com.fs.testkmp.db.MovieDao
import com.fs.testkmp.db.SqlMovieDao
import org.kodein.db.DB
import org.kodein.db.impl.open
import org.kodein.db.orm.kotlinx.KotlinxSerializer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dbModule = module {

    single {
        DB.open(get(named(FILE_PATH_QUALIFIER)), KotlinxSerializer {
            +MovieEntity.serializer()
        })
    }

    single<MovieDao>(named("Kodein")) {
        KodeinMovieDao(get())
    }

    single<MovieDao>(named("SqlDelight")) {
        SqlMovieDao()
    }

}

const val FILE_PATH_QUALIFIER = "FilePath"
