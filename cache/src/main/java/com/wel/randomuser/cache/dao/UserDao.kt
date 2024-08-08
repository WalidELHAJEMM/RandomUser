package com.wel.randomuser.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wel.randomuser.cache.models.UserCacheEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<UserCacheEntity>

    @Query("DELETE FROM users")
    fun clearUsers(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(vararg user: UserCacheEntity)
}
