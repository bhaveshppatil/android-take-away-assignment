package com.bhavesh.popularmovies_pagination.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bhavesh.popularmovies_pagination.remote.Network
import com.bhavesh.popularmovies_pagination.response.Results
import com.bhavesh.popularmovies_pagination.response.ResponseMovie

class PagingSource : PagingSource<Int, Results>() {

    private var apiClient = Network.getApiService();

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val pageNumber = params.key ?: 1
        val responseMovie: ResponseMovie = apiClient.getMovieData(pageNumber)
        val resultsList: List<Results> = responseMovie.results

        return try {
            LoadResult.Page(
                data = resultsList,
                prevKey = null,
                nextKey = if (resultsList.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}