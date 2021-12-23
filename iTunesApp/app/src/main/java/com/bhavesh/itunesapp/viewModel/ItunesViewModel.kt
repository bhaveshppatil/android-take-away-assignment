package com.bhavesh.itunesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bhavesh.itunesapp.remote.model.ItunesResponse
import com.bhavesh.itunesapp.repository.ItunesRepository
import com.masai.movieapp.Remote.Resource
import com.bhavesh.itunesapp.room.ITunesTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ItunesViewModel @Inject constructor(private val repository: ItunesRepository) : ViewModel() {

    fun getDataFromAPI(term: String): LiveData<Resource<ItunesResponse>> {
        return liveData(Dispatchers.IO) {
            val data = repository.getDataFromAPI(term = term)
            emit(data)
        }
    }

    fun displayList(): LiveData<ITunesTable> {
        return repository.displayList()
    }

    fun insertData(iTunesTable: ITunesTable) {

        repository.addItunesDataToRoom(iTunesTable)
    }

    fun deleteData() {
        repository.deleteDataFromDB()
    }
}