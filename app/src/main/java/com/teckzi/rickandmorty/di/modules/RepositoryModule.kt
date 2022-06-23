package com.teckzi.rickandmorty.di.modules

import com.teckzi.domain.usecases.UseCases
import com.teckzi.domain.usecases.characterusecases.GetAllCharactersUseCase
import com.teckzi.domain.usecases.characterusecases.GetCharacterListById
import com.teckzi.domain.usecases.characterusecases.GetSearchedCharacterUseCase
import com.teckzi.domain.usecases.characterusecases.GetSelectedCharacterUseCase
import com.teckzi.domain.usecases.episodeusecases.GetAllEpisodeUseCase
import com.teckzi.domain.usecases.episodeusecases.GetEpisodeListById
import com.teckzi.domain.usecases.episodeusecases.GetSearchedEpisodeUseCase
import com.teckzi.domain.usecases.episodeusecases.GetSelectedEpisodeUseCase
import com.teckzi.domain.usecases.locationusecases.GetAllLocationUseCase
import com.teckzi.domain.usecases.locationusecases.GetLocationByName
import com.teckzi.domain.usecases.locationusecases.GetSearchedLocationUseCase
import com.teckzi.domain.usecases.locationusecases.GetSelectedLocationUseCase
import com.teckzi.rickandmorty.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repositoryImpl: RepositoryImpl): UseCases {
        return UseCases(
            /** Character UseCases */
            getAllCharactersUseCase = GetAllCharactersUseCase(repositoryImpl),
            getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repositoryImpl),
            getSearchedCharacterUseCase = GetSearchedCharacterUseCase(repositoryImpl),
            getCharacterListById = GetCharacterListById(repositoryImpl),
            /** Episode UseCases */
            getAllEpisodeUseCase = GetAllEpisodeUseCase(repositoryImpl),
            getSelectedEpisodeUseCase = GetSelectedEpisodeUseCase(repositoryImpl),
            getSearchedEpisodeUseCase = GetSearchedEpisodeUseCase(repositoryImpl),
            getEpisodeListById = GetEpisodeListById(repositoryImpl),
            /** Location UseCases */
            getAllLocationUseCase = GetAllLocationUseCase(repositoryImpl),
            getSelectedLocationUseCase = GetSelectedLocationUseCase(repositoryImpl),
            getSearchedLocationUseCase = GetSearchedLocationUseCase(repositoryImpl),
            getLocationByName = GetLocationByName(repositoryImpl)
        )
    }
}