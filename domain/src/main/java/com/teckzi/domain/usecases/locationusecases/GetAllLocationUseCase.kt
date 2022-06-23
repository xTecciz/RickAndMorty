package com.teckzi.domain.usecases.locationusecases

import androidx.paging.PagingData
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetAllLocationUseCase(
    private val repository: IRepository
) {
    operator fun invoke(): Flow<PagingData<LocationModel>> {
        return repository.getAllLocation()
    }
}