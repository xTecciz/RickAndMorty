package com.teckzi.rickandmorty.data.pagingsource.location_paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.teckzi.domain.model.LocationModel
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toLocationDbo
import com.teckzi.rickandmorty.data.mappers.toLocationModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.util.convertStringToRoomSearch
import javax.inject.Inject

class SearchLocationSource @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase,
    private val name: String?,
    private val type: String?,
    private val dimension: String?
) : PagingSource<Int, LocationModel>() {

    private val locationDao = rickAndMortyDatabase.locationDao()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationModel> {
        var results: List<LocationModel>

        val page: Int = params.key ?: 1
        val pageSize = params.loadSize
        var nextKey: Int? = null

        try {
            rickAndMortyApi.getLocations(
                page = page,
                name = name,
                type = type,
                dimension = dimension
            ).apply {

                if (this.info.next != null) {
                    val uri = Uri.parse(this.info.next)
                    val nextPage = uri.getQueryParameter("page")
                    nextKey = nextPage?.toInt()
                }

                results = this.results.map { it.toLocationModel() }

                results.let { characterList ->
                    locationDao.addLocations(characterList.map { it.toLocationDbo() })
                }

            }
        } catch (e: Exception) {
            locationDao.getFilteredLocations(
                name = name?.convertStringToRoomSearch(),
                type = type?.convertStringToRoomSearch(),
                dimension = dimension?.convertStringToRoomSearch()
            ).apply {
                nextKey = if (size < pageSize) null else nextKey?.plus(1)
                results = this.map { it.toLocationModel() }
            }
        }

        return LoadResult.Page(data = results, prevKey = null, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, LocationModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
