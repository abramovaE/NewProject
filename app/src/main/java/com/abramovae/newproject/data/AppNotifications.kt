package com.abramovae.newproject.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.R
import com.android.academy.fundamentals.homework.features.data.Movie

interface Notifications {
    fun initialize()
    fun showNotification(movie: Movie)
}

    class AppNotifications(private val context: Context) : Notifications {

        companion object {
            private const val CHANNEL = "MV_CHANNEL"
            private const val REQUEST_CONTENT = 1
        }

        private val notificationManager: NotificationManagerCompat =
            NotificationManagerCompat.from(context)

        override fun initialize() {
            if (Build.VERSION.SDK_INT > 26) {
                if (notificationManager.getNotificationChannel(CHANNEL) == null) {
                    notificationManager.createNotificationChannel(
                        NotificationChannel(CHANNEL, CHANNEL,
                            NotificationManager.IMPORTANCE_HIGH
                        )
                    )
                }
            }
        }

        override fun showNotification(movie: Movie) {
            val notification = NotificationCompat.Builder(context, CHANNEL)
                .setContentTitle(movie.title)
                .setContentText(movie.title)
                .setSmallIcon(R.drawable.star)
                .setWhen(System.currentTimeMillis())
//                .setLargeIcon(bitmapIcon)
                .setContentIntent(
                    PendingIntent.getActivity(
                        context,
                        REQUEST_CONTENT,
                        Intent(context, MainActivity::class.java)
                            .setAction(Intent.ACTION_VIEW)
                            .putExtra("movie", movie),
//                            .setData(("https://api.themoviedb.org/" + movie.id).toUri()),
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()
            notificationManager.notify("movie", 1, notification)
        }


    }