package com.example.finalproject.services

import com.example.finalproject.data.Photo
import com.example.finalproject.data.PhotoDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {
    @Headers("Accept-Version: v1", "Authorization: Client-ID vV1ox9ZgrEPycImkgLTZLvnjQNW8Li9upFKRn7tNb-M")
    @GET("/photos?per_page=30&order_by=popular")
    fun getPhotosList(@Query("page") page: Int): Call<MutableList<Photo>>

    @Headers("Accept-Version: v1", "Authorization: Client-ID vV1ox9ZgrEPycImkgLTZLvnjQNW8Li9upFKRn7tNb-M")
    @GET("/photos/{id}")
    fun getPhotoDetails(@Path("id") id: String): Call<PhotoDetails>

    @Headers("Accept-Version: v1", "Authorization: Client-ID vV1ox9ZgrEPycImkgLTZLvnjQNW8Li9upFKRn7tNb-M")
    @GET("/photos/{id}/download?force=true")
    @Streaming
    fun downloadPhoto(@Path("id") id: String?): Call<ResponseBody>
}