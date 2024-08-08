package com.wel.randomuser.domain.fakes

import com.wel.randomuser.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getUsers(): Flow<List<User>> = flow {
        val users = listOf(
            User(
                "Alexandre",
                "Anriot",
                "alexandre@coppernic.fr",
                "https://dummyurl2.png"
            ),
            User(
                "EL HAJEM",
                "WALID",
                "walid@coppernic.fr",
                "https://dummyurl1.png"
            )
        )
        emit(users)
    }

}
