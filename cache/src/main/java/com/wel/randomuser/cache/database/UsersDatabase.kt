package com.wel.randomuser.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wel.randomuser.cache.dao.UserDao
import com.wel.randomuser.cache.models.UserCacheEntity
import com.wel.randomuser.cache.utils.CacheConstants
import com.wel.randomuser.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [UserCacheEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class UsersDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}