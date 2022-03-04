package com.fs.testkmp.data

import kotlinx.serialization.SerialName

data class MovieVideo(
    @SerialName("id")
    val id: String,
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String,
    @SerialName("site")
    val site: String,
    @SerialName("type")
    val type: String
)

data class MovieVideoResponse(
    @SerialName("id")
    val id: String,
    @SerialName("results")
    val list: List<MovieVideo>
)
