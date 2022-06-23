package com.teckzi.domain.usecases.locationusecases

import androidx.paging.PagingData
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllLocationUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<LocationModel>> {
        return repository.getAllLocation()
    }
}