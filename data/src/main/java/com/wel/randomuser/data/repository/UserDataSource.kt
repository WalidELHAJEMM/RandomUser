package com.wel.randomuser.data.repository

import com.wel.randomuser.data.models.UserEntity

interface UserDataSource {
    // Remote and cache
    suspend fun getUsers(): List<UserEntity>
    // Cache
    suspend fun saveUsers(listUsers: List<UserEntity>)
    suspend fun isCached(): Boolean

}
