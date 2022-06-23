package com.teckzi.domain.usecases.episodeusecases

import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.repository.IRepository

class GetSelectedEpisodeUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(episodeId: Int): EpisodeModel {
        return repository.getEpisodeById(episodeId)
    }
}