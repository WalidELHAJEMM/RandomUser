package com.wel.randomuser.cache.fakes

import java.util.UUID

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }
}
