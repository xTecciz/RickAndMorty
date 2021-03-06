package com.teckzi.rickandmorty.presentation.screens.locationdetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.usecases.UseCases
import com.teckzi.rickandmorty.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _selectedLocation: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val selectedLocation: StateFlow<LocationModel?> = _selectedLocation
    private val _characterList: MutableStateFlow<List<CharacterModel>> =
        MutableStateFlow(emptyList())
    val characterList: StateFlow<List<CharacterModel>> = _characterList

    fun getLocation(locationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedLocation.value =
                locationId.let { useCases.getSelectedLocationUseCase(locationId = locationId) }

            val listOfCharacterIds = _selectedLocation.value!!.characters
            _characterList.value = listOfCharacterIds.let {
                useCases.getCharacterListById(characterIdList = it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearLocationDetailComponent()
    }
}