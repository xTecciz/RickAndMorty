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
import com.teckzi.rickandmorty.data.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            /** Character UseCases */
            getAllCharactersUseCase = GetAllCharactersUseCase(repository),
            getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repository),
            getSearchedCharacterUseCase = GetSearchedCharacterUseCase(repository),
            getCharacterListById = GetCharacterListById(repository),
            /** Episode UseCases */
            getAllEpisodeUseCase = GetAllEpisodeUseCase(repository),
            getSelectedEpisodeUseCase = GetSelectedEpisodeUseCase(repository),
            getSearchedEpisodeUseCase = GetSearchedEpisodeUseCase(repository),
            getEpisodeListById = GetEpisodeListById(repository),
            /** Location UseCases */
            getAllLocationUseCase = GetAllLocationUseCase(repository),
            getSelectedLocationUseCase = GetSelectedLocationUseCase(repository),
            getSearchedLocationUseCase = GetSearchedLocationUseCase(repository),
            getLocationByName = GetLocationByName(repository)
        )
    }
}