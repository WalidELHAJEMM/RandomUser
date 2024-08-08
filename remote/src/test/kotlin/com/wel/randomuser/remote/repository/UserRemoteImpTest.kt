package com.wel.randomuser.remote.repository

import com.wel.randomuser.remote.api.UserService
import com.wel.randomuser.remote.fakes.FakeRemoteData
import com.wel.randomuser.remote.mappers.UserEntityMapper
import com.wel.randomuser.remote.utils.RemoteBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
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
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRemoteImpTest : RemoteBaseTest() {

    @Mock
    lateinit var userService: UserService

    @Mock
    lateinit var mapper: UserEntityMapper

    lateinit var sut: UserRemoteImp

    @Before
    fun setUp() {
        sut = UserRemoteImp(userService, mapper)
    }

    @Test
    fun `get users should return response with list size 7 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(7)
            `when`(userService.getUsers()) doReturn response

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            assertEquals(users.size, 7)
            verify(mapper, times(7)).mapFromModel(any())
        }

    @Test
    fun `get users should return response with empty user list from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(0)
            `when`(userService.getUsers()) doReturn response

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            assertEquals(users.size, 0)
            verify(mapper, times(0)).mapFromModel(any())
        }

    @Test
    fun `get users should return error from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(userService.getUsers()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUsers() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(userService, times(1)).getUsers()
        }

}
