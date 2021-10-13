package com.fs.testkmp

import com.fs.testkmp.data.MovieApi
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
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
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install("API_KEY") {
            sendPipeline.intercept(HttpSendPipeline.Before) {
                // todo Add api key
            }
        }
    }

    private val movieApi by lazy {
        MovieApi(httpClient)
    }


}
