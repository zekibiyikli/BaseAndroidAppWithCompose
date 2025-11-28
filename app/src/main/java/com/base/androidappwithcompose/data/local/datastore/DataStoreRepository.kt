package com.base.androidappwithcompose.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object PreferencesKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_AGE = intPreferencesKey("user_age")
}
