package com.wel.randomuser.cache.utils

import androidx.room.migration.Migration

class Migrations {
    companion object {
        const val DB_VERSION = 1

        //for next migrations
        fun getMigrations(): Array<Migration> {
            return arrayOf()
        }
    }
}
