package com.base.androidappwithcompose.data.local.kotpref

import com.chibatching.kotpref.KotprefModel

object KotPrefShared: KotprefModel(){
    var testKotPref :String by stringPref("")
}