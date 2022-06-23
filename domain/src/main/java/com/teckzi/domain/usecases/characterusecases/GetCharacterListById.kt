package com.teckzi.domain.usecases.characterusecases

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.repository.IRepository

class GetCharacterListById(
    private val repository: IRepository
) {
    suspend operator fun invoke(characterIdList: List<Int>): List<CharacterModel> {
        return repository.getCharacterListById(characterIdList)
    }
}