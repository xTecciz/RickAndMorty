package com.teckzi.rickandmorty.presentation.di.character.details

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.di.annotation.ViewModelKey
import com.teckzi.rickandmorty.presentation.screens.characterdetailscreen.CharacterDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CharacterDetailModule {

    @Binds
    @[IntoMap ViewModelKey(CharacterDetailViewModel::class)]
    fun bindCharacterDetailViewModel(characterDetailViewModel: CharacterDetailViewModel): ViewModel
}