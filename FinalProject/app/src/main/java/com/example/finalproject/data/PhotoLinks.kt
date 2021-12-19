package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class PhotoLinks(
    @SerializedName("self") val self: String,
    @SerializedName("html") val html: String,
    @SerializedName("download") val download: String,
)