package com.teckzi.domain.usecases.episodeusecases

import androidx.paging.PagingData
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetAllEpisodeUseCase(
    private val repository: IRepository
) {
    operator fun invoke(): Flow<PagingData<EpisodeModel>> {
        return repository.getAllEpisodes()
    }
}