package com.wel.randomuser.data.fakes

import com.wel.randomuser.data.models.UserEntity

object FakeUsers {
    fun getUsers(): List<UserEntity> = listOf(
        UserEntity(
            "EL HAJEM",
            "WALID",
            "walid@coppernic.fr",
            "https://dummyurl1.png"
        ),
        UserEntity(
            "Alexandre",
            "Anriot",
            "alexandre@coppernic.fr",
            "https://dummyurl2.png"
        )
    )
}