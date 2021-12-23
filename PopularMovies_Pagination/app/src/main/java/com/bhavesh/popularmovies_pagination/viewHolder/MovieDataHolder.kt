package com.bhavesh.popularmovies_pagination.viewHolder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.popularmovies_pagination.clicklistener.ClickListener
import com.bhavesh.popularmovies_pagination.R
import com.bhavesh.popularmovies_pagination.response.Results
import com.bhavesh.popularmovies_pagination.databinding.MovieItemLayoutBinding
import com.bumptech.glide.Glide

//ViewHolder class which holds the data in our recycler view
class MovieDataHolder(
    private val itemLayoutBinding: MovieItemLayoutBinding,
    private val clickListener: ClickListener
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    val moviePoster: ImageView = itemView.findViewById(R.id.ivPoster)
    val tvMovieVote: TextView = itemView.findViewById(R.id.tvMovieVote)
    val imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"

    fun setData(results: Results) {
        itemLayoutBinding.clickListener = clickListener
        itemLayoutBinding.results = results
        tvMovieVote.text = results.vote_count.toString()
        Glide.with(moviePoster).load(imagePath + results.poster_path).into(moviePoster)
    }
}