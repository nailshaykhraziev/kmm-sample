package com.fs.testkmp.db

import com.fs.testkmp.data.Movie
import com.fs.testkmp.data.MovieResponse
import com.fs.testkmp.database.AppDatabase
import comfstestkmp.MovieQueries

class SqlMovieDao(
    private val database: AppDatabase
) : MovieDao {

    override suspend fun saveMovie(movieEntity: MovieResponse) {
        database.movieQueries.run {
            transaction {
                insertMovie(movieEntity)
            }
        }
    }

    override suspend fun saveAll(movieEntities: List<MovieResponse>) {
        database.movieQueries.run {
            transaction {
                movieEntities.forEach { movieEntity ->
                    insertMovie(movieEntity)
                }
            }
        }
    }

    private fun MovieQueries.insertMovie(movieEntity: MovieResponse) {
        insertMovies(
            movieEntity.id.toLong(),
            movieEntity.title,
            movieEntity.voteCount?.toLong(),
            movieEntity.voteAverage,
            movieEntity.posterPath,
            movieEntity.releaseDate,
            movieEntity.overview,
            movieEntity.genres
        )
    }

    override suspend fun getAll(): List<Movie> {
        return database.movieQueries.selectMovies(::mapMovie).executeAsList()
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return database.movieQueries.selectMovieById(id.toLong(), ::mapMovie).executeAsOneOrNull()
    }

    override suspend fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        database.movieQueries.deleteAll()
    }

    private fun mapMovie(
        id: Long,
        title: String,
        vote_count: Long?,
        vote_average: Double?,
        poster_path: String,
        release_date: String,
        overview: String,
        genre_ids: List<Int>?
    ): Movie = Movie(
        id = id.toInt(),
        title = title,
        voteCount = vote_count?.toInt(),
        voteAverage = vote_average,
        posterPath = poster_path,
        releaseDate = release_date,
        overview = overview,
        genres = genre_ids
    )

    companion object {
//        const val
    }
}
