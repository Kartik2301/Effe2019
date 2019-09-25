package com.example.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

import java.util.ArrayList

class HomeFragment : Fragment() {
    private val mForecastAdapter: ArrayAdapter<String>? = null
    private var listViewUsers: ListView? = null
    private val title: EditText? = null
    private val note: EditText? = null

   // internal lateinit var Users: List<Data>
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
       /* Users = ArrayList()
        listViewUsers = rootView.findViewById<View>(R.id.list) as ListView
        // list item click listener
        listViewUsers!!.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l -> val User = Users[i] }*/
        return rootView
    }
}
