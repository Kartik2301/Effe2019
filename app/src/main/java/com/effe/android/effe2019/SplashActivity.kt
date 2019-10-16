package com.effe.android.effe2019

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.Serializable
import com.bumptech.glide.request.target.SimpleTarget


class SplashActivity : AppCompatActivity(), Serializable {
    private lateinit var events: ArrayList<DataForEvents>
    private lateinit var updates: ArrayList<DataForHome>
    private lateinit var team: ArrayList<DataForTeam>
    private lateinit var sponsors: ArrayList<DataForSponsors>
    private lateinit var firebaseDatabaseReference: DatabaseReference
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlideApp.with(this).load(R.drawable.background_splash3)
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    this@SplashActivity.relative_layout.background = resource
                }
            })

        startLogoAnimation()
        firebaseDatabaseReference = FirebaseDatabase.getInstance().reference
        events = ArrayList()
        updates = ArrayList()
        team = ArrayList()
        sponsors = ArrayList()
        fetchData()
    }

    private fun startLogoAnimation() {
        val yTranslationDown = ObjectAnimator.ofFloat(effeLogoIV, "translationY", 150f)
        val yTranslationUp = ObjectAnimator.ofFloat(effeLogoIV, "translationY", -130f)
        val yTranslationDownToStart = ObjectAnimator.ofFloat(effeLogoIV, "translationY", 0f)

        yTranslationDown.duration = 1500
        yTranslationUp.duration = 3000
        yTranslationUp.startDelay = 1500
        yTranslationDownToStart.duration = 1500
        yTranslationDownToStart.startDelay = 4500

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(yTranslationDown, yTranslationUp, yTranslationDownToStart)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
//                super.onAnimationEnd(animation)
                animatorSet.start()
            }
        })
        animatorSet.start()
    }


    private fun fetchData() {
        firebaseDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                createArrayLists(dataSnapshot)
                showFinishedAnimation()
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
        bundle.putParcelableArrayList("EVENTS", events)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun createArrayLists(dataSnapshot: DataSnapshot) {
        val updatesDataSnapshot = dataSnapshot.child("updates")
        val teamDataSnapshot = dataSnapshot.child("team")
        val sponsorsDataSnapshot = dataSnapshot.child("sponsors")
        val eventsDataSnapshot = dataSnapshot.child("events")

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

        events.clear()
        for (postSnapshot in eventsDataSnapshot.children) {
            val member = postSnapshot.getValue(DataForEvents::class.java)
            if (member != null) {
                events.add(member)
            }
        }
    }

    private fun showFinishedAnimation() {
        spin_kit.visibility = View.GONE
        animationView.setAnimation("checked_done.json")
        animationView.loop(false)
        animationView.playAnimation()
        animationView.addAnimatorListener(AnimatorListenerAdapter(
            onStart = { },
            onEnd = {
                startLaunchActivity()
                finish()
            },
            onCancel = { },
            onRepeat = { }))
    }
}
