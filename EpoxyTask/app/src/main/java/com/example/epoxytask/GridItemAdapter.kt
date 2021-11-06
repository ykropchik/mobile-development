package com.example.epoxytask

import androidx.annotation.DrawableRes

data class GridItem(
    @DrawableRes
    val icon: Int,
    val name: String,
    val number: String
)