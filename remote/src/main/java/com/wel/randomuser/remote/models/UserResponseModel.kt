package com.wel.randomuser.remote.models

import com.squareup.moshi.Json

data class UserResponseModel(
    @field:Json(name = "results")
    val users: List<UserModel>
)
