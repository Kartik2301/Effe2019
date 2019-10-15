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

class HomeFragment(var users: ArrayList<DataForHome>?) : Fragment() {
    val TAG: String = "0"
    private var listViewUsers: ListView? = null
    private lateinit var sensorManager: SensorManager
    //    internal lateinit var users: MutableList<DataForHome>
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

        listViewUsers = rootView.findViewById(R.id.list) as ListView

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateLists()
    }

    private fun populateLists() {
//        val layoutManager = LinearLayoutManager(context)
//        layoutManager.isAutoMeasureEnabled = true
//
//        bookmarksRecyclerView.layoutManager = LinearLayoutManager(context,
//            LinearLayoutManager.HORIZONTAL, false)
//        bookmarksRecyclerView.isNestedScrollingEnabled = false
//
//        bookmarksRecyclerView.setHasFixedSize(true)
//        bookmarksRecyclerView.setItemViewCacheSize(20)
//        bookmarksRecyclerView.isDrawingCacheEnabled = true
//        bookmarksRecyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH

//        val bookmarksAdapter = UpcomingAdapter(context!!)
//        bookmarksRecyclerView.adapter = bookmarksAdapter


        val userAdapter = activity?.let { HomeAdapter(it, users) }!!
        listViewUsers!!.adapter = userAdapter
    }
}
