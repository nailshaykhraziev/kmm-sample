package com.fs.testkmp.db

import com.fs.testkmp.data.Movie
import com.fs.testkmp.data.MovieResponse

class SqlMovieDao() : MovieDao {

    override suspend fun saveMovie(movieEntity: MovieResponse) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(movieEntities: List<MovieResponse>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieById(id: Int): Movie? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    companion object {
//        const val
    }
}
