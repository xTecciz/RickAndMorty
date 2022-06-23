package com.teckzi.rickandmorty.data.repository

import com.teckzi.domain.model.CharacterModel
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.mappers.toEpisodeModel
import com.teckzi.rickandmorty.data.mappers.toLocationModel

class LocalDataSourceImpl(rickAndMortyDatabase: RickAndMortyDatabase) : LocalDataSource {

    private val characterDao = rickAndMortyDatabase.characterDao()
    private val locationDao = rickAndMortyDatabase.locationDao()
    private val episodeDao = rickAndMortyDatabase.episodeDao()

    override suspend fun getCharacterByIdLocal(id: Int): CharacterModel {
        return characterDao.getSelectedCharacter(characterId = id).toCharacterModel()
    }

    override suspend fun getLocationByIdLocal(id: Int): LocationModel {
        return locationDao.getSelectedLocation(locationId = id).toLocationModel()
    }

    override suspend fun getEpisodeByIdLocal(id: Int): EpisodeModel {
        return episodeDao.getSelectedEpisode(episodeId = id).toEpisodeModel()
    }

    override suspend fun getCharactersListById(idList: List<Int>): List<CharacterModel> {
        return characterDao.getCharacterList(idList).map { it.toCharacterModel() }
    }

    override suspend fun getEpisodesListById(idList: List<Int>): List<EpisodeModel> {
        return episodeDao.getEpisodeList(idList).map { it.toEpisodeModel() }
    }

    override suspend fun getSelectedLocationByName(locationName: String): LocationModel? {

        return locationDao.getSelectedLocationByName(locationName = locationName)?.toLocationModel()
    }
}