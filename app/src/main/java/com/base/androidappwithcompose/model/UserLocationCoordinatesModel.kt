package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLocationCoordinatesModel(
    val latitude: String,
    val longitude: String
)
