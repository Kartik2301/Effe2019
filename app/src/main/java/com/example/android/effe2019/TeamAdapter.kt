package com.example.android.effe2019

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TeamAdapter(
    private val context: Activity, //list of users
    internal var Users: ArrayList<DataForTeam>?
) : ArrayAdapter<DataForTeam>(context, R.layout.list_itemforteam,
    (Users as MutableList<DataForTeam>?)!!
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (getContext() as Activity).layoutInflater.inflate(
                R.layout.list_itemforteam,
                parent,
                false
            )
        }
        val name = convertView!!.findViewById<View>(R.id.profile_name) as TextView
        val role = convertView.findViewById<View>(R.id.designation) as TextView
        val imageView = convertView.findViewById<View>(R.id.profile_img) as ImageView
        val imageButton = convertView.findViewById<ImageView>(R.id.phone)

        val task = getItem(position) as DataForTeam?
        name.text = task!!.name
        imageButton.setOnClickListener {
            val s = task.contact.toString()
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$s")
            context.startActivity(callIntent)
        }
        role.text = task.position
        val requestOption = RequestOptions().placeholder(R.drawable.placeholder_event)

        Glide.with(imageView.context)
            .load(task.imageUrl)
            .apply(requestOption)
            .into(imageView)
        //        if(position == 0){
        //            CardView cv = (CardView) convertView.findViewById(R.id.card);
        //            cv.setCardBackgroundColor(Color.parseColor("#FFE0B2"));
        //
        //        }
        return convertView
    }
}
