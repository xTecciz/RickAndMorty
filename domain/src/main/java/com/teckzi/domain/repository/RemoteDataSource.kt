package com.teckzi.domain.repository

import androidx.paging.PagingData
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    fun getAllLocations(): Flow<PagingData<LocationModel>>
    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>

    suspend fun getCharacterByIdRemote(id: Int): CharacterModel
    suspend fun getLocationByIdRemote(id: Int): LocationModel
    suspend fun getEpisodeByIdRemote(id: Int): EpisodeModel

    suspend fun getCharacterListById(charactersIds: List<Int>): List<CharacterModel>
    suspend fun getEpisodeListById(episodesIds: List<Int>): List<EpisodeModel>

    suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>>

    suspend fun searchLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>>

    suspend fun searchEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>>

}