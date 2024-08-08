package com.wel.randomuser.data.repository

import com.wel.randomuser.data.models.UserEntity

interface UserCache {
    suspend fun getUsers(): List<UserEntity>
    suspend fun saveUsers(listUsers: List<UserEntity>)
    suspend fun isCached(): Boolean

}
