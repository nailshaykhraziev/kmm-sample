package com.fs.testkmp.di

import com.fs.testkmp.Main
import com.fs.testkmp.data.MovieApi
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import org.koin.dsl.module

val networkModule = module {
    single {
        Main(get())
    }
    single {
        MovieApi(get())
    }
    single {
        createHttpClient()
    }
}

fun createHttpClient() = HttpClient {
    install(JsonFeature) {
        val json = kotlinx.serialization.json.Json {
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
