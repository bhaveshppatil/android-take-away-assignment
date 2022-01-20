package com.bhavesh.getbasisassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bhavesh.getbasisassignment.R
import com.bhavesh.getbasisassignment.databinding.ItemLayoutBinding
import com.bhavesh.getbasisassignment.remote.model.Data
import com.bhavesh.getbasisassignment.ui.viewholder.DataViewHolder

class DataAdapter(
    val viewPager2: ViewPager2,
    var dataModelList: MutableList<Data>,
) : RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return DataViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.onBindData(dataModel)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }
}