package com.teckzi.rickandmorty.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoModel(
    @SerialName("count") val count: Int? = null,
    @SerialName("pages") val pages: Int? = null,
    @SerialName("next") val next: String? = null,
    @SerialName("prev") val prev: String? = null
)