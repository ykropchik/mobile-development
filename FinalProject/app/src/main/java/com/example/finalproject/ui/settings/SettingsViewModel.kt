package com.example.finalproject.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Да, здесь ничего нет, потому что нечего настраивать :D"
    }
    val text: LiveData<String> = _text
}