package com.example.android.effe2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import java.io.Serializable


class SplashActivity : AppCompatActivity(), Serializable {
    internal lateinit var updates: ArrayList<DataForHome>
    private var updatesDatabaseReference: DatabaseReference? = null
    var bundle = Bundle()

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
                    val update = postSnapshot.getValue(DataForHome::class.java)
                    if (update != null) {
                        updates.add(update)

                    }
                }
                startLaunchActivity()
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    private fun startLaunchActivity() {
//        Toast.makeText(this@SplashActivity, updates.size.toString(), Toast.LENGTH_LONG).show()
        var intent = Intent(this, LaunchActivity::class.java)
        bundle.putParcelableArrayList("UPDATES", updates)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
