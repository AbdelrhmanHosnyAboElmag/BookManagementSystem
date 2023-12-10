package com.example.bookstoremanageruser.desginpattern.observer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.bookstoremanageruser.R

class BookSubscriber(private val context: Context) :
    com.example.bookstoremanageruser.desginpattern.observer.EventBookListener {
    override fun notify(key:Any,) {
        //code push notification
            val channelId = "your_channel_id"
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Create a notification channel (required for Android 8.0 and above)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelName = "Your Channel Name"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channelId, channelName, importance)
                notificationManager.createNotificationChannel(channel)
            }

            // Build the notification
            val notificationBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notification Title")
                .setContentText("Notification Content: $key")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

            // Show the notification
            notificationManager.notify(1,notificationBuilder.build())
    }
}