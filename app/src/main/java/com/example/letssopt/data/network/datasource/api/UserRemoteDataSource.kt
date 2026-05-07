package com.example.letssopt.data.network.datasource.api

import com.example.letssopt.data.dto.GetRecentUsersResponse
import com.example.letssopt.data.dto.GetUserProfileResponse
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getUserProfile(
        userId: String,
    ): Response<GetUserProfileResponse>

    suspend fun getRecentUsers(
    ): Response<GetRecentUsersResponse>
}
