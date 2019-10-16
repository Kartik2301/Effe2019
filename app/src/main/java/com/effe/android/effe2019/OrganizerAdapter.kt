package com.effe.android.effe2019

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList

class OrganizerAdapter(
    private val context: Activity, //list of users
    internal var users: ArrayList<DataForOrganizers>
) : ArrayAdapter<DataForOrganizers>(context, R.layout.list_itemfororganizer,
    (users as MutableList<DataForOrganizers>?)!!
) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (getContext() as Activity).layoutInflater.inflate(
                R.layout.list_itemfororganizer,
                parent,
                false
            )
        }
        val title = convertView!!.findViewById<View>(R.id.organizerName) as TextView
        val date = convertView.findViewById<View>(R.id.organizerNumber) as TextView

        val task = getItem(position)
        title.text = task!!.name
        date.text = task.phoneNumber.toString()
        //        navigationView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_bg_light));

        return convertView
    }

}
