package com.wel.randomuser.data.source

import com.wel.randomuser.data.fakes.FakeUsers
import com.wel.randomuser.data.repository.UserRemote
import com.wel.randomuser.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRemoteDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var userRemote: UserRemote

    lateinit var sut: UserRemoteDataSource

    @Before
    fun setUp() {
        sut = UserRemoteDataSource(userRemote)
    }

    @Test
    fun `get users should return users from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            Mockito.`when`(userRemote.getUsers()) doReturn FakeUsers.getUsers()

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            TestCase.assertEquals(users.size, 2)
            verify(userRemote, times(1)).getUsers()
        }

    @Test
    fun `get users should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(userRemote.getUsers()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUsers() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(userRemote, times(1)).getUsers()
        }


    @Test
    fun `save users should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.saveUsers(FakeUsers.getUsers()) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }

    @Test
    fun `is cached should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.isCached() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }
}
