package com.bhavesh.popularmovies_pagination.remote

import com.bhavesh.popularmovies_pagination.response.ResponseMovie
import retrofit2.http.GET
import retrofit2.http.Query

//this interface specifies the request type as GET

interface ApiClient {

    @GET("/3/movie/popular?api_key=API_Key")
    suspend fun getMovieData(@Query("page") page: Int): ResponseMovie
}
