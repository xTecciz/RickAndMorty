package com.teckzi.rickandmorty.presentation.di.episode.details

import com.teckzi.rickandmorty.di.annotation.FragmentScope
import com.teckzi.rickandmorty.presentation.screens.episodedetailscreen.EpisodeDetailFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [EpisodeDetailModule::class])
interface EpisodeDetailComponent {
    fun inject(episodeDetailFragment: EpisodeDetailFragment)
}