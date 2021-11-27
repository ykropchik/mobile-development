package com.example.asynctask

import android.os.AsyncTask
import android.os.Handler
import android.os.SystemClock
import android.widget.TextView
import java.util.concurrent.TimeUnit

class LoadService(textView: TextView) : AsyncTask<Void, Void, String>() {
    var statusContainer = textView

    override fun onPreExecute() {
        super.onPreExecute()
        statusContainer.text = "Загрузка..."
    }

    override fun doInBackground(vararg p0: Void?): String {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }

        return "Загрузка завершена"
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        statusContainer.text = result
    }
}