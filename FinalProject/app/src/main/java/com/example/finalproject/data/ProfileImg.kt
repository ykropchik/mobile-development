package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class ProfileImg (
    @SerializedName("small") val small: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("large") val large: String
)