package com.bhavesh.galleryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.galleryapp.R
import com.bhavesh.galleryapp.inteface.OnItemClick
import com.bhavesh.galleryapp.viewholder.ImageViewHolder
import java.io.File


class ImageAdapter(
    val context: Context,
    val imageList: MutableList<String>,
    val onItemClick: OnItemClick
) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.image_item_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imgFile = File(imageList[position])
        holder.loadImagePath(imgFile, context, onItemClick)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}