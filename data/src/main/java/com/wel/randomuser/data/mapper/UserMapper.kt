package com.wel.randomuser.data.mapper

import com.wel.randomuser.data.models.UserEntity
import com.wel.randomuser.domain.models.User
import javax.inject.Inject

class UserMapper @Inject constructor(
) : Mapper<UserEntity, User> {

    override fun mapFromEntity(type: UserEntity): User {
        return User(
            firstName = type.firstName,
            lastName = type.lastName,
            smallPicture = type.smallPicture,
            email = type.email
        )
    }

    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(
            firstName = type.firstName,
            lastName = type.lastName,
            smallPicture = type.smallPicture,
            email = type.email
        )
    }
}
