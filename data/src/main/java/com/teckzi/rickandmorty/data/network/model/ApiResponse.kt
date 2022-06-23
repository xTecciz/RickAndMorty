package com.teckzi.rickandmorty.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("info") val info: InfoModel,
    @SerialName("results") val results: List<T> = emptyList()
)