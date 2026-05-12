package com.suryashakti.monitor.ui

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.suryashakti.monitor.R

object PeakNotifier {
    private const val channelId = "peak_sun_alerts"
    private const val notificationId = 101

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Peak sun alerts",
                NotificationManager.IMPORTANCE_DEFAULT,
            ).apply {
                description = "Suggestions for running heavy appliances during high solar generation."
            }

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun showPeakSuggestion(context: Context) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_peak_sun)
            .setContentTitle("High Sun")
            .setContentText("Ideal time for heavy appliances.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }
}
