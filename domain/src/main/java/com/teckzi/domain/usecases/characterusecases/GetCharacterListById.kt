package com.teckzi.domain.usecases.characterusecases

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.repository.Repository

class GetCharacterListById(
    private val repository: Repository
) {
    suspend operator fun invoke(characterIdList: List<Int>): List<CharacterModel> {
        return repository.getCharacterListById(characterIdList)
    }
}