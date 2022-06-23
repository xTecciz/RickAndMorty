package com.teckzi.ricks.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("dimension") val dimension: String,
    @SerialName("residents") val characters: List<String>,
    @SerialName("url") val url: String? = null,
    @SerialName("created") val created: String? = null
)