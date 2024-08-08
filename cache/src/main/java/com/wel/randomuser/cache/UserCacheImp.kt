package com.wel.randomuser.cache

import com.wel.randomuser.cache.dao.UserDao
import com.wel.randomuser.cache.mapper.UserCacheMapper
import com.wel.randomuser.data.models.UserEntity
import com.wel.randomuser.data.repository.UserCache
import javax.inject.Inject

class UserCacheImp @Inject constructor(
    private val userDao: UserDao,
    private val userCacheMapper: UserCacheMapper,
) : UserCache {

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers().map { cacheUser ->
            userCacheMapper.mapFromCached(cacheUser)
        }
    }

    override suspend fun saveUsers(listUsers: List<UserEntity>) {
        userDao.addUser(
            *listUsers.map {
                userCacheMapper.mapToCached(it)
            }.toTypedArray()
        )
    }

    override suspend fun isCached(): Boolean {
        return userDao.getUsers().isNotEmpty()
    }

}
