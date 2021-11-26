package com.fs.testkmp

import com.fs.testkmp.data.MovieApi
import com.fs.testkmp.db.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Main(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) {

    fun getPopularList() = flow {
        movieDao.saveAll(movieApi.getPopularMovies())
        emit(movieDao.getAll())
    }.flowOn(Dispatchers.Default)

    fun getMovieById(id: Int) = flow {
        emit(movieApi.getMovieById(id))
    }.flowOn(Dispatchers.Default)
}
