package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLocationTimezoneModel(
    val offset: String,
    val description: String
)
