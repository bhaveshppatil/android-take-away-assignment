package com.bhavesh.popularmovies_pagination.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
This class helps us to hit the api using Retrofit builder
After receiving the response, we convert it into Gson using GsonConvertorFactory
 */

object Network {
        private const val BASE_URL = "https://api.themoviedb.org/"

        private val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                .build()
        }

    fun getApiService(): ApiClient = getInstance().create(ApiClient::class.java)
}