package com.teckzi.rickandmorty.data.network

import com.teckzi.rickandmorty.data.network.model.ApiResponse
import com.teckzi.rickandmorty.data.network.model.CharacterDto
import com.teckzi.ricks.data.network.model.EpisodeDto
import com.teckzi.ricks.data.network.model.LocationDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): ApiResponse<CharacterDto>

    @GET("character/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int
    ): CharacterDto

    @GET("character/{characterId}")
    suspend fun getCharacterListById(
        @Path("characterId") ids: String
    ): List<CharacterDto>


    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int = 1,
        @Query("name") name: String? = null,
        @Query("type") type: String? = null,
        @Query("dimension") dimension: String? = null
    ): ApiResponse<LocationDto>

    @GET("location/{locationId}")
    suspend fun getLocationById(
        @Path("locationId") id: Int
    ): LocationDto

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int = 1,
        @Query("name") name: String? = null,
        @Query("episode") episode: String? = null
    ): ApiResponse<EpisodeDto>

    @GET("episode/{episodeId}")
    suspend fun getEpisodeListById(
        @Path("episodeId") id: String
    ): List<EpisodeDto>

    @GET("episode/{episodeId}")
    suspend fun getEpisodeById(
        @Path("episodeId") id: Int
    ): EpisodeDto
}