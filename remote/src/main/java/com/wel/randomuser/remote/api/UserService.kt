package com.wel.randomuser.remote.api

import com.wel.randomuser.remote.models.UserResponseModel
import com.wel.randomuser.remote.utils.RemoteConstants.DEFAULT_PAGE
import com.wel.randomuser.remote.utils.RemoteConstants.DEFAULT_PAGE_SIZE
import com.wel.randomuser.remote.utils.RemoteConstants.DEFAULT_SEED
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api")
    suspend fun getUsers(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("results") results: Int = DEFAULT_PAGE_SIZE,
        @Query("seed") seed: Int = DEFAULT_SEED,
    ): UserResponseModel
}