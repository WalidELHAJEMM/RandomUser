package com.wel.randomuser.di

import com.wel.randomuser.BuildConfig
import com.wel.randomuser.data.repository.UserRemote
import com.wel.randomuser.remote.api.UserService
import com.wel.randomuser.remote.api.ServiceFactory
import com.wel.randomuser.remote.repository.UserRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideBlogService(): UserService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideUserRemote(userRemote: UserRemoteImp): UserRemote {
        return userRemote
    }
}
