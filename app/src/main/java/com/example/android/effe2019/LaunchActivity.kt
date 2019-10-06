package com.example.android.effe2019

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.gauravk.bubblenavigation.BubbleNavigationLinearView
import android.R.attr.fragment
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.fragment.app.FragmentTransaction


class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
//        val actionBar: ActionBar
//        actionBar = this!!.supportActionBar!!
//        actionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.mygradient))
//        getActionBar()?.setTitle("My Effe");
//        getSupportActionBar()?.setTitle("My Effe");
//        val obj : HomeFragment = HomeFragment()
//        obj.on();
        var fr: Fragment

        fr = HomeFragment()
        val fm = supportFragmentManager
        var fragmentTransaction = fm.beginTransaction()
        var replace = fragmentTransaction.replace(R.id.container2, fr)
        fragmentTransaction.commit()
        val bubbleNavigationLinearView =
            findViewById<View>(R.id.bottom_navigation_view_linear) as BubbleNavigationLinearView
        bubbleNavigationLinearView.setNavigationChangeListener { view, position ->

            if (position == 0) {

                fr = HomeFragment()

            } else if (position == 1) {
                fr = TestFragment()
            } else if (position == 2) {
                fr = ProshowsFragment()
            } else { // Note the block
                fr = InfoFragment()
            }
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            val replace = fragmentTransaction.replace(R.id.container2, fr)
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()

    }
}