package com.example.android.effe2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import java.io.Serializable
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import com.esotericsoftware.kryo.util.IntArray
import com.firebase.ui.auth.data.model.User


class SplashActivity : AppCompatActivity(), Serializable {
    internal lateinit var updates: ArrayList<DataforHome>
    private var updatesDatabaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        updatesDatabaseReference = FirebaseDatabase.getInstance().getReference("updates")
        updates = ArrayList()
        fetchData()
    }

    private fun fetchData() {
        updatesDatabaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                updates.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val update = postSnapshot.getValue(DataforHome::class.java)
                    if (update != null) {
                        updates.add(update)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        var intent = Intent(this, LaunchActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelableArrayList("UPDATES", updates)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
