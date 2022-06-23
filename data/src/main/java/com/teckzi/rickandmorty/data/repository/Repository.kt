package com.teckzi.rickandmorty.data.repository

import androidx.paging.PagingData
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.IRepository
import com.teckzi.domain.repository.LocalDataSource
import com.teckzi.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : IRepository {
    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return remote.getAllCharacters()
    }

    override fun getAllLocation(): Flow<PagingData<LocationModel>> {
        return remote.getAllLocations()
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return remote.getAllEpisodes()
    }


    override suspend fun getCharacterById(characterId: Int): CharacterModel {
        return try {
            remote.getCharacterByIdRemote(id = characterId)
        } catch (e: Exception) {
            local.getCharacterByIdLocal(id = characterId)
        }
    }

    override suspend fun getLocationById(locationId: Int): LocationModel {
        return try {
            remote.getLocationByIdRemote(id = locationId)
        } catch (e: Exception) {
            local.getLocationByIdLocal(id = locationId)
        }
    }

    override suspend fun getEpisodeById(episodeId: Int): EpisodeModel {
        return try {
            remote.getEpisodeByIdRemote(id = episodeId)
        } catch (e: Exception) {
            local.getEpisodeByIdLocal(id = episodeId)
        }
    }


    override suspend fun getCharacterListById(characterIdList: List<Int>): List<CharacterModel> {
        return try {
            remote.getCharacterListById(characterIdList)
        } catch (e: Exception) {
            local.getCharactersListById(characterIdList)
        }
    }

    override suspend fun getEpisodeListById(episodeIdList: List<Int>): List<EpisodeModel> {
        return try {
            remote.getEpisodeListById(episodeIdList)
        } catch (e: Exception) {
            local.getEpisodesListById(episodeIdList)
        }
    }


    override suspend fun getSelectedLocationByName(locationName: String): LocationModel? {
        return local.getSelectedLocationByName(locationName = locationName)
    }


    override suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>> {
        return remote.searchCharacters(
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender
        )
    }

    override suspend fun searchLocation(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {
        return remote.searchLocations(
            name = name,
            type = type,
            dimension = dimension
        )
    }

    override suspend fun searchEpisode(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return remote.searchEpisodes(
            name = name,
            episode = episode
        )
    }
}