package com.example.android.effe2019


import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.gauravk.bubblenavigation.BubbleNavigationLinearView

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val bubbleNavigationLinearView =
            findViewById<View>(R.id.bottom_navigation_view_linear) as BubbleNavigationLinearView
        bubbleNavigationLinearView.setNavigationChangeListener { view, position ->
            val fr: Fragment
                if (position == 0) {
                    fr = HomeFragment()
                }
                else if(position == 1) {
                    fr = EventFragment()
                }
                else  { // Note the block
                    fr = InfoFragment()
                }
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            val replace = fragmentTransaction.replace(R.id.container2, fr)
            fragmentTransaction.commit()
        }
    }
}
