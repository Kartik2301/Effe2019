package com.example.android.effe2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import java.io.Serializable


class SplashActivity : AppCompatActivity(), Serializable {
    private lateinit var updates: ArrayList<DataForHome>
    private lateinit var team: ArrayList<DataForTeam>
    private lateinit var sponsors: ArrayList<DataForSponsors>
    private lateinit var firebaseDatabaseReference: DatabaseReference
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseDatabaseReference = FirebaseDatabase.getInstance().reference
        updates = ArrayList()
        team = ArrayList()
        sponsors = ArrayList()
        fetchData()
    }

    private fun fetchData() {
        firebaseDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                createArrayLists(dataSnapshot)
                startLaunchActivity()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    private fun startLaunchActivity() {
        var intent = Intent(this, LaunchActivity::class.java)
        bundle.putParcelableArrayList("UPDATES", updates)
        bundle.putParcelableArrayList("TEAM", team)
        bundle.putParcelableArrayList("SPONSORS", sponsors)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun createArrayLists(dataSnapshot: DataSnapshot) {
        val updatesDataSnapshot = dataSnapshot.child("updates")
        val teamDataSnapshot = dataSnapshot.child("team")
        val sponsorsDataSnapshot = dataSnapshot.child("sponsors")

        updates.clear()
        for (postSnapshot in updatesDataSnapshot.children) {
            val update = postSnapshot.getValue(DataForHome::class.java)
            if (update != null) {
                updates.add(update)
            }
        }
        updates = ArrayList(updates.sortedWith(compareByDescending { it.timestamp }))

        team.clear()
        for (postSnapshot in teamDataSnapshot.children) {
            val member = postSnapshot.getValue(DataForTeam::class.java)
            if (member != null) {
                team.add(member)
            }
        }

        sponsors.clear()
        for (postSnapshot in sponsorsDataSnapshot.children) {
            val member = postSnapshot.getValue(DataForSponsors::class.java)
            if (member != null) {
                sponsors.add(member)
            }
        }
    }
}
