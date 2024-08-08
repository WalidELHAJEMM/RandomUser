package com.wel.randomuser.data.source

import com.wel.randomuser.data.repository.UserDataSource
import javax.inject.Inject

open class UserDataSourceFactory @Inject constructor(
    private val cacheDataSource: UserCacheDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {

    open suspend fun getDataStore(isCached: Boolean): UserDataSource {
        return if (isCached) {
            return getCacheDataSource()
        } else {
            getRemoteDataSource()
        }
    }

    fun getRemoteDataSource(): UserDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): UserDataSource {
        return cacheDataSource
    }
}
