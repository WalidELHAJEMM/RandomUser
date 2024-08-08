package com.wel.randomuser.remote.repository

import com.wel.randomuser.data.models.UserEntity
import com.wel.randomuser.data.repository.UserRemote
import com.wel.randomuser.remote.api.UserService
import com.wel.randomuser.remote.mappers.UserEntityMapper
import javax.inject.Inject

class UserRemoteImp @Inject constructor(
    private val userService: UserService,
    private val userEntityMapper: UserEntityMapper
) : UserRemote {

    override suspend fun getUsers(): List<UserEntity> {
        return userService.getUsers().users.map { userModel ->
            userEntityMapper.mapFromModel(userModel)
        }
    }

}
