package com.bhavesh.popularmovies_pagination.viewModel

import androidx.lifecycle.ViewModel
import com.bhavesh.popularmovies_pagination.repository.MovieRepository

class MovieViewModels : ViewModel() {

    private val repository = MovieRepository()

    fun searchMovieResults() = repository.getMovieResultList()

}