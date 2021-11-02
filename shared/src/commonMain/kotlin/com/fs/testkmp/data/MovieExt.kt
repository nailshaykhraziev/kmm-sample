package com.fs.testkmp.data

private const val BASE_URL = "https://image.tmdb.org/t/p/w185"

fun Movie.toMovieThumbnailUrl() = "$BASE_URL$posterPath"

fun MovieDetails.toMovieThumbnailUrl() = "$BASE_URL${posterPath.orEmpty()}"
