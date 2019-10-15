package com.example.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

import java.util.ArrayList
import android.hardware.SensorManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(var updates: ArrayList<DataForHome>?, var events: ArrayList<DataForEvents>) :
    Fragment() {
    val TAG: String = "0"
    private var listViewupdates: ListView? = null
    private lateinit var sensorManager: SensorManager
    //    internal lateinit var updates: MutableList<DataForHome>
    lateinit var userAdapter: HomeAdapter
    private var databaseReference: DatabaseReference? = null
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
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        listViewupdates = rootView.findViewById(R.id.list) as ListView

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateLists()
    }

    private fun populateLists() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.isAutoMeasureEnabled = true

        updatesRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        updatesRecyclerView.isNestedScrollingEnabled = false

        updatesRecyclerView.setHasFixedSize(true)
        updatesRecyclerView.setItemViewCacheSize(20)
        updatesRecyclerView.isDrawingCacheEnabled = true
        updatesRecyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH

        val upcomingAdapter = UpcomingAdapter(context!!)
        events = ArrayList(events.sortedWith(compareBy { it.timestamp }))

        events.filter {
            ((it.timestamp - 5 * 60 * 60 - 30 * 60) > System.currentTimeMillis() / 1000L)
                .and(it.timestamp != 2L)
        }
            .sortedBy { it.timestamp }
        if (events.size > 10) {
            events = ArrayList(events.subList(0, 10))
        }
        upcomingAdapter.addEvents(events)
        updatesRecyclerView.adapter = upcomingAdapter

        val userAdapter = activity?.let { HomeAdapter(it, updates) }!!
        listViewupdates!!.adapter = userAdapter
    }
}
