package com.example.menufragment.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "У ТЕБЯ НЕТ РАСПИСАНИЯ. ТЕБЯ ОТЧИСЛИЛИ!"
    }
    val text: LiveData<String> = _text
}