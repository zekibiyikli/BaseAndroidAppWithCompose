package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLocationTimezoneModel(
    var offset:String,
    var description:String
)