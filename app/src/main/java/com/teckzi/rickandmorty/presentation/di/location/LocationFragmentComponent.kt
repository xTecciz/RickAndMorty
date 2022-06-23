package com.teckzi.rickandmorty.presentation.di.location

import com.teckzi.rickandmorty.di.annotation.FragmentScope
import com.teckzi.rickandmorty.presentation.screens.locationscreen.LocationFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [LocationFragmentModule::class])
interface LocationFragmentComponent {
    fun inject(locationFragment: LocationFragment)
}