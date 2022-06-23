package com.teckzi.rickandmorty.di.component

import android.content.Context
import com.teckzi.rickandmorty.di.modules.*
import com.teckzi.rickandmorty.presentation.di.character.CharacterFragmentComponent
import com.teckzi.rickandmorty.presentation.di.character.details.CharacterDetailComponent
import com.teckzi.rickandmorty.presentation.di.episode.EpisodeFragmentComponent
import com.teckzi.rickandmorty.presentation.di.episode.details.EpisodeDetailComponent
import com.teckzi.rickandmorty.presentation.di.location.LocationFragmentComponent
import com.teckzi.rickandmorty.presentation.di.location.details.LocationDetailComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, RepositoryModule::class, NetworkModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    val context: Context

    val characterFragmentComponent: CharacterFragmentComponent
    val characterDetailComponent: CharacterDetailComponent
    val locationFragmentComponent: LocationFragmentComponent
    val locationDetailComponent: LocationDetailComponent
    val episodeFragmentComponent: EpisodeFragmentComponent
    val episodeDetailComponent: EpisodeDetailComponent
}
