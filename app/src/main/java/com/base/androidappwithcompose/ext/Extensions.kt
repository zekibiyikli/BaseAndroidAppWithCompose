package com.base.androidappwithcompose.ext

import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException

fun <T : RequestBody?> T.bodyToString(): String {
    return try {
        val buffer = Buffer()
        if (this != null) this.writeTo(buffer) else return ""
        buffer.readUtf8()
    } catch (e: IOException) {
        "did not work"
    }
}
