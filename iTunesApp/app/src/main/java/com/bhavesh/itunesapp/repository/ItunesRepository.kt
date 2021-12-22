package com.bhavesh.itunesapp.repository

import androidx.lifecycle.LiveData
import com.bhavesh.itunesapp.di.Module
import com.bhavesh.itunesapp.remote.model.ItunesResponse
import com.masai.movieapp.Remote.Resource
import com.masai.movieapp.Remote.ResponseHandler
import com.masai.movieapp.room.ITunesDAO
import com.masai.movieapp.room.ITunesTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItunesRepository @Inject constructor(private val iTunesDAO: ITunesDAO) {


    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getDataFromAPI(term: String): Resource<ItunesResponse> {
        return try {
            val itunesResponse: ItunesResponse = Module.getApiService().getITunesData(term)
            responseHandler.handleSuccess(itunesResponse)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun addItunesDataToRoom(ITunesData: ITunesTable) {
        CoroutineScope(Dispatchers.IO).launch {
            iTunesDAO.insertData(ITunesData)
        }
    }

    fun deleteDataFromDB(){
        iTunesDAO.deleteDataFromDB()
    }


    //livedata which provides the list of data present inside database
    fun displayList(): LiveData<ITunesTable> {
        return iTunesDAO.getDataFromDB()
    }
}