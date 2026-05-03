package com.base.androidappwithcompose.model

import androidx.annotation.Keep
import com.base.androidappwithcompose.core.BaseModel

@Keep
data class UserModel(
    val gender: String,
    val name: UserNameModel,
    val location: UserLocationModel,
    val email: String,
    val login: UserLoginModel,
    val dob: UserDobModel,
    val registered: UserRegisteredModel,
    val phone: String,
    val cell: String,
    val id: UserIdModel,
    val picture: UserPictureModel,
    val nat: String,
) : BaseModel()
