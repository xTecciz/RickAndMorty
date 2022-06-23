package com.teckzi.domain.usecases.locationusecases

import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.IRepository

class GetSelectedLocationUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(locationId: Int): LocationModel {
        return repository.getLocationById(locationId)
    }
}