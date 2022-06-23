package com.teckzi.rickandmorty.di.modules

import androidx.lifecycle.ViewModelProvider
import com.teckzi.rickandmorty.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}