package com.example.android.effe2019

import android.app.Notification
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

import java.util.ArrayList
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.database.core.Context
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import android.content.Context.SENSOR_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService

class HomeFragment : Fragment() {
    val TAG: String = "0"
    private var listViewUsers: ListView? = null
    private lateinit var sensorManager: SensorManager
    internal lateinit var Users: MutableList<DataforHome>
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

        Users = ArrayList()
        databaseReference = FirebaseDatabase.getInstance().getReference("updates")
        listViewUsers = rootView.findViewById(R.id.list) as ListView
// list item click listener
        listViewUsers!!.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val User = Users[i]
        })

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        on()
    }
     fun on() {
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
88
                Users.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val User = postSnapshot.getValue(DataforHome::class.java)
                    if (User != null) {
                        Users.add(User)
                    }
                }
                val UserAdapter = HomeAdapter(activity, Users)
                listViewUsers?.setAdapter(UserAdapter)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

}
