package com.fs.testkmp

import com.fs.testkmp.data.MovieApi
import com.fs.testkmp.db.MovieDao
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json

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
