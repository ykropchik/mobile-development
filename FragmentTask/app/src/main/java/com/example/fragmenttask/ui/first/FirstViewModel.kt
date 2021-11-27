package com.example.fragmenttask.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Это первая страница"
    }
    val text: LiveData<String> = _text
}