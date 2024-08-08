package com.wel.randomuser.remote.fakes

import com.wel.randomuser.remote.fakes.FakeValueFactory.randomString
import com.wel.randomuser.remote.models.LoginDto
import com.wel.randomuser.remote.models.NameDto
import com.wel.randomuser.remote.models.UserModel
import com.wel.randomuser.remote.models.UserResponseModel

object FakeRemoteData {

    fun getResponse(size: Int, isRandomId: Boolean = true): UserResponseModel {
        return UserResponseModel(getFakeUserModel(size, isRandomId))
    }

    private fun getFakeUserModel(size: Int, isRandomId: Boolean): List<UserModel> {
        val users = mutableListOf<UserModel>()
        repeat(size) {
            users.add(getUserModel(isRandomId))
        }
        return users
    }

    private fun getUserModel(isRandomId: Boolean): UserModel {
        return UserModel(
            name = if (isRandomId) NameDto(
                randomString(),
                randomString(),
                randomString()
            ) else null,
            phone = randomString(),
            email = randomString(),
            login = LoginDto(randomString(), randomString(), randomString()),
            cell = randomString()
        )
    }

}
