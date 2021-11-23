package com.fs.testkmp

import com.fs.testkmp.data.MovieApi
import com.fs.testkmp.data.mapToMovie
import com.fs.testkmp.db.KodeinMovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Main(
    private val movieApi: MovieApi,
    private val kodeinMovieDao: KodeinMovieDao
) {

    fun getPopularList() = flow {
        kodeinMovieDao.saveAll(movieApi.getPopularMovies())
        emit(kodeinMovieDao.getAll().map {
            it.mapToMovie()
        })
    }.flowOn(Dispatchers.Default)

    fun getMovieById(id: Int) = flow {
        emit(movieApi.getMovieById(id))
    }.flowOn(Dispatchers.Default)
}
