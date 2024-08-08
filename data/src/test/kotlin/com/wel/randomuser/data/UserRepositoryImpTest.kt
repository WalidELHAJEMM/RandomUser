package com.wel.randomuser.data

import com.wel.randomuser.data.fakes.FakeUsers
import com.wel.randomuser.data.mapper.UserMapper
import com.wel.randomuser.data.repository.UserDataSource
import com.wel.randomuser.data.source.UserDataSourceFactory
import com.wel.randomuser.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImpTest : DataBaseTest() {

    @Mock
    lateinit var dataSourceFactory: UserDataSourceFactory

    @Mock
    lateinit var userMapper: UserMapper

    @Mock
    lateinit var dataSource: UserDataSource

    private lateinit var sut: UserRepositoryImp

    @Before
    fun setUp() {
        sut = UserRepositoryImp(dataSourceFactory, userMapper)
    }

    @Test
    fun `get users with cached true should return user list from local cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = true
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.isCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(
                dataSourceFactory.getDataStore(isCached).getUsers()
            ) doReturn FakeUsers.getUsers()

            // Act (When)
            val users = sut.getUsers().single()

            // Assert (Then)
            assertEquals(users.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSource, times(1)).isCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getUsers()
            verify(userMapper, times(2)).mapFromEntity(any())
        }

    @Test
    fun `get users with cached true should return user list from local cache and saved the user to local db`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = true
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.isCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(
                dataSourceFactory.getDataStore(isCached).getUsers()
            ) doReturn FakeUsers.getUsers()

            // Act (When)
            val users = sut.getUsers().single()

            // Assert (Then)
            assertEquals(users.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSource, times(1)).isCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getUsers()
            verify(userMapper, times(2)).mapFromEntity(any())
            verify(userMapper, times(2)).mapToEntity(anyOrNull())
            verify(dataSourceFactory.getCacheDataSource(), times(1)).saveUsers(any())
        }

    @Test
    fun `get users with cached false should return user list from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = false
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.isCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(
                dataSourceFactory.getDataStore(isCached).getUsers()
            ) doReturn FakeUsers.getUsers()

            // Act (When)
            val users = sut.getUsers().single()

            // Assert (Then)
            assertEquals(users.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSource, times(1)).isCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getUsers()
            verify(userMapper, times(2)).mapFromEntity(any())
        }

}
