package com.effe.android.effe2019

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab4.view.*
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.content.pm.PackageManager

class Tab4Fragment : Fragment() {
    var FACEBOOK_URL = "https://www.facebook.com/effervescence.iiita/"
    var FACEBOOK_PAGE_ID = "effervescence.iiita"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tab4, container, false)
        rootView.insta_icon.setOnClickListener {
            val uri = Uri.parse("https://www.instagram.com/goeffervescence")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.instagram.android")

            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/goeffervescence")
                    )
                )
            }

        }
        rootView.fb_icon.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = activity?.let { it1 -> getFacebookPageURL(it1) }
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        }


        rootView.youtube_icon.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse("https://www.youtube.com/user/effervescenceMM13"));
                startActivity(intent);
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
        rootView.twitter_icon.setOnClickListener {
            var intent: Intent? = null
            try {
                // get the Twitter app if possible
                activity?.packageManager?.getPackageArchiveInfo("com.twitter.android", 0)
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/goeffervescence"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            } catch (e: Exception) {
                // no Twitter app, revert to browser
                intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/goeffervescence")
                )
            }

            this.startActivity(intent)
        }
        rootView.tv_web.setOnClickListener {
            var intent: Intent? = null
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://effe.org.in")
            )
            this.startActivity(intent)
        }
        return rootView
    }
    fun getFacebookPageURL(context: Context): String {
        val packageManager = context.getPackageManager()
        try {
            val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            return if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$FACEBOOK_URL"
            } else { //older versions of fb app
                "fb://page/$FACEBOOK_PAGE_ID"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            return FACEBOOK_URL //normal web url
        }

    }
}
