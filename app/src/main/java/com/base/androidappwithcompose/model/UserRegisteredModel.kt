package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserRegisteredModel(
    val date: String,
    val age: Int
)
