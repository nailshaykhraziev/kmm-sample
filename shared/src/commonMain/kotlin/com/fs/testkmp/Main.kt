package com.fs.testkmp

import com.fs.testkmp.data.MovieApi
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
    private val movieApi: MovieApi
) {

    fun getPopularList() = flow {
        emit(movieApi.getPopularMovies())
    }.flowOn(Dispatchers.Default)

    fun getMovieById(id: Int) = flow {
        emit(movieApi.getMovieById(id))
    }.flowOn(Dispatchers.Default)
}
