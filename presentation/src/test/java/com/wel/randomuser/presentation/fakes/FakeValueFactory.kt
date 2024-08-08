package com.wel.randomuser.presentation.fakes

import java.util.UUID

object FakeValueFactory {
    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

}
