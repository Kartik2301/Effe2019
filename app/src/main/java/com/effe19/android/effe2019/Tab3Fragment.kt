package com.effe19.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import java.util.ArrayList

class Tab3Fragment(val sponsors: ArrayList<DataForSponsors>?) : Fragment() {
    val TAG: String = "0"
    private var listViewUsers: ListView? = null
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
        val rootView =  inflater.inflate(R.layout.fragment_tab3, container, false)
//        users = ArrayList()
//        databaseReference = FirebaseDatabase.getInstance().getReference("sponsors")
        listViewUsers = rootView.findViewById(R.id.listforsponsors) as ListView
//// list item click listener
//        listViewUsers!!.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//            val User = users[i]
//        })

        return rootView
    }

    override fun onStart() {
        super.onStart()
        val UserAdapter = activity?.let { SponsorAdapter(it, sponsors) }
        listViewUsers?.adapter = UserAdapter
    }
}