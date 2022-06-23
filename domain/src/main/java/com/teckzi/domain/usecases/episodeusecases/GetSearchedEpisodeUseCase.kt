package com.teckzi.domain.usecases.episodeusecases

import androidx.paging.PagingData
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetSearchedEpisodeUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return repository.searchEpisode(
            name = name,
            episode = episode
        )
    }
}