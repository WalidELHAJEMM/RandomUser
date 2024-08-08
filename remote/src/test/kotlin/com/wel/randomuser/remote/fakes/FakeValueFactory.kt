package com.wel.randomuser.remote.fakes

import java.util.UUID

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }
}
