package com.teckzi.rickandmorty.presentation.screens.characterdetailscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.usecases.UseCases
import com.teckzi.rickandmorty.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _selectedCharacter: MutableStateFlow<CharacterModel?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<CharacterModel?> = _selectedCharacter
    private val _episodeList: MutableStateFlow<List<EpisodeModel?>> = MutableStateFlow(emptyList())
    val episodeList: StateFlow<List<EpisodeModel?>> = _episodeList
    private val _characterOrigin: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val characterOrigin: StateFlow<LocationModel?> = _characterOrigin
    private var _characterLocation: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val characterLocation: StateFlow<LocationModel?> = _characterLocation

    fun getCharacter(characterId: Int) {
        Log.d("TAG viewModel", "id = $characterId")

        viewModelScope.launch(Dispatchers.IO) {
            _selectedCharacter.value =
                characterId.let { useCases.getSelectedCharacterUseCase(characterId = characterId) }

            val listOfEpisodeIds = _selectedCharacter.value!!.episode
            _episodeList.value = listOfEpisodeIds.let {
                useCases.getEpisodeListById(episodeIdList = listOfEpisodeIds)
            }
            val origin = _selectedCharacter.value!!.origin
            val location = _selectedCharacter.value!!.location
            if (origin != "unknown") _characterOrigin.value =
                useCases.getLocationByName(locationName = origin)
            if (location != "unknown") _characterLocation.value =
                useCases.getLocationByName(locationName = location)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearCharacterDetailComponent()
    }
}
