package com.teckzi.rickandmorty.di.modules

import android.content.Context
import androidx.room.Room
import com.teckzi.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.repository.LocalDataSourceImpl
import com.teckzi.rickandmorty.util.Constants.RICK_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): RickAndMortyDatabase {
        return Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            RICK_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        rickAndMortyDatabase: RickAndMortyDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            rickAndMortyDatabase = rickAndMortyDatabase
        )
    }

}