package com.teckzi.rickandmorty.presentation.di.episode.details

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.di.annotation.ViewModelKey
import com.teckzi.rickandmorty.presentation.screens.episodedetailscreen.EpisodeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EpisodeDetailModule {

    @Binds
    @[IntoMap ViewModelKey(EpisodeDetailViewModel::class)]
    fun bindEpisodeDetailViewModel(episodeDetailViewModel: EpisodeDetailViewModel): ViewModel
}