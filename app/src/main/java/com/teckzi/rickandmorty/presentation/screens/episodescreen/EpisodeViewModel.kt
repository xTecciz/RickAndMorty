package com.teckzi.rickandmorty.presentation.screens.episodescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.usecases.UseCases
import com.teckzi.rickandmorty.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class EpisodeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _getAllEpisodes = useCases.getAllEpisodeUseCase()
    val getAllEpisodes = _getAllEpisodes

    private val _searchEpisode = MutableStateFlow<PagingData<EpisodeModel>>(PagingData.empty())
    val searchEpisode = _searchEpisode

    fun searchEpisode(
        name: String?,
        episode: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getSearchedEpisodeUseCase(
                name = name,
                episode = episode
            ).cachedIn(viewModelScope).collectLatest {
                _searchEpisode.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearEpisodeFragmentComponent()
    }
}