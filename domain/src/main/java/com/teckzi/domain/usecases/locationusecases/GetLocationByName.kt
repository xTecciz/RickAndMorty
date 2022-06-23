package com.teckzi.domain.usecases.locationusecases

import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.Repository

class GetLocationByName(
    private val repository: Repository
) {
    suspend operator fun invoke(locationName: String): LocationModel? {
        return repository.getSelectedLocationByName(locationName)
    }
}