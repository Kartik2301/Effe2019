package com.example.android.effe2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.data.model.User
import com.google.firebase.database.*
import java.io.Serializable


class SplashActivity : AppCompatActivity(), Serializable {
    internal lateinit var updates: ArrayList<DataForHome>
    internal lateinit var team: ArrayList<DataForTeam>
    private lateinit var updatesDatabaseReference: DatabaseReference
    private lateinit var teamDatabaseReference: DatabaseReference
    private lateinit var sponsorsDatabaseReference: DatabaseReference
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        updatesDatabaseReference = FirebaseDatabase.getInstance().getReference("updates")
        teamDatabaseReference = FirebaseDatabase.getInstance().getReference("team")
        updates = ArrayList()
        fetchData()
    }

    private fun fetchData() {
        updatesDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                updates.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val update = postSnapshot.getValue(DataForHome::class.java)
                    if (update != null) {
                        updates.add(update)

                    }
                }
                updates = ArrayList(updates.sortedWith(compareByDescending { it.timestamp }))
                startLaunchActivity()
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

//        teamDatabaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                team.clear()
//                for (postSnapshot in dataSnapshot.children) {
//                    val member = postSnapshot.getValue(DataForTeam::class.java)
//                    if (member != null) {
//                        team.add(member)
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//            }
//        })
    }

    private fun startLaunchActivity() {
        var intent = Intent(this, LaunchActivity::class.java)
        bundle.putParcelableArrayList("UPDATES", updates)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
