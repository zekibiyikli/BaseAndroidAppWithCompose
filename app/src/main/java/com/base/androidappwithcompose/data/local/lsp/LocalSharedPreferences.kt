package com.base.androidappwithcompose.data.local.lsp

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.base.androidappwithcompose.enums.LocalSharedPrefKey

class LocalSharedPreferences(applicationContext: Context) {

    private val masterKey = MasterKey.Builder(applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref: SharedPreferences = EncryptedSharedPreferences.create(
        applicationContext,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var isFirstRun: Boolean
        set(value) = pref.edit().putBoolean(LocalSharedPrefKey.IS_FIRST_RUN, value).apply()
        get() = pref.getBoolean(LocalSharedPrefKey.IS_FIRST_RUN, true)

    var userToken: String
        set(value) = pref.edit().putString(LocalSharedPrefKey.USER_TOKEN, value).apply()
        get() = pref.getString(LocalSharedPrefKey.USER_TOKEN, "") ?: ""
}
