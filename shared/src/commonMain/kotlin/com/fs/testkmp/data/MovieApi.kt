package com.fs.testkmp.data

import io.ktor.client.*
import io.ktor.client.request.*

private const val BASE_URL = "https://api.themoviedb.org/3/"

class MovieApi(
    private val client: HttpClient
) {

    suspend fun getPopularMovies(): List<MovieResponse> =
        client.get<MoviesResponse>("${BASE_URL}movie/popular").movieEntities

    suspend fun getMovieById(
        id: Int
    ): MovieDetails = client.get("${BASE_URL}movie/$id")
}
