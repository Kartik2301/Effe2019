package com.effe19.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout

class InfoFragment(val team: ArrayList<DataForTeam>?, val sponsors: ArrayList<DataForSponsors>?) :
    Fragment() {
    private var adapter: TabAdapter? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)
        viewPager = rootView.findViewById<View>(R.id.viewPager) as ViewPager
        tabLayout = rootView.findViewById<View>(R.id.tabLayout) as TabLayout
        adapter = TabAdapter(fragmentManager!!)
        adapter!!.addFragment(Tab1Fragment(team), "Team")
        adapter!!.addFragment(Tab2Fragment(), "Developers")
        adapter!!.addFragment(Tab3Fragment(sponsors), "Sponsors")
        adapter!!.addFragment(Tab4Fragment(), "About")
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)
        return rootView
    }
}
