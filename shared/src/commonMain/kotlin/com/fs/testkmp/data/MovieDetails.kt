package com.fs.testkmp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    @SerialName("id")
    var id: Int,
    @SerialName("adult")
    var adult: Boolean = false,
    @SerialName("backdrop_path")
    var backdropPath: String = "",
    @SerialName("budget")
    var budget: Int = 0,
    @SerialName("genres")
    var genres: List<Genre> = arrayListOf(),
    @SerialName("imdb_id")
    var imdbId: String = "",
    @SerialName("original_language")
    var originalLanguage: String = "",
    @SerialName("original_title")
    var originalTitle: String = "",
    @SerialName("overview")
    var overview: String = "",
    @SerialName("popularity")
    var popularity: Double = 0.0,
    @SerialName("poster_path")
    var posterPath: String? = null,
    @SerialName("production_companies")
    var productionCompanies: List<ProductionCompany> = arrayListOf(),
    @SerialName("production_countries")
    var productionCountries: List<ProductionCountry> = arrayListOf(),
    @SerialName("release_date")
    var releaseDate: String = "",
    @SerialName("revenue")
    var revenue: Int = 0,
    @SerialName("runtime")
    var runtime: Int = 0,
    @SerialName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage> = arrayListOf(),
    @SerialName("status")
    var status: String = "",
    @SerialName("tagline")
    var tagline: String = "",
    @SerialName("title")
    var title: String = "",
    @SerialName("video")
    var video: Boolean = false,
    @SerialName("vote_average")
    var voteAverage: Double = 0.0,
    @SerialName("vote_count")
    var voteCount: Int = 0
)

@Serializable
data class Genre(
    @SerialName("id")
    var id: Int,
    @SerialName("name")
    var name: String
)

@Serializable
data class ProductionCompany(
    @SerialName("id")
    var id: Int,
    @SerialName("logo_path")
    var logoPath: String?,
    @SerialName("name")
    var name: String,
    @SerialName("origin_country")
    var originCountry: String
)

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1")
    var iso31661: String,
    @SerialName("name")
    var name: String
)

@Serializable
data class SpokenLanguage(
    @SerialName("iso_639_1")
    var iso6391: String,
    @SerialName("name")
    var name: String
)
