package com.bhavesh.popularmovies_pagination.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bhavesh.popularmovies_pagination.clicklistener.ClickListener
import com.bhavesh.popularmovies_pagination.R
import com.bhavesh.popularmovies_pagination.response.Results
import com.bhavesh.popularmovies_pagination.databinding.MovieItemLayoutBinding
import com.bhavesh.popularmovies_pagination.viewHolder.MovieDataHolder

//adapter for our recycler view
class MovieAdapter(val clickListener: ClickListener) : PagingDataAdapter<Results, MovieDataHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MovieDataHolder, position: Int) {
        val movieResultsList = getItem(position)
        movieResultsList?.let {
            holder.setData(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataHolder {
        val view = LayoutInflater.from(parent.context)
        val itemLayoutBinding = DataBindingUtil.inflate<MovieItemLayoutBinding>(view, R.layout.movie_item_layout, parent, false)
        return MovieDataHolder(itemLayoutBinding, clickListener)
    }
}