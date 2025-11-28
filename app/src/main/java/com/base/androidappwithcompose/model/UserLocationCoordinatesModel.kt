package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLocationCoordinatesModel(
    var latitude: String,
    var longitude:String
)