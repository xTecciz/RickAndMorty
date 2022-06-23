package com.teckzi.domain.usecases.characterusecases

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.repository.Repository

class GetSelectedCharacterUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(characterId: Int): CharacterModel {
        return repository.getCharacterById(characterId)
    }
}