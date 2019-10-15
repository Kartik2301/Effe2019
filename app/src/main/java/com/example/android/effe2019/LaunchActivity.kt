package com.example.android.effe2019

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.gauravk.bubblenavigation.BubbleNavigationLinearView
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.esotericsoftware.kryo.util.IntArray




class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val bundle: Bundle? = intent.extras
        val updatesArrayList = bundle!!.getParcelableArrayList<DataForHome>("UPDATES")
        val teamArrayList = bundle!!.getParcelableArrayList<DataForTeam>("TEAM")
        val sponsorsArrayList = bundle!!.getParcelableArrayList<DataForSponsors>("SPONSORS")
        var fr: Fragment
        fr = HomeFragment(updatesArrayList)
        val fm = supportFragmentManager
        var fragmentTransaction = fm.beginTransaction()
        var replace = fragmentTransaction.replace(R.id.container2, fr)
        fragmentTransaction.commit()
        val bubbleNavigationLinearView =
            findViewById<View>(R.id.bottom_navigation_view_linear) as BubbleNavigationLinearView

        bubbleNavigationLinearView.setNavigationChangeListener { view, position ->
            fr = if (position == 0) {
                HomeFragment(updatesArrayList)
            } else if (position == 1) {
                TestFragment()
            } else if (position == 2) {
                ProshowsFragment()
            } else { // Note the block
                InfoFragment(teamArrayList, sponsorsArrayList)
            }
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.container2, fr)
            fragmentTransaction.commit()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//***Change Here***
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
    }
}