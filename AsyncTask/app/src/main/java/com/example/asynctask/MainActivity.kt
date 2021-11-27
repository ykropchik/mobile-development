package com.example.asynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.tv_Container)
        val btn: Button = findViewById(R.id.btn_load)
        btn.setOnClickListener {
            val loadService = LoadService(textView)
            loadService.execute()
        }
    }
}