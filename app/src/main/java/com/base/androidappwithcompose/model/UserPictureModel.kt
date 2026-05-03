package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserPictureModel(
    val large: String,
    val medium: String,
    val thumbnail: String
)
