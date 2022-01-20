package com.bhavesh.getbasisassignment.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.getbasisassignment.databinding.ItemLayoutBinding
import com.bhavesh.getbasisassignment.remote.model.Data

class DataViewHolder(
    private val itemLayoutBinding: ItemLayoutBinding,
) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBindData(data: Data) {
        itemLayoutBinding.tvID.text = "Id ${data.id}"
        itemLayoutBinding.tvData.text = data.text
    }
}