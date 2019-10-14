package com.example.android.effe2019

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import java.text.SimpleDateFormat
import java.util.Date

class HomeAdapter(
    private val context: Activity, //list of users
    internal var users: ArrayList<DataForHome>?
) : ArrayAdapter<DataForHome>(context, R.layout.list_item_homefragment,
    (users as MutableList<DataForHome>?)!!
) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (getContext() as Activity).layoutInflater.inflate(
                R.layout.list_item_homefragment,
                parent,
                false
            )
        }
        val title = convertView!!.findViewById<View>(R.id.updates) as TextView
        val date = convertView.findViewById<View>(R.id.date) as TextView
        val desc = convertView.findViewById<View>(R.id.desc) as TextView
        //        View navigationView = convertView.findViewById(R.id.bottom_navigation_view_linear);
        val task = getItem(position)
        val timeStamp = task!!.timestamp
        title.text = task.title
        desc.text = task.description
        date.text = convertTime(timeStamp!!)
        //        navigationView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_bg_light));

        return convertView
    }

    private fun convertTime(time: Long): String {
        val date = Date(time)
        val dateFormat = SimpleDateFormat("hh:mm a\ndd/MM")
        return dateFormat.format(date)
    }

}
