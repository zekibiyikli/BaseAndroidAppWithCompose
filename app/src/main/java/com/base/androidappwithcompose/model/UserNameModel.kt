package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserNameModel(
    val title: String,
    val first: String,
    val last: String
)
