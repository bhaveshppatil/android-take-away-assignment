package com.bhavesh.itunesapp.remote.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.itunesapp.R
import com.bhavesh.itunesapp.remote.clicklistener.ClickListener
import com.bhavesh.itunesapp.remote.model.Results
import com.bhavesh.itunesapp.remote.viewHolder.ITunesDataHolder

//adapter for our recycler view
class ITunesAdapter(
    val context: Context,
    private var responseTable: MutableList<Results>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<ITunesDataHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITunesDataHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itunes_item_layout, parent, false)
        return ITunesDataHolder(view)
    }

    override fun onBindViewHolder(holder: ITunesDataHolder, position: Int) {
        val results = responseTable[position]
        holder.trackName.text = results.trackName
        holder.setData(results)

        holder.crdTrackView.setOnClickListener {
            clickListener.onTrackClick(results)
        }
    }

    override fun getItemCount(): Int {
        return responseTable.size
    }
}