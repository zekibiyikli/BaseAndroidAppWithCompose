package com.base.androidappwithcompose.data.local.datastore

import androidx.datastore.preferences.core.Preferences.Key
import androidx.datastore.preferences.core.edit
import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

suspend fun <T> Context.saveToDataStore(key: Key<T>, value: T) {
    dataStore.edit { preferences ->
        preferences[key] = value
    }
}

fun <T> Context.readFromDataStore(key: Key<T>, defaultValue: T): Flow<T> {
    return dataStore.data.map { preferences ->
        preferences[key] ?: defaultValue
    }
}

