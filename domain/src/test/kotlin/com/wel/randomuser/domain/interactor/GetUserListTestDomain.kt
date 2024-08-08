package com.wel.randomuser.domain.interactor

import com.wel.randomuser.domain.fakes.FakeData
import com.wel.randomuser.domain.repository.UserRepository
import com.wel.randomuser.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetUserListTestDomain : DomainBaseTest() {

    @Mock
    lateinit var userRepository: UserRepository

    lateinit var sut: GetUserListUseCase

    @Before
    fun setUp() {
        sut = GetUserListUseCase(userRepository)
    }

    @Test
    fun `get user should return success result with user list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(userRepository.getUsers()) doReturn FakeData.getUsers()

            // Act (When)
            val users = sut(Unit).single()

            // Assert (Then)
            assertEquals(users.size, 2)
            verify(userRepository, times(1)).getUsers()
        }

    @Test
    fun `get users should return error result with exception`() = dispatcher.runBlockingTest {
        // Arrange (Given)
        whenever(userRepository.getUsers()) doAnswer { throw IOException() }

        // Act (When)
        launch(exceptionHandler) { sut(Unit).single() }

        // Assert (Then)
        assertThat(exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java))
        verify(userRepository, times(1)).getUsers()
    }
}
