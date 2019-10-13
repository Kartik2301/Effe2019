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
//        val actionBar: ActionBar
//        actionBar = this!!.supportActionBar!!
//        actionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.mygradient))
//        getActionBar()?.setTitle("My Effe");
//        getSupportActionBar()?.setTitle("My Effe");
//        val obj : HomeFragment = HomeFragment()
//        obj.on();

        val bundle: Bundle? = intent.extras
        val updatesArrayList = bundle!!.getParcelableArrayList<DataForHome>("UPDATES")
//        if (updatesArrayList != null)
//            Toast.makeText(this, updatesArrayList.size.toString(), Toast.LENGTH_LONG).show()
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
                InfoFragment()
            }
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.container2, fr)
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
    }
}