package com.example.finalproject.ui.savedPhotos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.services.StorageService
import java.net.URI

class SavedPhotosViewModel() : ViewModel() {

    private val _items = MutableLiveData<ArrayList<URI>>()

    val items: LiveData<ArrayList<URI>>
        get() = _items

    fun loadPhotos() {
        val storageService = StorageService()
        val test = storageService.getDownloadedPhotosList()
        Log.d("URI", test.toString())
        _items.value = storageService.getDownloadedPhotosList()
    }
}