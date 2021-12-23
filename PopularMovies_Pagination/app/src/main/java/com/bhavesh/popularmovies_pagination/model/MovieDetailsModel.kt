package com.bhavesh.popularmovies_pagination.model

data class MovieDetailsModel(
    val title: String,
    val overview: String,
    val language: String,
    val release_date: String,
    val vote_average: String,
    val vote_count: String,
    val poster_path: String,

    ) {
}