package com.wel.randomuser.data

import com.wel.randomuser.data.mapper.UserMapper
import com.wel.randomuser.data.source.UserDataSourceFactory
import com.wel.randomuser.domain.models.User
import com.wel.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val dataSourceFactory: UserDataSourceFactory,
    private val userMapper: UserMapper,
) : UserRepository {

    override suspend fun getUsers(): Flow<List<User>> = flow {
        val isCached = dataSourceFactory.getCacheDataSource().isCached()
        val userList =
            dataSourceFactory.getDataStore(isCached).getUsers().map { userEntity ->
                userMapper.mapFromEntity(userEntity)
            }
        saveUsers(userList)
        emit(userList)
    }

    override suspend fun saveUsers(listUsers: List<User>) {
        val userEntities = listUsers.map { user ->
            userMapper.mapToEntity(user)
        }
        dataSourceFactory.getCacheDataSource().saveUsers(userEntities)
    }

}
