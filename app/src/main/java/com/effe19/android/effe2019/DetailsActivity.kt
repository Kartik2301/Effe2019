package com.effe19.android.effe2019

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.TextView

//import com.ramotion.garlandview.effe.GarlandApp

import java.util.ArrayList

import androidx.appcompat.app.AppCompatActivity

//import io.bloco.faker.Faker

class DetailsActivity : AppCompatActivity() {

    private val mListData = ArrayList<DetailsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        (findViewById<TextView>(R.id.event_name)).text = intent.getStringExtra(BUNDLE_NAME)
        (findViewById<TextView>(R.id.event_info)).text = intent.getStringExtra(BUNDLE_INFO)

    }

//    fun onFakerReady(faker: Faker) {
//        (findViewById(R.id.tv_status) as TextView).setText(faker.book.title())
//
//        for (i in 0 until ITEM_COUNT) {
//            mListData.add(DetailsData(faker.book.title(), faker.name.name()))
//        }
//
//        (findViewById(R.id.recycler_view) as RecyclerView).adapter = DetailsAdapter(mListData)
//    }

    fun onCloseClick(v: View) {
        super.onBackPressed()
    }

    companion object {

        private val ITEM_COUNT = 4

        private val BUNDLE_NAME = "BUNDLE_NAME"
        private val BUNDLE_INFO = "BUNDLE_INFO"
        private val BUNDLE_AVATAR_URL = "BUNDLE_AVATAR_URL"

        fun start(
            activity: LaunchActivity,
            name: String, address: String, url: String,
            card: View, avatar: View
        ) {
            val starter = Intent(activity, DetailsActivity::class.java)

            starter.putExtra(BUNDLE_NAME, name)
            starter.putExtra(BUNDLE_INFO, address)
            starter.putExtra(BUNDLE_AVATAR_URL, url)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val p1 = Pair.create(card, activity.getString(R.string.transition_card))
                val p2 = Pair.create(avatar, activity.getString(R.string.transition_avatar_border))

                val options = ActivityOptions.makeSceneTransitionAnimation(activity, p1, p2)
                activity.startActivity(starter, options.toBundle())
            } else {
                activity.startActivity(starter)
            }
        }
    }
}
