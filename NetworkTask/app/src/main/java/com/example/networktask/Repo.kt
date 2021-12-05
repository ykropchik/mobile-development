package com.example.networktask

import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("name") val name: String?,
    @SerializedName("html_url") val url: String?,
    @SerializedName("created_at") val creationDate: String?
)