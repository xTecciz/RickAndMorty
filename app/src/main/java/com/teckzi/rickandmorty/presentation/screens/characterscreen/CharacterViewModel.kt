package com.teckzi.rickandmorty.presentation.screens.characterscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.usecases.UseCases
import com.teckzi.rickandmorty.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _getAllCharacters = useCases.getAllCharactersUseCase()
    val getAllCharacters = _getAllCharacters

    private val _searchCharacter = MutableStateFlow<PagingData<CharacterModel>>(PagingData.empty())
    val searchCharacter = _searchCharacter

    fun searchCharacter(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getSearchedCharacterUseCase(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender
            ).cachedIn(viewModelScope).collect {
                _searchCharacter.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearCharacterFragmentComponent()
    }
}