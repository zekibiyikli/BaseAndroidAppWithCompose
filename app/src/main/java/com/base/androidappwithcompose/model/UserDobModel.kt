package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserDobModel(
    var date: String,
    var age: Int
)