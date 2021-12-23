package com.bhavesh.popularmovies_pagination.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhavesh.popularmovies_pagination.Adapter.MovieAdapter
import com.bhavesh.popularmovies_pagination.clicklistener.ClickListener
import com.bhavesh.popularmovies_pagination.R
import com.bhavesh.popularmovies_pagination.response.Results
import com.bhavesh.popularmovies_pagination.databinding.ActivityMainBinding
import com.bhavesh.popularmovies_pagination.viewModel.MovieViewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var movieViewModels: MovieViewModels
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        movieViewModels = ViewModelProviders.of(this).get(MovieViewModels::class.java)
        setRecyclerView()
        movieViewModels.searchMovieResults().observe(this, Observer {
            it?.let {
                CoroutineScope(Main).launch {
                    movieAdapter.submitData(it)
                }
            }
        })
    }

    private fun setRecyclerView() {
        movieAdapter = MovieAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this)

        dataBinding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = linearLayoutManager
        }

    }

    override fun onClick(results: Results) {
        val imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"
        val moviePath = "https://www.themoviedb.org/movie/"

        val intent = Intent(this, MovieDetails::class.java)
        intent.putExtra("title", results.title)
        intent.putExtra("overview", results.overview)
        intent.putExtra("language", results.original_language)
        intent.putExtra("release_date", results.release_date)
        intent.putExtra("vote_average", results.vote_average)
        intent.putExtra("vote_count", results.vote_count)
        intent.putExtra("movie_id", "${moviePath + results.id}")
        intent.putExtra("poster_path", imagePath + results.poster_path)

        startActivity(intent)
    }
}