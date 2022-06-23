package com.teckzi.rickandmorty.presentation.di.character

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.di.annotation.ViewModelKey
import com.teckzi.rickandmorty.presentation.screens.characterscreen.CharacterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CharacterFragmentModule {

    @Binds
    @[IntoMap ViewModelKey(CharacterViewModel::class)]
    fun bindCharacterFragmentViewModel(characterViewModel: CharacterViewModel): ViewModel
}