package com.example.android.effe2019

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun sendNotification() {

        val builder = NotificationCompat.Builder(this)
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.journaldev.com/"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, android.R.mipmap.sym_def_app_icon))
        builder.setContentTitle("Notifications Title")
        builder.setContentText("Your notification content here.")
        builder.setSubText("Tap to view the website.")

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build())
    }
}
