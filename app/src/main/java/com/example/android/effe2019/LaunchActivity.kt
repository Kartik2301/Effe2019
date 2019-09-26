package com.example.android.effe2019

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.gauravk.bubblenavigation.BubbleNavigationLinearView

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val actionBar: ActionBar
        actionBar = this!!.supportActionBar!!
        actionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.mygradient))
        getActionBar()?.setTitle("My Effe");
        getSupportActionBar()?.setTitle("My Effe");
        val bubbleNavigationLinearView =
            findViewById<View>(R.id.bottom_navigation_view_linear) as BubbleNavigationLinearView
        bubbleNavigationLinearView.setNavigationChangeListener { view, position ->
            val fr: Fragment
                if (position == 0) {
                    fr = HomeFragment()
                }
                else if(position == 1) {
                    fr = TestFragment()
                }
                else if(position ==2){
                    fr = InfoFragment()
                }
                else  { // Note the block
                    fr = ProshowsFragment()
                }
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            val replace = fragmentTransaction.replace(R.id.container2, fr)
            fragmentTransaction.commit()
        }
    }
}
