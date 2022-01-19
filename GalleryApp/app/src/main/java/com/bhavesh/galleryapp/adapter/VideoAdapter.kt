package com.bhavesh.galleryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.galleryapp.R
import com.bhavesh.galleryapp.inteface.OnItemClick
import com.bhavesh.galleryapp.viewholder.ImageViewHolder
import com.bhavesh.galleryapp.viewholder.VideoViewHolder
import java.io.File


class VideoAdapter(
    val context: Context,
    val videoList: MutableList<String>,
    val onItemClick: OnItemClick
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.image_item_layout, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoFile = File(videoList[position])
        holder.loadImagePath(videoFile, context, onItemClick)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}