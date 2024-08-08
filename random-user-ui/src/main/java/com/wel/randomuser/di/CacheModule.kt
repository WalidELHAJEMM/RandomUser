package com.wel.randomuser.di

import android.content.Context
import com.wel.randomuser.cache.UserCacheImp
import com.wel.randomuser.cache.dao.UserDao
import com.wel.randomuser.cache.database.UsersDatabase
import com.wel.randomuser.data.repository.UserCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): UsersDatabase {
        return UsersDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(usersDatabase: UsersDatabase): UserDao {
        return usersDatabase.cachedUserDao()
    }

    @Provides
    @Singleton
    fun provideUserCache(userCache: UserCacheImp): UserCache {
        return userCache
    }

}
