package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class InfoModel(
    var seed:String,
    var results:Int,
    var page:Int,
    var version:String
)