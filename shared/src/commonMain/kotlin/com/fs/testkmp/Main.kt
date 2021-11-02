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

class Main {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = Logger.SIMPLE
        }
        install("API_KEY") {
            sendPipeline.intercept(HttpSendPipeline.Before) {
                context.url.parameters.append("api_key", "3240b6a52ee540b0a26fb1d7687ffafa")
            }
        }
    }

    private val movieApi by lazy {
        MovieApi(httpClient)
    }

    fun getPopularList() = flow {
        emit(movieApi.getPopularMovies())
    }.flowOn(Dispatchers.Default)

    fun getMovieById(id: Int) = flow {
        emit(movieApi.getMovieById(id))
    }.flowOn(Dispatchers.Default)
}
