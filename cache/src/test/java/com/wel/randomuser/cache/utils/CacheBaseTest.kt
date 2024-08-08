package com.wel.randomuser.cache.utils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wel.randomuser.cache.dao.UserDao
import com.wel.randomuser.cache.database.UsersDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

@ExperimentalCoroutinesApi
abstract class CacheBaseTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    private lateinit var database: UsersDatabase
    protected lateinit var charaterDao: UserDao
    protected lateinit var context: Context

    @Before
    open fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, UsersDatabase::class.java)
            .allowMainThreadQueries().build()
        charaterDao = database.cachedUserDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        dispatcher.runBlockingTest {
            database.clearAllTables()
        }
        database.close()
    }
}
