package com.teckzi.domain.usecases.characterusecases

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.repository.IRepository

class GetSelectedCharacterUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(characterId: Int): CharacterModel {
        return repository.getCharacterById(characterId)
    }
}