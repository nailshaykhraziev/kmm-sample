package com.fs.testkmp.db

import com.fs.testkmp.data.Movie
import com.fs.testkmp.data.MovieResponse

interface MovieDao {
    suspend fun saveMovie(movieEntity: MovieResponse)

    suspend fun saveAll(movieEntities: List<MovieResponse>)

    suspend fun getAll(): List<Movie>

    suspend fun getMovieById(id: Int): Movie?

    suspend fun deleteById(id: Int)

    suspend fun deleteAll()
}
