package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserRegisteredModel(
    var date: String,
    var age: Int
)