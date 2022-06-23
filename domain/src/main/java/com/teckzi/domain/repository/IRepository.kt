package com.teckzi.domain.repository

import androidx.paging.PagingData
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterById(characterId: Int): CharacterModel
    suspend fun getCharacterListById(characterIdList: List<Int>): List<CharacterModel>
    suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>>

    fun getAllLocation(): Flow<PagingData<LocationModel>>
    suspend fun getLocationById(locationId: Int): LocationModel
    suspend fun getSelectedLocationByName(locationName: String): LocationModel?
    suspend fun searchLocation(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>>

    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>
    suspend fun getEpisodeById(episodeId: Int): EpisodeModel
    suspend fun getEpisodeListById(episodeIdList: List<Int>): List<EpisodeModel>
    suspend fun searchEpisode(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>>
}