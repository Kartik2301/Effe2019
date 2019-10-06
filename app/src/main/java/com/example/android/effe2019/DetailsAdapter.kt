package com.example.android.effe2019

import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import com.example.android.effe2019.databinding.ActivityDetailsBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.android.effe2019.DetailsData
import com.example.android.effe2019.DetailsItem
import com.example.android.effe2019.R

internal class DetailsAdapter(private val mData: List<DetailsData>) :
    RecyclerView.Adapter<DetailsItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsItem {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ActivityDetailsBinding = inflate(inflater, R.layout.details_item, parent, false)
        return DetailsItem(binding.getRoot())
    }

    override fun onBindViewHolder(holder: DetailsItem, position: Int) {
        holder.setContent(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}
