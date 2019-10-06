package com.example.android.effe2019

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.greenrobot.eventbus.EventBus

class InnerItem(itemView: View) : com.ramotion.garlandview.inner.InnerItem(itemView) {

    private val mInnerLayout: View

    val mHeader: TextView
    val mName: TextView
    val mAddress: TextView
    val mAvatar: ImageView
    val mAvatarBorder: View

    var itemData: InnerData? = null
        private set

    init {
        mInnerLayout = (itemView as ViewGroup).getChildAt(0)

        mHeader = itemView.findViewById(R.id.tv_header) as TextView
        mName = itemView.findViewById(R.id.event_name) as TextView
        mAddress = itemView.findViewById(R.id.tv_address) as TextView
        mAvatar = itemView.findViewById(R.id.avatar) as ImageView
        mAvatarBorder = itemView.findViewById(R.id.avatar_border)

        mInnerLayout.setOnClickListener { EventBus.getDefault().post(this@InnerItem) }
    }

    override fun getInnerLayout(): View {
        return mInnerLayout
    }

    fun clearContent() {
        GlideApp.with(mAvatar.context).clear(mAvatar)
        itemData = null
    }

    internal fun setContent(data: InnerData) {
        itemData = data

        mHeader.text = data.title
        mName.text =
            data.Time+", "+data.date
        mAddress.text =
            data.Location


        GlideApp.with(itemView.context)
            .load(data.Url)
            .placeholder(R.drawable.avatar_placeholder)
            .transform(CircleCrop())
            .into(mAvatar)
    }

}
