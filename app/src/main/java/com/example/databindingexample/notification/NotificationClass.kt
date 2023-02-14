package com.example.databindingexample.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.databindingexample.MainActivity
import com.example.databindingexample.R

class NotificationClass {

    val notification_chanelId: String = "subscriber"
    var notificationManager: NotificationManager? = null
    val channelDescription = "This is SubscriberChanel"
    val channelName = "subscriber_channel"
    private val notificationID = 45

    fun generateNotification(context: Context) {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(notification_chanelId, channelDescription, importance)
            notificationManager?.createNotificationChannel(channel)
        }
    }
    fun showNotification(context: Context, message: String) {
        generateNotification(context)
        val tapToIntent=Intent(context,MainActivity::class.java)
        val pendingIntent:PendingIntent= PendingIntent.getActivity(
            context,
            0,
            tapToIntent,
            FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(context, notification_chanelId)
            .setContentTitle("New Notification")
            .setContentText(message)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager?.notify(notificationID, notification)
    }
}