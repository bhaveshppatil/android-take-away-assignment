package com.bhavesh.itunesapp.remote

import com.bhavesh.itunesapp.remote.model.ItunesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//this interface specifies the request type as GET

interface ApiService {

    @GET("search")
     suspend fun getITunesData(@Query("term") term : String): ItunesResponse
}