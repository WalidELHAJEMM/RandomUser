package com.wel.randomuser.domain.repository

import com.wel.randomuser.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    // Remote and cache
    suspend fun getUsers(): Flow<List<User>>
    // Cache
    suspend fun saveUsers(listUsers: List<User>)
}
