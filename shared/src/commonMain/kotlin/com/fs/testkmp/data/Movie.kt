package com.fs.testkmp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kodein.db.model.orm.Metadata

@Serializable
data class Movie(
    @SerialName("id")
    override val id: Int,
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
) : Metadata

@Serializable
data class MoviesResponse(
    @SerialName("results")
    val movies: List<Movie>
)
