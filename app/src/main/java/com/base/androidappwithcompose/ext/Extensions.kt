package com.base.androidappwithcompose.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
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

fun ImageView.loadImage(context:Context,url:String){
    Glide.with(context).load(url).into(this)
}
