package com.wel.randomuser.di

import com.wel.randomuser.data.UserRepositoryImp
import com.wel.randomuser.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImp): UserRepository =
        userRepository

}
