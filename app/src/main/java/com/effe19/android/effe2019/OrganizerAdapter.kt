package com.effe19.android.effe2019

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import androidx.core.content.ContextCompat.startActivity
import android.net.Uri.fromParts
import android.content.Intent
import android.net.Uri
import android.widget.Button


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
        val call = convertView.findViewById<View>(R.id.callButton)
        val task = getItem(position)
        title.text = task!!.name
        date.text = task.phoneNumber.toString()
        val phone = task.phoneNumber.toString()
        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            context.startActivity(intent)
        }

        //        navigationView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_bg_light));

        return convertView
    }

}
