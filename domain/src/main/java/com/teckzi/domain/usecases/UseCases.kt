package com.teckzi.domain.usecases

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

data class UseCases(
    /** Character UseCases */
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getSelectedCharacterUseCase: GetSelectedCharacterUseCase,
    val getSearchedCharacterUseCase: GetSearchedCharacterUseCase,
    val getCharacterListById: GetCharacterListById,
    /** Episode UseCases */
    val getAllEpisodeUseCase: GetAllEpisodeUseCase,
    val getSelectedEpisodeUseCase: GetSelectedEpisodeUseCase,
    val getSearchedEpisodeUseCase: GetSearchedEpisodeUseCase,
    val getEpisodeListById: GetEpisodeListById,
    /** Location UseCases */
    val getAllLocationUseCase: GetAllLocationUseCase,
    val getSelectedLocationUseCase: GetSelectedLocationUseCase,
    val getSearchedLocationUseCase: GetSearchedLocationUseCase,
    val getLocationByName: GetLocationByName
)