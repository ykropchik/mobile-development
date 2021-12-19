package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("id") val id: String,
    @SerializedName("links") val photoLinks: PhotoLinks,
    @SerializedName("urls") val photoSource: PhotoSource
)