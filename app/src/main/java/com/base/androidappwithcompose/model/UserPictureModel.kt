package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserPictureModel(
    var large: String,
    var medium: String,
    var thumbnail: String
)