package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("links") val links: UserLinks,
    @SerializedName("profile_image") val profileImg: ProfileImg,
)