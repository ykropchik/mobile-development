package com.example.finalproject.services

import android.app.PendingIntent.getActivity
import android.content.Context
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.os.Environment
import android.util.Log
import com.example.finalproject.configs.Constants


class DownloadService(private val context: Context) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Constants.PROGRESS_UPDATE) {
            val downloadComplete = intent.getBooleanExtra("downloadComplete", false)
            if (downloadComplete) {
                Toast.makeText(
                    context,
                    "Фото успешно сохранено",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun startImageDownload(photoId: String) {
        val intent = Intent(this.context, NotificationService::class.java)
        intent.putExtra("photoId", photoId)
        this.context.startService(intent)
    }
}