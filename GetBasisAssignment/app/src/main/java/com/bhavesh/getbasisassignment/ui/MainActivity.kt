package com.bhavesh.getbasisassignment.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bhavesh.getbasisassignment.R
import com.bhavesh.getbasisassignment.remote.Status
import com.bhavesh.getbasisassignment.remote.model.Data
import com.bhavesh.getbasisassignment.ui.adapter.DataAdapter
import com.bhavesh.getbasisassignment.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dataAdapter: DataAdapter
    private val dataViewModel: DataViewModel by viewModels()
    private var dataList = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDatFromApi()
    }

    private fun loadDatFromApi() {
        dataViewModel.getDataFromApi().observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    showToast("network error..")
                }
                Status.SUCCESS -> {
                    it.data?.data?.let {
                        dataList.addAll(it)
                        setDataAdapter()
                    }
                }
            }
        })
    }

    private fun setDataAdapter() {
        dataAdapter = DataAdapter(viewPager, dataList)
        viewPager.adapter = dataAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}