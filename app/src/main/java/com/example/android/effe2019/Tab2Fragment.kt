package com.example.android.effe2019

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import java.util.ArrayList

class Tab2Fragment : Fragment() {
    private var listViewUsers: ListView? = null
    internal lateinit var Users: MutableList<Data>
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
        val rootView = inflater.inflate(R.layout.fragment_tab2, container, false)
        Users = ArrayList()
        databaseReference = FirebaseDatabase.getInstance().getReference("devs")
        listViewUsers = rootView.findViewById(R.id.messageListView) as ListView
// list item click listener
        listViewUsers!!.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val User = Users[i]
        })
        return rootView
    }
    override fun onStart() {
        super.onStart()
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Users.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val User = postSnapshot.getValue(Data::class.java)
                    if (User != null) {
                        Users.add(User)
                    }
                }
                val UserAdapter = activity?.let { TaskAdapter(it, Users) }
                listViewUsers?.setAdapter(UserAdapter)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}
