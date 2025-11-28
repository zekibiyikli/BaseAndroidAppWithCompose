package com.base.androidappwithcompose.model

import androidx.annotation.Keep

@Keep
data class UserLocationModel(
    var street: UserLocationStreetModel,
    var city:String,
    var state:String,
    var country:String,
    var postcode:String,
    var coordinates: UserLocationCoordinatesModel,
    var timezone: UserLocationTimezoneModel,
)
