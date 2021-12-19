package com.example.finalproject.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.configs.Constants.API_URL
import com.example.finalproject.data.Photo
import com.example.finalproject.services.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel() : ViewModel() {

    private val _items: MutableLiveData<MutableList<Photo>> by lazy {
        MutableLiveData<MutableList<Photo>>().apply {
            value = mutableListOf<Photo>()
        }
    }

    val items: LiveData<MutableList<Photo>>
        get() = _items

    fun loadPhotos(page: Int) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkService: NetworkService = retrofit.create(NetworkService::class.java)
        networkService.getPhotosList(page).enqueue(object: Callback<MutableList<Photo>> {
            override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                Log.d("Error", "Load photos list failure")
            }

            override fun onResponse(call: Call<MutableList<Photo>>, response: Response<MutableList<Photo>>) {

                var currentValue = _items.value
                response.body()?.let {
                    if (currentValue == null) {
                        currentValue = it
                    } else {
                        currentValue?.addAll(it)
                    }
                }

                _items.value = currentValue!!
            }
        })
    }
}