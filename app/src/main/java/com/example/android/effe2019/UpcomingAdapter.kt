package com.example.android.effe2019

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.bookmark_event_layout.view.*
import com.example.android.effe2019.R
import kotlinx.android.synthetic.main.bookmark_event_layout.view.eventImageView
import kotlinx.android.synthetic.main.upcoming_event_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class UpcomingAdapter(val context: Context) :
        RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {

    private val events = ArrayList<DataForEvents>()

    override fun getItemCount() = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.bookmark_event_layout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, this, events[position], position)
    }

    fun addEvents(events: ArrayList<DataForEvents>) {
        this.events.addAll(events)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.events.clear()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<TextView>(R.id.titleTextView)
        val timeView = itemView.findViewById<TextView>(R.id.timeTextView)
        val locationView = itemView.findViewById<TextView>(R.id.locationTextView)

        fun bindItem(context: Context, adapter: UpcomingAdapter, event: DataForEvents, position: Int) {
            titleView.text = event.name

            val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/India"))
            calendar.timeInMillis = event.timestamp!!.times(1000L)

            val sdf = SimpleDateFormat("hh:mm a")

            timeView.text = sdf.format(calendar.time)

            locationView.text = event.location


            if(event.timestamp!! < 100L){
                locationView.text = "Online"
                timeView.text = ""
            }else{
                timeView.visibility = View.VISIBLE
            }

            Glide.with(context)
                    .load(event.imageUrl)
                    .into(itemView.eventImageView)
        }
    }
}