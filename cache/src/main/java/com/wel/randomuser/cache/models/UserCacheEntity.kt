package com.wel.randomuser.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wel.randomuser.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.USER_TABLE_NAME)
data class UserCacheEntity(
    @PrimaryKey
    val firstName: String,
    val lastname: String,
    val email: String,
    val smallPicture: String,
)
