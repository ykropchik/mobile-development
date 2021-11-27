package com.example.fragmenttask.ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Это вторая страница"
    }
    val text: LiveData<String> = _text
}