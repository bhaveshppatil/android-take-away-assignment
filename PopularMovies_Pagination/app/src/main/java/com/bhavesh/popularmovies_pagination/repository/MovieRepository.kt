package com.bhavesh.popularmovies_pagination.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bhavesh.popularmovies_pagination.Paging.PagingSource

class MovieRepository {

    fun getMovieResultList() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
//                maxSize = 50
            ), pagingSourceFactory = { PagingSource() }
        ).liveData
}