package com.fs.testkmp.data

import io.ktor.client.*
import io.ktor.client.request.*

private const val BASE_URL = "https://api.themoviedb.org/3/"

class MovieApi(
    private val client: HttpClient
) {

    suspend fun getPopularMovies(): List<Movie> =
        client.get<MoviesResponse>("${BASE_URL}movie/popular").movies
}
