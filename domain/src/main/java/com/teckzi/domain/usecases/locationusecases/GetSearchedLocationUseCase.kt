package com.teckzi.domain.usecases.locationusecases

import androidx.paging.PagingData
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetSearchedLocationUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {
        return repository.searchLocation(
            name = name,
            type = type,
            dimension = dimension
        )
    }
}