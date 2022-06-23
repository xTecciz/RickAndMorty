package com.teckzi.rickandmorty.presentation.di.episode

import com.teckzi.rickandmorty.di.annotation.FragmentScope
import com.teckzi.rickandmorty.presentation.screens.episodescreen.EpisodeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [EpisodeFragmentModule::class])
interface EpisodeFragmentComponent {
    fun inject(episodeFragment: EpisodeFragment)
}