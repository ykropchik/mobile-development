package com.example.finalproject.services

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.os.Environment
import okhttp3.ResponseBody
import android.widget.Toast
import retrofit2.Retrofit
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.app.IntentService
import android.util.Log
import com.example.finalproject.R
import com.example.finalproject.configs.Constants
import com.example.finalproject.configs.Constants.APP_STORE_DIR_NAME
import com.example.finalproject.configs.Constants.DOWNLOAD_URL
import com.example.finalproject.configs.Constants.PROGRESS_UPDATE
import retrofit2.Call
import java.io.*

class NotificationService: IntentService("Service") {
    private var notificationBuilder: NotificationCompat.Builder? = null
    private var notificationManager: NotificationManager? = null
    private var photoId = String()

    override fun onHandleIntent(intent: Intent?) {
        photoId = intent?.getStringExtra("photoId").toString()
        Log.d("BNS", photoId)
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("id", "an", NotificationManager.IMPORTANCE_LOW)
            notificationChannel.description = "no sound"
            notificationChannel.setSound(null, null)
            notificationChannel.enableLights(false)
            notificationChannel.lightColor = R.color.primary_light
            notificationChannel.enableVibration(false)
            notificationManager!!.createNotificationChannel(notificationChannel)
        }
        notificationBuilder = NotificationCompat.Builder(this, "id")
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle(resources.getString(R.string.photo_download))
            .setContentText(resources.getString(R.string.photo_download_starting))
            .setDefaults(0)
            .setAutoCancel(true)
        notificationManager!!.notify(0, notificationBuilder!!.build())
        initRetrofit()
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(DOWNLOAD_URL)
            .build()
        val networkService = retrofit.create(NetworkService::class.java)
        val request: Call<ResponseBody> =
            networkService.downloadPhoto(photoId)
        try {
            downloadImage(request.execute().body())
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class)
    private fun downloadImage(body: ResponseBody?) {
        var count: Int
        val data = ByteArray(1024 * 4)
        val fileSize = body!!.contentLength()
        val inputStream: InputStream = BufferedInputStream(body.byteStream(),
            body.contentLength().toInt()
        )
        val fileExtension = body.contentType()?.subtype()
        val appStore = File(Environment.getExternalStorageDirectory(), APP_STORE_DIR_NAME)
        if (!appStore.exists()) {
            appStore.mkdirs()
        }
        val outputFile = File(
            appStore,
            "$photoId.$fileExtension"
        )
        val outputStream: OutputStream = FileOutputStream(outputFile)
        var total: Long = 0
        var downloadComplete = false
        //int totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
        while (inputStream.read(data).also { count = it } != -1) {
            total += count.toLong()
            val progress = ((total * 100).toDouble() / fileSize.toDouble()).toInt()
            updateNotification(progress)
            outputStream.write(data, 0, count)
            downloadComplete = true
        }
        onDownloadComplete(downloadComplete)
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    private fun updateNotification(currentProgress: Int) {
        notificationBuilder!!.setProgress(100, currentProgress, false)
        notificationBuilder!!.setContentText(resources.getString(R.string.photo_downloading_process) + " $currentProgress %")
        notificationManager!!.notify(0, notificationBuilder!!.build())
    }

    private fun sendProgressUpdate(downloadComplete: Boolean) {
        val intent = Intent(PROGRESS_UPDATE)
        intent.putExtra("downloadComplete", downloadComplete)
        LocalBroadcastManager.getInstance(this@NotificationService).sendBroadcast(intent)
    }

    private fun onDownloadComplete(downloadComplete: Boolean) {
        sendProgressUpdate(downloadComplete)
        notificationManager!!.cancel(0)
        notificationBuilder!!.setProgress(0, 0, false)
        notificationBuilder!!.setContentText(resources.getString(R.string.photo_download_ending))
        notificationManager!!.notify(0, notificationBuilder!!.build())
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        notificationManager!!.cancel(0)
    }
}