package com.wel.randomuser.data.source

import com.wel.randomuser.data.fakes.FakeUsers
import com.wel.randomuser.data.repository.UserCache
import com.wel.randomuser.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserCacheDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var userCache: UserCache

    lateinit var sut: UserCacheDataSource

    @Before
    fun setUp() {
        sut = UserCacheDataSource(userCache)
    }

    @Test
    fun `get users should return users from local cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            `when`(userCache.getUsers()) doReturn FakeUsers.getUsers()

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            assertEquals(users.size, 2)
            verify(userCache, times(1)).getUsers()
        }

    @Test
    fun `get users should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(userCache.getUsers()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUsers() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(userCache, times(1)).getUsers()
        }



    @Test
    fun `save user passed user list should save user into local cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val users = FakeUsers.getUsers()
            // Act (When)
            sut.saveUsers(users)

            // Assert (Then)
            verify(userCache, times(1)).saveUsers(users)
        }

    @Test
    fun `save user passed user list should return error failed to save last time`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val users = FakeUsers.getUsers()
            whenever(userCache.saveUsers(users)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.saveUsers(users) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(userCache, times(1)).saveUsers(users)
        }



    @Test
    fun `is cached should return true`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            `when`(userCache.isCached()) doReturn true

            // Act (When)
            val resultStatus = sut.isCached()

            // Assert (Then)
            assertTrue(resultStatus)
            verify(userCache, times(1)).isCached()
        }

    @Test
    fun `is cached should return false`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            `when`(userCache.isCached()) doReturn false

            // Act (When)
            val resultStatus = sut.isCached()

            // Assert (Then)
            assertFalse(resultStatus)
            verify(userCache, times(1)).isCached()
        }
}
