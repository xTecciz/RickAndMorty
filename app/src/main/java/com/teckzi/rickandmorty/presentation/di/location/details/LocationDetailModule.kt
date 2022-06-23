package com.teckzi.rickandmorty.presentation.di.location.details

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.di.annotation.ViewModelKey
import com.teckzi.rickandmorty.presentation.screens.locationdetailscreen.LocationDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LocationDetailModule {

    @Binds
    @[IntoMap ViewModelKey(LocationDetailViewModel::class)]
    fun bindLocationDetailViewModel(locationDetailViewModel: LocationDetailViewModel): ViewModel
}