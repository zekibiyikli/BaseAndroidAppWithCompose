package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLocationModel(
    val street: UserLocationStreetModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: UserLocationCoordinatesModel,
    val timezone: UserLocationTimezoneModel,
)
