package com.wel.randomuser.data.source

import com.wel.randomuser.data.models.UserEntity
import com.wel.randomuser.data.repository.UserCache
import com.wel.randomuser.data.repository.UserDataSource
import javax.inject.Inject

class UserCacheDataSource @Inject constructor(
    private val userCache: UserCache
) : UserDataSource {

    override suspend fun getUsers(): List<UserEntity> {
        return userCache.getUsers()
    }

    override suspend fun saveUsers(listUsers: List<UserEntity>) {
        userCache.saveUsers(listUsers)
    }

    override suspend fun isCached(): Boolean {
        return userCache.isCached()
    }
}
