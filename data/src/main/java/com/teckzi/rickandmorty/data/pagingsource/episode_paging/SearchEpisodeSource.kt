package com.teckzi.rickandmorty.data.pagingsource.episode_paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toEpisodeDbo
import com.teckzi.rickandmorty.data.mappers.toEpisodeModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.util.convertStringToRoomSearch
import javax.inject.Inject

class SearchEpisodeSource @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase,
    private val name: String?,
    private val episode: String?
) : PagingSource<Int, EpisodeModel>() {

    private val episodeDao = rickAndMortyDatabase.episodeDao()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        var results: List<EpisodeModel>

        val page: Int = params.key ?: 1
        val pageSize = params.loadSize
        var nextKey: Int? = null

        try {
            rickAndMortyApi.getEpisodes(page = page, name = name, episode = episode).apply {

                if (this.info.next != null) {
                    val uri = Uri.parse(this.info.next)
                    val nextPage = uri.getQueryParameter("page")
                    nextKey = nextPage?.toInt()
                }

                results = this.results.map { it.toEpisodeModel() }

                results.let { characterList ->
                    episodeDao.addEpisodes(characterList.map { it.toEpisodeDbo() })
                }

            }
        } catch (e: Exception) {
            episodeDao.getFilteredEpisodes(
                name = name?.convertStringToRoomSearch(),
                episode = episode?.convertStringToRoomSearch()
            ).apply {
                nextKey = if (size < pageSize) null else nextKey?.plus(1)
                results = this.map { it.toEpisodeModel() }
            }
        }

        return LoadResult.Page(data = results, prevKey = null, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
