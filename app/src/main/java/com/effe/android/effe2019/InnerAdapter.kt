package com.effe.android.effe2019

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.effe.android.effe2019.databinding.InnerItemBinding


import java.util.ArrayList

class InnerAdapter : com.ramotion.garlandview.inner.InnerAdapter<InnerItem>() {

    private val mData = ArrayList<InnerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerItem {
        val binding = DataBindingUtil.inflate<InnerItemBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return InnerItem(binding.getRoot())
    }

    override fun onBindViewHolder(holder: InnerItem, position: Int) {
        holder.setContent(mData[position])
    }

    override fun onViewRecycled(holder: InnerItem) {
        holder.clearContent()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.inner_item
    }

    fun addData(innerDataList: List<InnerData>) {
        val size = mData.size
        mData.addAll(innerDataList)
        notifyItemRangeInserted(size, innerDataList.size)
    }

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

}