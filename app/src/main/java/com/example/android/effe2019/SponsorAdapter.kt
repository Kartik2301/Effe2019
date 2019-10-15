package com.example.android.effe2019

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class SponsorAdapter(
    private val context: Activity, //list of users
    internal var users: ArrayList<DataForSponsors>?
) : ArrayAdapter<DataForSponsors>(
    context,
    R.layout.list_itemforsponsors,
    (users as MutableList<DataForSponsors>?)!!
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (getContext() as Activity).layoutInflater.inflate(
                R.layout.list_itemforsponsors,
                parent,
                false
            )
        }
        val name = convertView!!.findViewById<View>(R.id.name) as TextView
        val role = convertView.findViewById<View>(R.id.role) as TextView
        val imageView = convertView.findViewById<View>(R.id.img_view) as ImageView
        val task = getItem(position) as DataForSponsors?
        name.text = task!!.name
        val l = task.categories
        var str = ""
        for (i in l!!.indices) {
            if (i == l.size - 1) {
                str = str + l[i]
            } else
                str = str + l[i] + " ,"
        }
        val ch = ','
        role.text = str
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(imageView.context)
            .load(task.imageUrl)
            .apply(requestOptions)
            .into(imageView)
        /*new DownloadImageTask(imageView)
        .execute(task.getUrlofSponsor());*/
        return convertView
    }

    private inner class DownloadImageTask(internal var bmImage: ImageView) :
        AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = java.net.URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }

            return mIcon11
        }

        override fun onPostExecute(result: Bitmap) {
            bmImage.setImageBitmap(result)
        }
    }
}
