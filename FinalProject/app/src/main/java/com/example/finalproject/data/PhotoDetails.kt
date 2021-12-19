package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class PhotoDetails(
    @SerializedName("id") val id: String,
    @SerializedName("user") val user: User,
    @SerializedName("urls") val photoSource: PhotoSource,
    @SerializedName("links") val photoLinks: PhotoLinks,
    @SerializedName("views") val views: Int,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("likes") val likes: Int,
)