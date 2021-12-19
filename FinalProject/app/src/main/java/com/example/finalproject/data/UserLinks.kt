package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class UserLinks (
    @SerializedName("self") val raw: String,
    @SerializedName("html") val full: String,
    @SerializedName("photos") val regular: String,
)