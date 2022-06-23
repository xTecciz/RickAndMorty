package com.teckzi.rickandmorty.presentation.di.episode

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.di.annotation.ViewModelKey
import com.teckzi.rickandmorty.presentation.screens.episodescreen.EpisodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EpisodeFragmentModule {

    @Binds
    @[IntoMap ViewModelKey(EpisodeViewModel::class)]
    fun bindEpisodeFragmentViewModel(episodeViewModel: EpisodeViewModel): ViewModel
}