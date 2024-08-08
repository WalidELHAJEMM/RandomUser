package com.wel.randomuser.cache.mapper

import com.wel.randomuser.cache.models.UserCacheEntity
import com.wel.randomuser.data.models.UserEntity
import javax.inject.Inject

class UserCacheMapper @Inject constructor(
) : CacheMapper<UserCacheEntity, UserEntity> {
    override fun mapFromCached(type: UserCacheEntity): UserEntity {
        return UserEntity(
            firstName = type.firstName,
            lastName = type.lastname,
            smallPicture = type.smallPicture,
            email = type.email
        )
    }

    override fun mapToCached(type: UserEntity): UserCacheEntity {
        return UserCacheEntity(
            firstName = type.firstName,
            lastname = type.lastName,
            smallPicture = type.smallPicture,
            email = type.email
        )
    }
}
