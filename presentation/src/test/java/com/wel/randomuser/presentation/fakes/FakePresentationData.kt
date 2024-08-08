package com.wel.randomuser.presentation.fakes

import com.wel.randomuser.domain.models.User
import com.wel.randomuser.presentation.fakes.FakeValueFactory.randomString

object FakePresentationData {

    fun getUsers(
        size: Int,
    ): List<User> {
        val users = mutableListOf<User>()
        repeat(size) {
            users.add(createUser())
        }
        return users
    }

    private fun createUser(): User {
        return User(
            firstName = randomString(),
            lastName = randomString(),
            email = randomString(),
            smallPicture = randomString(),
          )
    }
}
