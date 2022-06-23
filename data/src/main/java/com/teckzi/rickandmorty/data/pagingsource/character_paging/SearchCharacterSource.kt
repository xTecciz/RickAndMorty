package com.teckzi.rickandmorty.data.pagingsource.character_paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.teckzi.domain.model.CharacterModel
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterDbo
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.util.convertStringToRoomSearch
import javax.inject.Inject

class SearchCharacterSource @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val type: String?,
    private val gender: String?
) : PagingSource<Int, CharacterModel>() {

    private val characterDao = rickAndMortyDatabase.characterDao()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        var results: List<CharacterModel>

        val page: Int = params.key ?: 1
        val pageSize = params.loadSize
        var nextKey: Int? = null

        try {
            rickAndMortyApi.getCharacters(
                page = page,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender
            ).apply {
                if (this.info.next != null) {
                    val uri = Uri.parse(this.info.next)
                    val nextPage = uri.getQueryParameter("page")
                    nextKey = nextPage?.toInt()
                }
                results = this.results.map { it.toCharacterModel() }
                results.let { characterList ->
                    characterDao.addCharacters(characterList.map { it.toCharacterDbo() })
                }
            }
        } catch (e: Exception) {
            characterDao.getFilteredCharacters(
                name = name?.convertStringToRoomSearch(),
                status = status?.convertStringToRoomSearch(),
                species = species?.convertStringToRoomSearch(),
                type = type?.convertStringToRoomSearch(),
                gender = gender?.convertStringToRoomSearch()
            ).apply {
                nextKey = if (size < pageSize) null else nextKey?.plus(1)
                results = this.map { it.toCharacterModel() }
            }
        }

        return LoadResult.Page(data = results, prevKey = null, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
