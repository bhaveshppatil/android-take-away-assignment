package com.bhavesh.galleryapp.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.galleryapp.R
import com.bhavesh.galleryapp.inteface.OnItemClick
import com.bumptech.glide.Glide
import java.io.File

class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    val ivImage: ImageView = itemview.findViewById(R.id.ivImage)

    fun loadImagePath(imgFile: File, context: Context, onItemClick: OnItemClick) {

        if (imgFile.exists()) {
            Glide.with(ivImage).load(imgFile).into(ivImage)

            itemView.setOnClickListener(View.OnClickListener {
                onItemClick.onImageClick(imgFile)
            })
        }
    }
}
