package com.teckzi.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.RemoteDataSource
import com.teckzi.rickandmorty.util.Constants.ITEMS_PER_PAGE
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.mappers.toEpisodeModel
import com.teckzi.rickandmorty.data.mappers.toLocationModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.data.pagingsource.character_paging.CharacterPagingSource
import com.teckzi.rickandmorty.data.pagingsource.character_paging.SearchCharacterSource
import com.teckzi.rickandmorty.data.pagingsource.episode_paging.EpisodePagingSource
import com.teckzi.rickandmorty.data.pagingsource.episode_paging.SearchEpisodeSource
import com.teckzi.rickandmorty.data.pagingsource.location_paging.LocationPagingSource
import com.teckzi.rickandmorty.data.pagingsource.location_paging.SearchLocationSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteDataSource {

    override fun getAllCharacters() = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            CharacterPagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override fun getAllLocations() = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            LocationPagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override fun getAllEpisodes() = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
        pagingSourceFactory = {
            EpisodePagingSource(
                rickAndMortyApi = rickAndMortyApi,
                rickAndMortyDatabase = rickAndMortyDatabase
            )
        }
    ).flow

    override suspend fun getCharacterByIdRemote(id: Int): CharacterModel {
        return rickAndMortyApi.getCharacterById(id).toCharacterModel()
    }

    override suspend fun getLocationByIdRemote(id: Int): LocationModel {
        return rickAndMortyApi.getLocationById(id).toLocationModel()
    }

    override suspend fun getEpisodeByIdRemote(id: Int): EpisodeModel {
        return rickAndMortyApi.getEpisodeById(id).toEpisodeModel()
    }

    override suspend fun getCharacterListById(charactersIds: List<Int>): List<CharacterModel> {
        val ids = charactersIds.toString().split("[")[1].substringBefore("]")
        val response = rickAndMortyApi.getCharacterListById(ids)
        return response.map { it.toCharacterModel() }
    }

    override suspend fun getEpisodeListById(episodesIds: List<Int>): List<EpisodeModel> {
        val ids = episodesIds.toString().split("[")[1].substringBefore("]")
        val response = rickAndMortyApi.getEpisodeListById(ids)
        return response.map { it.toEpisodeModel() }
    }

    override suspend fun searchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchCharacterSource(
                    rickAndMortyApi = rickAndMortyApi,
                    rickAndMortyDatabase = rickAndMortyDatabase,
                    name = name,
                    status = status,
                    species = species,
                    type = type,
                    gender = gender
                )
            }
        ).flow
    }

    override suspend fun searchLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchLocationSource(
                    rickAndMortyApi = rickAndMortyApi,
                    rickAndMortyDatabase = rickAndMortyDatabase,
                    name = name,
                    type = type,
                    dimension = dimension
                )
            }
        ).flow
    }

    override suspend fun searchEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchEpisodeSource(
                    rickAndMortyApi = rickAndMortyApi,
                    rickAndMortyDatabase = rickAndMortyDatabase,
                    name = name,
                    episode = episode
                )
            }
        ).flow
    }

}