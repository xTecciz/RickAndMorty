package com.teckzi.domain.repository

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel

interface LocalDataSource {
    suspend fun getCharacterByIdLocal(id: Int): CharacterModel
    suspend fun getLocationByIdLocal(id: Int): LocationModel
    suspend fun getEpisodeByIdLocal(id: Int): EpisodeModel

    suspend fun getCharactersListById(idList: List<Int>): List<CharacterModel>
    suspend fun getEpisodesListById(idList: List<Int>): List<EpisodeModel>

    suspend fun getSelectedLocationByName(locationName: String): LocationModel?
}