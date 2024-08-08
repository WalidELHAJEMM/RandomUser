package com.wel.randomuser.data.source

import com.wel.randomuser.data.models.UserEntity
import com.wel.randomuser.data.repository.UserDataSource
import com.wel.randomuser.data.repository.UserRemote
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userRemote: UserRemote
) : UserDataSource {

    override suspend fun getUsers(): List<UserEntity> {
        return userRemote.getUsers()
    }

    override suspend fun saveUsers(listUsers: List<UserEntity>) {
        throw UnsupportedOperationException("Save user is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(): Boolean {
        throw UnsupportedOperationException("Cache is not supported for RemoteDataSource.")
    }
}
