package com.bhavesh.getbasisassignment.remote

import com.bhavesh.getbasisassignment.extras.Utils.END_POINT
import com.bhavesh.getbasisassignment.remote.model.ResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
   suspend fun getData(): String

}