package com.example.android.effe2019

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.text.Layout
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

import androidx.core.content.ContextCompat

import java.io.InputStream
import java.text.DateFormat
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

class HomeAdapter(
    private val context: Activity, //list of users
    internal var Users: ArrayList<DataforHome>?
) : ArrayAdapter<DataforHome>(context, R.layout.list_item_homefragment, Users as MutableList<DataforHome>) {


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
        val task = getItem(position) as DataforHome?
        val timeStamp = task!!.getTimeStampofEvent()!!

        title.text = task.title
        desc.text = task.getDesciprionofEvent()
        date.text = convertTime(timeStamp)
        //        navigationView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_bg_light));

        return convertView
    }

    fun convertTime(time: Long): String {
        val date = Date(time)
        val dateFormat = SimpleDateFormat("hh:mm a\ndd/MM")
        return dateFormat.format(date)
    }

}
