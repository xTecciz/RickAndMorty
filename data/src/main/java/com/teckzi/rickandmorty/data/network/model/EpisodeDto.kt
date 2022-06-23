package com.teckzi.ricks.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("air_date") val airDate: String,
    @SerialName("episode") val episode: String,
    @SerialName("characters") val characters: List<String>,
    @SerialName("url") val url: String? = null,
    @SerialName("created") val created: String? = null
)