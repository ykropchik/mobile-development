package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast




class MainActivity : AppCompatActivity() {
//    val textView: TextView = findViewById(R.id.tvCalc) as TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickBtn(v: View?) {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show()
    }
}