package com.fs.testkmp.db

import com.fs.testkmp.data.Movie
import org.kodein.db.*
import org.kodein.memory.use

class MovieDao(
    private val db: DB
) {
    suspend fun saveMovie(movie: Movie) {
        db.put(movie)
    }

    suspend fun saveAll(movies: List<Movie>) {
        movies.forEach { saveMovie(it) }
    }

    suspend fun getAll() =
        db.find<Movie>().all()

    suspend fun getMovieById(id: Int) = db.getById<Movie>(id)

    suspend fun deleteById(id: Int) {
        db.deleteById<Movie>(id)
    }

    suspend fun deleteAll() {
        db.find<Movie>().all().use {
            db.deleteAll(it)
        }
    }
}