package com.example.letssopt.data.remote.datasource.api

import com.example.letssopt.data.remote.dto.GetRecentUsersResponse
import com.example.letssopt.data.remote.dto.GetUserProfileResponse
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getUserProfile(
        userId: String,
    ): Response<GetUserProfileResponse>

    suspend fun getRecentUsers(
    ): Response<GetRecentUsersResponse>
}
