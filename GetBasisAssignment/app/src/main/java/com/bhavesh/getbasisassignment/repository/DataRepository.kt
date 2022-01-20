package com.bhavesh.getbasisassignment.repository

import com.bhavesh.getbasisassignment.di.DataModule
import com.bhavesh.getbasisassignment.remote.ApiService
import com.bhavesh.getbasisassignment.remote.Resource
import com.bhavesh.getbasisassignment.remote.ResponseHandler
import com.bhavesh.getbasisassignment.remote.model.ResponseModel
import com.google.gson.Gson
import javax.inject.Inject

class DataRepository @Inject constructor(val apiService: ApiService) {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getDataFromAPI(): Resource<ResponseModel> {
        return try {
            val responseModel = DataModule.provideApiService().getData()
            val response: String = responseModel.substring(1)
            val dataResponse = Gson().fromJson(response, ResponseModel::class.java)
            responseHandler.handleSuccess(dataResponse)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}