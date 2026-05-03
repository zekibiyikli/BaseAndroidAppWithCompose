package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLoginModel(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)
