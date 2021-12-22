package com.bhavesh.itunesapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bhavesh.itunesapp.R
import com.bhavesh.itunesapp.databinding.ActivityMainBinding
import com.bhavesh.itunesapp.remote.adapter.ITunesAdapter
import com.bhavesh.itunesapp.remote.clicklistener.ClickListener
import com.bhavesh.itunesapp.remote.model.Results
import com.bhavesh.itunesapp.viewModel.ItunesViewModel
import com.masai.movieapp.Remote.Status
import com.masai.movieapp.room.ITunesTable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ClickListener {

    private val itunesViewModel: ItunesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var itunesAdapter: ITunesAdapter
    private val resultList = mutableListOf<Results>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setRecyclerView();

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false;
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                loadApi(newText.toString())
                return false;
            }
        })
    }

    fun loadApi(query: String) {
        itunesViewModel.getDataFromAPI(query).observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
                    itunesViewModel.displayList()
                }
                Status.SUCCESS -> {
                    resultList.clear()
                    it.data?.results?.let {

                        resultList.addAll(it)
                        itunesAdapter.notifyDataSetChanged()
                    }
                    CoroutineScope(Dispatchers.Default).launch {
                        it.data?.results?.let { it1 -> insertDataToDb(it1) }
                    }
                }
            }
        })
        itunesViewModel.getDataFromAPI(query)
    }

    private fun insertDataToDb(resultModels: List<Results>) {

        resultModels.forEach {
            val iTunesTable = ITunesTable(
                it.trackName,
                it.artistName,
                it.artworkUrl100,
                it.trackViewUrl
            )
            itunesViewModel.insertData(iTunesTable)
        }
    }

    private fun setRecyclerView() {
        itunesAdapter = ITunesAdapter(this, resultList, this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = itunesAdapter
        }
    }

    override fun onTrackClick(results: Results) {

        val intent = Intent(this, TrackDetails::class.java)
        intent.putExtra("trackName", results.trackName)
        intent.putExtra("artistName", results.artistName)
        intent.putExtra("artworkUrl100", results.artworkUrl100)
        intent.putExtra("releaseDate", results.releaseDate)
        intent.putExtra("trackViewUrl", results.trackViewUrl)
        intent.putExtra("trackName", results.trackTimeMillis)
        startActivity(intent)

    }
}
