package com.bhavesh.itunesapp.remote.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.itunesapp.R
import com.bhavesh.itunesapp.remote.model.Results
import com.bumptech.glide.Glide

//ViewHolder class which holds the data in our recycler view
class ITunesDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val trackName: TextView = itemView.findViewById(R.id.trackName)
    val crdTrackView: CardView = itemView.findViewById(R.id.crdTrackView)
    val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)

    fun setData(results: Results) {
        Glide.with(ivThumbnail).load(results.artworkUrl100).into(ivThumbnail)
    }
}