package com.wel.randomuser.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wel.randomuser.cache.fakes.FakeCacheData
import com.wel.randomuser.cache.mapper.UserCacheMapper
import com.wel.randomuser.cache.utils.CacheBaseTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class UserCacheImpTest : CacheBaseTest() {

    private val userCacheMapper = UserCacheMapper()
    lateinit var sut: UserCacheImp

    @Before
    override fun setup() {
        super.setup()
        sut = UserCacheImp(charaterDao, userCacheMapper)
    }

    @Test
    fun `get users should return success users from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userEntity = FakeCacheData.getFakeUserEntity(7)
            // Saving users to database so we can get it when select query executes
            sut.saveUsers(userEntity)

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            assertEquals(users.size, 7)
        }

    @Test
    fun `get user should return success users from local room cache with empty list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) no arrange

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            assertTrue(users.isEmpty())
        }



    @Test
    fun `save user should return saved users from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userEntity = FakeCacheData.getFakeUserEntity(7)
            // Act (When)
            sut.saveUsers(userEntity)
            val users = sut.getUsers()
            // Assert (Then)
            assertEquals(users.size, 7)
        }

}
