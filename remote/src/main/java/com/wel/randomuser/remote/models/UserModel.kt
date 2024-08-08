package com.wel.randomuser.remote.models

data class UserModel(
    val gender: String? = null,
    val name: NameDto? = null,
    val location: LocationDto? = null,
    val email: String? = null,
    val login: LoginDto? = null,
    val dob: DobDto? = null,
    val registered: RegisteredDto? = null,
    val phone: String? = null,
    val cell: String? = null,
    val picture: PictureDto? = null,
    val nat: String? = null
)
