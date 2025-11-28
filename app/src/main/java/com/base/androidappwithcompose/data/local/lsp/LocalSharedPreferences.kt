package com.base.androidappwithcompose.data.local.lsp

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.base.androidappwithcompose.enums.LocalSharedPrefKey

class LocalSharedPreferences(private val applicationContext: Context) {

    //Variables
    private val masterKey = MasterKey.Builder(applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private var pref: SharedPreferences = EncryptedSharedPreferences.create(
        applicationContext,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    //Functions
    var isFirstRun : Boolean
        set(value) = pref.edit().putBoolean(LocalSharedPrefKey.IS_FIRST_RUN,value).apply()
        get() = pref.getBoolean(LocalSharedPrefKey.IS_FIRST_RUN,true)

    var userToken : String
        set(value) = pref.edit().putString(LocalSharedPrefKey.USER_TOKEN,value).apply()
        get() = pref.getString(LocalSharedPrefKey.USER_TOKEN,"")?:""

    //Companion Object
    companion object {
        @Volatile
        private var instance: LocalSharedPreferences? = null
        fun init(context: Context) = instance ?: synchronized(this) {
            instance = LocalSharedPreferences(context.applicationContext)
        }

        fun getInstance(context: Context): LocalSharedPreferences {
            return instance ?: synchronized(this) {
                instance ?: LocalSharedPreferences(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }
}