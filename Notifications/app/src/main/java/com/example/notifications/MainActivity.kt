package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.content.BroadcastReceiver




class MainActivity : AppCompatActivity() {
    private fun createNotificationChannel(channelInfo: ChannelClass) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelInfo.id, channelInfo.name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelInfo.description
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstBtn: Button = findViewById(R.id.BtnFirst)
        val secondBtn: Button = findViewById(R.id.BtnSecond)

        val firstChannel = ChannelClass("1", "Первый канал", "Описание первого канала")
        val secondChannel = ChannelClass("2", "Второй канал", "Описание второго канала")

        createNotificationChannel(firstChannel)
        createNotificationChannel(secondChannel)

        firstBtn.setOnClickListener {
            ++firstChannel.notificationCount

            val builder = NotificationCompat.Builder(this, firstChannel.id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Первый канал уведомлений")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            if (firstChannel.notificationCount >= 3) {
                val mNotificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.cancelAll()

                builder.setContentText("У вас " + firstChannel.notificationCount + " сообщения")
                with(NotificationManagerCompat.from(this)) {
                    notify(0, builder.build()) // посылаем уведомление
                }
            } else {
                builder.setContentText("Сообщение №" + firstChannel.notificationCount)
                with(NotificationManagerCompat.from(this)) {
                    notify(firstChannel.notificationCount, builder.build()) // посылаем уведомление
                }
            }
        }

        secondBtn.setOnClickListener {
            ++secondChannel.notificationCount

            val builder = NotificationCompat.Builder(this, secondChannel.id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Второй канал уведомлений")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            if (secondChannel.notificationCount >= 3) {
                val mNotificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.cancelAll()

                builder.setContentText("У вас " + secondChannel.notificationCount + " сообщения")
                with(NotificationManagerCompat.from(this)) {
                    notify(0, builder.build()) // посылаем уведомление
                }
            } else {
                builder.setContentText("Сообщение №" + secondChannel.notificationCount)
                with(NotificationManagerCompat.from(this)) {
                    notify(secondChannel.notificationCount, builder.build()) // посылаем уведомление
                }
            }
        }
    }
}