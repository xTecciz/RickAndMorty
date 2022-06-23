package com.teckzi.domain.usecases.episodeusecases

import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.repository.IRepository

class GetEpisodeListById(
    private val repository: IRepository
) {
    suspend operator fun invoke(episodeIdList: List<Int>): List<EpisodeModel> {
        return repository.getEpisodeListById(episodeIdList)
    }
}