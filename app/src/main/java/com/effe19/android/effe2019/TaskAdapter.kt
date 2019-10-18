package com.effe19.android.effe2019


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class TaskAdapter(
    private val context: Activity, //list of users
    internal var Users: List<Data>
) : ArrayAdapter<Any>(context, R.layout.list_itemfordevs, Users) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (getContext() as Activity).layoutInflater.inflate(
                R.layout.list_itemfordevs,
                parent,
                false
            )
        }
        val titleTextView = convertView!!.findViewById<View>(R.id.profile_name) as TextView
        val imageView = convertView.findViewById<View>(R.id.profile_img) as ImageView
        val button = convertView.findViewById<ImageView>(R.id.gotogit)
        val designation = convertView.findViewById<View>(R.id.designation) as TextView
        //TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
        //TextView noteTextView = (TextView) convertView.findViewById(R.id.desc);

        val task = getItem(position) as Data?

        titleTextView.text = task!!.getNameofDev()
        button.setOnClickListener {
            val url = task.getGithubDev()
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)
        }
        designation.text = task.getDesignationDev()
        val mTestArray = intArrayOf(
            R.drawable.rahul_image,
            R.drawable.akshit_image,
            R.drawable.kartik_image,
            R.drawable.anurag_image
        )
        imageView.setImageResource(mTestArray[position])
        //        new DownloadImageTask(imageView)
        //                .execute(task.getImageofDev());


        //        dateTextView.setText(task.getNameofDev());
        //        noteTextView.setText(task.getNameofDev());

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
