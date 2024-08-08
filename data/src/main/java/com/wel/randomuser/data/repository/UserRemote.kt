package com.wel.randomuser.data.repository

import com.wel.randomuser.data.models.UserEntity

interface UserRemote {
    suspend fun getUsers(): List<UserEntity>
}
