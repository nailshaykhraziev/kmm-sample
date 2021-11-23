package com.fs.testkmp.db

import com.fs.testkmp.data.Movie
import com.fs.testkmp.data.MovieEntity
import com.fs.testkmp.data.MovieResponse
import com.fs.testkmp.data.mapToMovie
import org.kodein.db.*
import org.kodein.memory.use

class KodeinMovieDao(
    private val db: DB
):MovieDao {
    override suspend fun saveMovie(movieEntity: MovieResponse) {
        db.put(movieEntity)
    }

    override suspend fun saveAll(movieEntities: List<MovieResponse>) {
        movieEntities.forEach { saveMovie(it) }
    }

    override suspend fun getAll(): List<Movie> =
        db.find<MovieEntity>().all().use {
            it.asModelSequence().toList().map {
                it.mapToMovie()
            }
        }

    override suspend fun getMovieById(id: Int): Movie? = db.getById<MovieEntity>(id)?.mapToMovie()

    override suspend fun deleteById(id: Int) {
        db.deleteById<MovieEntity>(id)
    }

    override suspend fun deleteAll() {
        db.find<MovieEntity>().all().use {
            db.deleteAll(it)
        }
    }
}
