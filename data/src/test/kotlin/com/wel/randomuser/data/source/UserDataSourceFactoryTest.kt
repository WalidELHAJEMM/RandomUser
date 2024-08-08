package com.wel.randomuser.data.source

import com.wel.randomuser.data.utils.DataBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserDataSourceFactoryTest : DataBaseTest() {

    @Mock
    lateinit var dataSourceCache: UserCacheDataSource

    @Mock
    lateinit var dataSourceRemote: UserRemoteDataSource

    lateinit var sut: UserDataSourceFactory

    @Before
    fun setUp() {
        sut = UserDataSourceFactory(dataSourceCache, dataSourceRemote)
    }

    @Test
    fun `get data store with cache should return users from cache data-source`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = true
            // Act (When)
            val dataSource = sut.getDataStore(isCached)
            // Assert (Then)
            assertThat(dataSource, instanceOf(UserCacheDataSource::class.java))
        }

    @Test
    fun `get data store with cache should return users from remote data-source`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = true
            // Act (When)
            val dataSource = sut.getDataStore(isCached)
            // Assert (Then)
            assertThat(dataSource, instanceOf(UserCacheDataSource::class.java))
        }
}
