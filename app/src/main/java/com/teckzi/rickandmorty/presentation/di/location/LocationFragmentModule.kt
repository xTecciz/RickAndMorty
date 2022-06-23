package com.teckzi.rickandmorty.presentation.di.location

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.di.annotation.ViewModelKey
import com.teckzi.rickandmorty.presentation.screens.locationscreen.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LocationFragmentModule {

    @Binds
    @[IntoMap ViewModelKey(LocationViewModel::class)]
    fun bindLocationFragmentViewModel(locationViewModel: LocationViewModel): ViewModel
}