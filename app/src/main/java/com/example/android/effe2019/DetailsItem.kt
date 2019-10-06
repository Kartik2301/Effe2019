package com.example.android.effe2019


import android.view.View
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView

internal class DetailsItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setContent(data: DetailsData) {
        (itemView.findViewById(R.id.organizer_name) as TextView).setText(data.title)
        (itemView.findViewById(R.id.organizer_contact) as TextView).setText(data.text)
    }

}
