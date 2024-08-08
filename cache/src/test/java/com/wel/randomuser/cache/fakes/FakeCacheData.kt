package com.wel.randomuser.cache.fakes

import com.wel.randomuser.cache.fakes.FakeValueFactory.randomString
import com.wel.randomuser.data.models.UserEntity

object FakeCacheData {

    fun getFakeUserEntity(
        size: Int,
    ): List<UserEntity> {
        val users = mutableListOf<UserEntity>()
        repeat(size) {
            users.add(createUserEntity())
        }
        return users
    }

    private fun createUserEntity(): UserEntity {
        return UserEntity(
            firstName = randomString(),
            lastName = randomString(),
            email = randomString(),
            smallPicture = randomString()
        )
    }
}
