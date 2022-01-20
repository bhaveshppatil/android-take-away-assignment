package com.bhavesh.getbasisassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bhavesh.getbasisassignment.remote.Resource
import com.bhavesh.getbasisassignment.remote.model.ResponseModel
import com.bhavesh.getbasisassignment.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {


    fun getDataFromApi(): LiveData<Resource<ResponseModel>> {
        return liveData(Dispatchers.IO) {
            val data = dataRepository.getDataFromAPI()
            emit(data)
        }
    }
}