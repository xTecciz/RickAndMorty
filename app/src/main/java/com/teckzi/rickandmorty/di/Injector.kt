package com.teckzi.rickandmorty.di

import android.content.Context
import com.teckzi.rickandmorty.di.component.AppComponent
import com.teckzi.rickandmorty.di.component.DaggerAppComponent
import com.teckzi.rickandmorty.di.modules.AppModule
import com.teckzi.rickandmorty.presentation.di.character.CharacterFragmentComponent
import com.teckzi.rickandmorty.presentation.di.character.details.CharacterDetailComponent
import com.teckzi.rickandmorty.presentation.di.episode.EpisodeFragmentComponent
import com.teckzi.rickandmorty.presentation.di.episode.details.EpisodeDetailComponent
import com.teckzi.rickandmorty.presentation.di.location.LocationFragmentComponent
import com.teckzi.rickandmorty.presentation.di.location.details.LocationDetailComponent

object Injector {

    private var appComponent: AppComponent? = null

    private var characterFragmentComponent: CharacterFragmentComponent? = null
    private var characterDetailComponent: CharacterDetailComponent? = null
    private var locationFragmentComponent: LocationFragmentComponent? = null
    private var locationDetailComponent: LocationDetailComponent? = null
    private var episodeFragmentComponent: EpisodeFragmentComponent? = null
    private var episodeDetailComponent: EpisodeDetailComponent? = null

    fun createAppComponent(context: Context) {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context))
            .build()
    }

    fun getCharacterFragmentComponent(): CharacterFragmentComponent {
        if (characterFragmentComponent == null) {
            characterFragmentComponent = appComponent!!.characterFragmentComponent
        }
        return characterFragmentComponent!!
    }

    fun clearCharacterFragmentComponent() {
        characterFragmentComponent = null
    }

    fun getCharacterDetailComponent(): CharacterDetailComponent {
        if (characterDetailComponent == null) {
            characterDetailComponent = appComponent!!.characterDetailComponent
        }
        return characterDetailComponent!!
    }

    fun clearCharacterDetailComponent() {
        characterDetailComponent = null
    }

    fun getLocationFragmentComponent(): LocationFragmentComponent {
        if (locationFragmentComponent == null) {
            locationFragmentComponent = appComponent!!.locationFragmentComponent
        }
        return locationFragmentComponent!!
    }

    fun clearLocationFragmentComponent() {
        locationFragmentComponent = null
    }

    fun getLocationDetailComponent(): LocationDetailComponent {
        if (locationDetailComponent == null) {
            locationDetailComponent = appComponent!!.locationDetailComponent
        }
        return locationDetailComponent!!
    }

    fun clearLocationDetailComponent() {
        locationDetailComponent = null
    }

    fun getEpisodeFragmentComponent(): EpisodeFragmentComponent {
        if (episodeFragmentComponent == null) {
            episodeFragmentComponent = appComponent!!.episodeFragmentComponent
        }
        return episodeFragmentComponent!!
    }

    fun clearEpisodeFragmentComponent() {
        episodeFragmentComponent = null
    }

    fun getEpisodeDetailComponent(): EpisodeDetailComponent {
        if (episodeDetailComponent == null) {
            episodeDetailComponent = appComponent!!.episodeDetailComponent
        }
        return episodeDetailComponent!!
    }

    fun clearEpisodeDetailComponent() {
        episodeDetailComponent = null
    }
}