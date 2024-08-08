package com.wel.randomuser.remote.mappers

import com.wel.randomuser.data.models.UserEntity
import com.wel.randomuser.remote.models.UserModel
import javax.inject.Inject

class UserEntityMapper @Inject constructor(
) : EntityMapper<UserModel, UserEntity> {
    override fun mapFromModel(model: UserModel): UserEntity {
        return UserEntity(
            firstName = model.name!!.first,
            lastName = model.name.last!!,
            smallPicture = model.picture!!.large!!,
            email = model.email!!
        )
    }
}
