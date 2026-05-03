package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserDobModel(
    val date: String,
    val age: Int
)
