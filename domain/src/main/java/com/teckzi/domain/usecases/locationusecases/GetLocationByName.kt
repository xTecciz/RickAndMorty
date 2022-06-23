package com.teckzi.domain.usecases.locationusecases

import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.IRepository

class GetLocationByName(
    private val repository: IRepository
) {
    suspend operator fun invoke(locationName: String): LocationModel? {
        return repository.getSelectedLocationByName(locationName)
    }
}