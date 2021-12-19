package com.example.finalproject.ui.search.photoDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.configs.Constants.API_URL
import com.example.finalproject.data.PhotoDetails
import com.example.finalproject.services.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoDetailsViewModel : ViewModel() {

    private val _details = MutableLiveData<PhotoDetails>()

    val details: LiveData<PhotoDetails>
        get() = _details

    fun getPhotoDetails(id: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkService: NetworkService = retrofit.create(NetworkService::class.java)
        networkService.getPhotoDetails(id).enqueue(object: Callback<PhotoDetails> {
            override fun onFailure(call: Call<PhotoDetails>, t: Throwable) {
                Log.d("Error", "Load photos details failure")
            }

            override fun onResponse(call: Call<PhotoDetails>, response: Response<PhotoDetails>) {
                _details.value = response.body()
            }
        })
    }
}