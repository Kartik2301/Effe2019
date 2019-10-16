package com.effe.android.effe2019

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.bookmark_event_layout.view.eventImageView
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        override fun onClick(p0: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

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
            itemView.setOnClickListener {
                val name : TextView
                val date : TextView
                val description : TextView
                val image : ImageView
                val myDialog = AlertDialog.Builder(context)
                val inflater = LayoutInflater.from(context)
                val myview = inflater.inflate(R.layout.custominputfield, null)
                name = myview.findViewById(R.id.name)
                date = myview.findViewById(R.id.date)
                image = myview.findViewById(R.id.imageView)
                description = myview.findViewById(R.id.profession)
                name.text = event.name
                date.text = event.date
                val organizers: java.util.ArrayList<DataForOrganizers> = event.organizers as java.util.ArrayList<DataForOrganizers>
                Glide.with(image.context)
                    .load(event.imageUrl)
                    .into(image)
                var listViewupdates: ListView? = null
                listViewupdates = myview.findViewById(R.id.list) as ListView
                val userAdapter: OrganizerAdapter = context?.let { OrganizerAdapter(context as Activity, organizers) }!!
                listViewupdates!!.adapter = userAdapter
                description.text = event.description
                myDialog.setView(myview)
                val dialog: AlertDialog = myDialog.create();
                dialog.show()
            }
        }
    }
}