package com.teckzi.domain.model

data class EpisodeModel(
    var id: Int,
    var name: String,
    var airDate: String,
    var episode: String,
    var characters: List<Int>
)