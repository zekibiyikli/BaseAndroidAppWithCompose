package com.base.androidappwithcompose.model.response

import com.base.androidappwithcompose.core.BaseResponse
import com.base.androidappwithcompose.model.InfoModel
import com.base.androidappwithcompose.model.UserModel

data class UserResponse(
    var results : ArrayList<UserModel>?,
    var info: InfoModel?
): BaseResponse()
