package com.fs.testkmp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("vote_count")
    val voteCount: Int?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("genre_ids")
    val genres: List<Int>?
)

@Serializable
data class MoviesResponse(
    @SerialName("results")
    val movieEntities: List<MovieResponse>
)

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val voteCount: Int?,
    val voteAverage: Double?,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val genres: List<Int>?
)

fun MovieEntity.mapToMovie() = Movie(
    id, title, voteCount, voteAverage, posterPath, releaseDate, overview, genres
)

@Serializable
data class MovieResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("vote_count")
    val voteCount: Int?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("genre_ids")
    val genres: List<Int>?
)
