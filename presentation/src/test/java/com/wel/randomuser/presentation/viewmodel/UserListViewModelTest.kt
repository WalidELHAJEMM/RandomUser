package com.wel.randomuser.presentation.viewmodel

import androidx.lifecycle.Observer
import com.wel.randomuser.domain.interactor.GetUserListUseCase
import com.wel.randomuser.presentation.fakes.FakePresentationData
import com.wel.randomuser.presentation.utils.PresentationBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.wel.randomuser.presentation.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest : PresentationBaseTest() {

    @Mock
    lateinit var usersUseCase: GetUserListUseCase

    @Mock
    private lateinit var observer: Observer<DataState>

    private lateinit var sut: UserListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = UserListViewModel(dispatcher, usersUseCase)
        sut.getRandomUsers().observeForever(observer)
    }

    @Test
    fun `get users should return user list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val users = FakePresentationData.getUsers(7)
            `when`(usersUseCase(Unit)).thenReturn(flowOf(users))

            // Act (When)
            sut.getRandomUsers()

            // Assert (Then)
            verify(observer).onChanged(DataState.Loading)
            verify(observer).onChanged(DataState.Success(users))
        }

    @Test
    fun `get users should return empty user list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val users = FakePresentationData.getUsers(0)
            `when`(usersUseCase(Unit)).thenReturn(flowOf(users))

            // Act (When)
            sut.getRandomUsers()

            // Assert (Then)
            verify(observer).onChanged(DataState.Loading)
            verify(observer).onChanged(DataState.Success(users))
        }

    @Test
    fun `get users should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val errorMessage = "Internal server error"
            whenever(usersUseCase(Unit)) doAnswer { throw IOException(errorMessage) }

            // Act (When)
            sut.getRandomUsers()

            // Assert (Then)
            verify(observer).onChanged(DataState.Loading)
            verify(observer).onChanged(DataState.Error(errorMessage))
        }


    @After
    fun tearDown() {
        sut.onCleared()
    }
}
