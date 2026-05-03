package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class InfoModel(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
